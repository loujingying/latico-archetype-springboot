/**
 * <PRE>
 * TO （DTO） ：Data Transfer Object数据传输对象
 * 1 ．用在需要跨进程或远程传输时，它不应该包含业务逻辑。
 * 2 ．比如一张表有100个字段，那么对应的PO就有100个属性（大多数情况下，DTO 内的数据来自多个表）。但view层只需显示10个字段，没有必要把整个PO对象传递到client，这时我们就可以用只有这10个属性的DTO来传输数据到client，这样也不会暴露server端表结构。到达客户端以后，如果用这个对象来对应界面显示，那此时它的身份就转为VO。
 * </PRE>
 *
 * @author: latico
 * @date: 2019-02-21 22:54
 * @version: 1.0
 */
package com.latico.archetype.springboot.bean.dto;