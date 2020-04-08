package com.latico.archetype.springboot.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-03-20 16:09
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("响应传输对象")
public class RestResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 请求ID，决定请求的唯一性
     */
    @ApiModelProperty("响应ID，决定请求的唯一性,与请求ID对应")
    private String id;
    /**
     * 请求的名称或者描述
     */
    @ApiModelProperty("对象名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty("对象描述")
    private String descr;

    /**
     * 总状态，成功/失败，true/false
     */
    @ApiModelProperty("对象状态,true/false")
    private Boolean status;

    /**
     * 扩展的参数
     */
    @ApiModelProperty("泛型扩展参数对象")
    private T param;

}
