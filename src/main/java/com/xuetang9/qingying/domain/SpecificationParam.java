package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 11:46
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_specification_param")
public class SpecificationParam implements Serializable {

    @Id
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "specification_group_id")
    private Integer specificationGroupId;

    @Column(name = "`name`")
    private String name;

    @Column(name = "`values`")
    private String values;

    @Column(name = "`numeric`")
    private Boolean numeric;

    private String unit;

    private Boolean generic;

    private Boolean searchable;

    @Column(name = "`range`")
    private String range;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
