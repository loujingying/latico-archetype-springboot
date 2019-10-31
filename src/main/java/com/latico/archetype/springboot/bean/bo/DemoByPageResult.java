package com.latico.archetype.springboot.bean.bo;

import com.latico.archetype.springboot.dao.primary.entity.Demo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
public class DemoByPageResult implements Serializable {
    private List<Demo> demos;
    private int totalPages;
    private int pageNum;
    private int pageSize;
    private long totalElements;
}
