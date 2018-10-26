package com.shianxian.trace.config.shiro;

import com.github.pagehelper.util.StringUtil;
import com.shianxian.trace.sys.pojo.Permission;
import com.shianxian.trace.sys.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * shiro重新加载权限
 */
@Service
@Slf4j
public class ShiroService {

    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Autowired
    private PermissionService permissionService;

    /**
     * 初始化权限
     */
    public Map<String, String> loadFilterChainDefinitions() {
        // 权限控制map.从数据库获取
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

        filterChainDefinitionMap.put("/login", "anon");

        filterChainDefinitionMap.put("/logout", "anon");

//        filterChainDefinitionMap.put("/css/**", "anon");

//        filterChainDefinitionMap.put("/js/**", "anon");

//        filterChainDefinitionMap.put("/img/**", "anon");

        List<Permission> resourcesList = this.permissionService.selectAll();

        for (Permission per : resourcesList) {
            if (StringUtil.isNotEmpty(per.getUrl())) {
                String permission = "perms[" + per.getPermissionCode() + "]";
                filterChainDefinitionMap.put(per.getUrl(), permission);
            }
        }

        filterChainDefinitionMap.put("/**", "authc");
        return filterChainDefinitionMap;

    }


    /**
     * 重新加载权限
     */
    public void reloadPermission() {
        synchronized (shiroFilterFactoryBean) {
            AbstractShiroFilter shiroFilter = null;
            try {
                shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            } catch (Exception e) {
                throw new RuntimeException("从shiroFilterFactoryBean获取ShiroFilter错误!");
            }
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            shiroFilterFactoryBean.setFilterChainDefinitionMap(loadFilterChainDefinitions());

            // 重新构建生成
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            log.info("更新权限成功！！");
        }
    }

}