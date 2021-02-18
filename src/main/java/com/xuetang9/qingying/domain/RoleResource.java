package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/22 8:56
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_role_resource")
public class RoleResource implements Serializable {

    @Id
    private Integer id;

    @Column(name = "role_id")
    private Integer roleId;

    @Column(name = "resource_id")
    private Integer resourceId;

}
