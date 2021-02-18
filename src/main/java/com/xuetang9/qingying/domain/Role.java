package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 20:54
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_role")
public class Role implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String description;

    @Column(name = "is_disabled")
    private Boolean disabled;

    @Column(name = "is_deleted")
    private Boolean deleted;

}
