package com.latico.archetype.springboot.bean.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-10-31 11:21
 * @Version: 1.0
 */
@Data
public class DemoPageRequestParam implements Serializable {
    private int pageNum;
    private int pageSize;
}
