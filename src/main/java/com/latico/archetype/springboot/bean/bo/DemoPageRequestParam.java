package com.latico.archetype.springboot.bean.bo;

import lombok.Data;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2019-10-31 11:21
 * @version: 1.0
 */
@Data
public class DemoPageRequestParam implements Serializable {
    private int pageNum;
    private int pageSize;
}
