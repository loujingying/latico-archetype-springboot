/**
 * <PRE>
 * 客户端调用服务端的API等
 * 这里是通用的，使用feign的客户端，不支持连接注册中心
 *
 * 如果是自测，不管是不是springcloud项目，建议使用该方式，这种方式不需要启动springboot测试效率高，
 * 只是如果是springcloud集成全量测试，那么就没办法支持；
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-02-21 22:56
 * @Version: 1.0
 */
package com.latico.archetype.springboot.client.type_common_feign;