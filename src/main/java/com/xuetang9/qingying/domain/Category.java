package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/15 19:19
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_category")
public class Category implements Serializable {

    @Id
    private Integer id;

    @NotBlank(message = "类目名称必填")
    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "is_leaf")
    private Boolean isLeaf;

    private Integer sort;

}
