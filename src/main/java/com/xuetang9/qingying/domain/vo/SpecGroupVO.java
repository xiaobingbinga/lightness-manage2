package com.xuetang9.qingying.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/17 11:51
 * @copyright 老九学堂
 */
@Data
public class SpecGroupVO implements Serializable {

    private Integer id;
    private Integer categoryId;
    private String name;

    private List<SpecParamVO> params;

}
