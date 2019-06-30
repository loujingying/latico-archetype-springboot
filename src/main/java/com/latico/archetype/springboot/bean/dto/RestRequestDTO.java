package com.latico.archetype.springboot.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <PRE>
 * 请求参数
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-03-20 15:56
 * @Version: 1.0
 */
@ApiModel("请求传输对象")
public class RestRequestDTO<T> implements Serializable {
    private static final long serialVersionUID = -8147655332473090271L;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RestRequestDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", param=").append(param);
        sb.append('}');
        return sb.toString();
    }
}
