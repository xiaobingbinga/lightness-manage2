package com.xuetang9.qingying.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 9:37
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_brand")
public class Brand implements Serializable {

    @Id
    private Integer id;

    @NotBlank
    private String name;

    private String image;

    @NotBlank
    private String letter;

}
