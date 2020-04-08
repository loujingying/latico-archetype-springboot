package com.latico.archetype.springboot.bean.bo;

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
 * @date: 2019-10-31 11:21
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoPageRequestParam implements Serializable {
    private int pageNum;
    private int pageSize;
}
