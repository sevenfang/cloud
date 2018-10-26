package com.shianxian.trace.base.vo;

import com.shianxian.trace.base.pojo.Material;
import lombok.Data;

/**
 * @Auther: 赵明明
 * @Date: 2018/9/27 9:06
 * @Description: 物料VO对象
 */
@Data
public class MaterialVO extends Material {

    /**
     * 计量单位名称
     */
    private String unitName;

    /**
     * 物料类别名称
     */
    private String categoryName;
}
