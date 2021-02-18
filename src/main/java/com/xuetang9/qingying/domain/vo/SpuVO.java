package com.xuetang9.qingying.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/20 10:00
 * @copyright 老九学堂
 */
@Data
public class SpuVO implements Serializable {

    private Integer id;
    private String name;
    private String categoryName;
    private String brandName;
}
