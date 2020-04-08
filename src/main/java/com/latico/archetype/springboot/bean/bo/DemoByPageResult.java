package com.latico.archetype.springboot.bean.bo;

import com.latico.archetype.springboot.dao.primary.entity.Demo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

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
public class DemoByPageResult implements Serializable {
    private List<Demo> demos;
    private int totalPages;
    private int pageNum;
    private int pageSize;
    private long totalElements;
}
