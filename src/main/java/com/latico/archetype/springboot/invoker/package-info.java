/**
 * <PRE>
 * 调用者，restful客户端，调用别的服务
 * 可以参考该包下的测试用例里面的代码，也可以参考如下写法，
 * 示例如下：
 @Path("/test")
 public interface DemoServiceClient {

    @POST
    @Path("/serverTime")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    DemoTimeParam serverTime();

   @POST
   @Path("/serverTimeBean")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
       DemoTimeParam serverTimeBean(DemoTimeParam timeBean);

   @POST
   @Path("/serverTimeStr")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
       String serverTimeStr();

   @POST
   @Path("testRequestParam")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
   public String testRequestParam(@QueryParam("name") String name);

   @POST
   @Path("testPathVariable/{name}")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
   public String testPathVariable(@PathParam("name") String name) ;

   @POST
   @Path("/testMultiPathVariable/{name}/{value}")
   @Produces({MediaType.APPLICATION_JSON})
   @Consumes({MediaType.APPLICATION_JSON})
   public String testMultiPathVariable(@PathParam("name") String name, @PathParam("value") String value) ;

 }
 * </PRE>
 *
 * @Author: LanDingDong
 * @Date: 2019-05-09 15:09
 * @Version: 1.0
 */
package com.latico.archetype.springboot.invoker;