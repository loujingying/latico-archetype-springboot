package com.latico.archetype.springboot.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <PRE>
 * 请求参数
 * </PRE>
 *
 * @author: latico
 * @date: 2019-03-20 15:56
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("请求传输对象")
public class RestRequestDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 请求ID，决定请求的唯一性
     */
    @ApiModelProperty("请求ID，决定请求的唯一性,与响应ID对应,建议使用UUID")
    private String id;
    /**
     * 请求的名称或者描述
     */
    @ApiModelProperty("对象名称")
    private String name;

    /**
     * 扩展的参数
     */
    @ApiModelProperty("泛型扩展参数对象")
    private T param;

}
