package com.shianxian.trace.base.dao;

import com.shianxian.trace.base.pojo.Material;
import com.shianxian.trace.base.vo.MaterialVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


/**
 * @Auther: 赵明明
 * @Date: 2018/9/25 13:47
 * @Description: 物料持久层实现
 */
@Repository
public interface MaterialDao extends Mapper<Material> {


    /**
     * 根据企业id、物料类型分页查询物料
     * @param material
     * @return
     */
    List<MaterialVO> selectMaterialByPage(@Param("material") Material material);
}
