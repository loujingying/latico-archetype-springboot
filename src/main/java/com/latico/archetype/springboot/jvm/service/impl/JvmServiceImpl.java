package com.latico.archetype.springboot.jvm.service.impl;

import com.latico.archetype.springboot.bean.dto.RestResponseDTO;
import com.latico.archetype.springboot.jvm.bean.JvmPerfInfo;
import com.latico.archetype.springboot.jvm.service.JvmService;
import org.springframework.stereotype.Service;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-14 11:36
 * @version: 1.0
 */
@Service
public class JvmServiceImpl implements JvmService {
    @Override
    public RestResponseDTO<JvmPerfInfo> queryCurrentPerf() {
        return null;
    }
}
