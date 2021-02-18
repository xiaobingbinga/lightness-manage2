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
 * @date 2020/7/17 11:43
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_specification_group")
public class SpecificationGroup implements Serializable {

    @Id
    private Integer id;

    @Column(name = "category_id")
    private Integer categoryId;

    private String name;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
