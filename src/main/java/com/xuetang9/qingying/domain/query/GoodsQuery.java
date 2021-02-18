package com.xuetang9.qingying.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品查询的条件对象
 *
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:15
 * @copyright 老九学堂
 */
@Data
public class GoodsQuery implements Serializable {

    private String name;
    private Integer categoryId;
    private Integer brandId;
    private Boolean marketable;

}
