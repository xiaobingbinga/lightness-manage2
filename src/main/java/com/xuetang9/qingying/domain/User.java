package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/14 16:56
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_user")
public class User implements Serializable {

    @Id
    private Integer id;

    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    private String phone;

    private String email;

    @Column(name = "is_disabled")
    private Boolean disabled;

    @Column(name = "is_deleted")
    private Boolean deleted;

    @Column(name = "employee_id")
    private Integer employeeId;

}
