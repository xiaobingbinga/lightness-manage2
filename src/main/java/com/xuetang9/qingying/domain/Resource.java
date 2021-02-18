package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:22
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_resource")
public class Resource implements Serializable {

    @Id
    private Integer id;
    private String url;
    private Integer type;
    private String icon;
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;
    private Integer sort;

    private String component;

}
