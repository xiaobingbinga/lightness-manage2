package com.xuetang9.qingying.domain.vo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 11:52
 * @copyright 老九学堂
 */
@Data
public class SpecParamVO implements Serializable {

    private Integer id;

    private Integer categoryId;

    private Integer specificationGroupId;

    private String name;

    private String values;

    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searchable;

    private String range;

    private Date createTime;

    private Date updateTime;
}
