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
 * @date 2020/7/20 15:00
 * @copyright 老九学堂
 */
@Data
@Table(name = "xt_spu")
public class Spu implements Serializable {

    @Id
    private Integer id;

    private String name;

    @Column(name = "category_id1")
    private Integer categoryId1;

    @Column(name = "category_id2")
    private Integer categoryId2;

    @Column(name = "category_id3")
    private Integer categoryId3;

    @Column(name = "brand_id")
    private Integer brandId;

    private Boolean marketable;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

}
