package com.latico.archetype.springboot.client.type_common_feign;


import com.latico.archetype.springboot.dao.primary.entity.Demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * <PRE>
 @RequestMapping(value = "selectDemo")
 public List<Demo> selectDemo() {
 return demoRepository.findAll();
 }

 @RequestMapping(value = "selectDemo2")
 public List<Demo> selectDemo2() {
 return demo2Mapper.findAll();
 }
 * </PRE>
 *
 * @Author: latico
 * @Date: 2019-03-05 16:13
 * @Version: 1.0
 */
@Path("/test")
public interface TestDBControllerClient {

    @POST
    @Path("/selectDemo")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Demo> selectDemo();

    @POST
    @Path("/selectDemo2")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public List<Demo> selectDemo2();

}
