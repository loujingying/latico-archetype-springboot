package com.latico.archetype.springboot.bean.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <PRE>
 *  时间对象
 *
 * </PRE>
 * @author: latico
 * @date: 2019-06-25 14:25:20
 * @version: 1.0
 */
@Data
@ApiModel("演示时间bean")
public class DemoTimeParam implements Serializable {

    private static final long serialVersionUID = -6041985998342837879L;

    @ApiModelProperty("时间")
    private Date time;

    @ApiModelProperty(value = "名称", example = "时间1")
    private String name;

}
