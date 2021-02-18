package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/22 12:00
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_user_role")
public class UserRole implements Serializable {

    @Id
    private Integer id;

    private Integer userId;

    private Integer roleId;

}
