package com.latico.archetype.springboot.jvm.service;

import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.jvm.bean.JvmPerfInfo;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-14 11:36
 * @version: 1.0
 */
public interface JvmService {
    RestResponseDTO<JvmPerfInfo> queryCurrentPerf();
}
