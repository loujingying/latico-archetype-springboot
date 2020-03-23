package com.latico.archetype.springboot.dao.secondary.entity;

import lombok.Data;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 11:54:07
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "demo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Demo2 implements Serializable {

	@Column(name = "auto_id", insertable = false, updatable = false)
	private Integer autoId;

	@Id
	private String id;

	@Column(name = "task_id")
	private Integer taskId;

	@Column(name = "exec_type")
	private String execType;

	@Column(name = "exec_status")
	private Integer execStatus;

	@Column(name = "status_descr")
	private String statusDescr;

	@Column(name = "call_param")
	private String callParam;

	@Column(name = "call_client_id")
	private String callClientId;

	@Column(name = "collect_server_id")
	private String collectServerId;

	@Column(name = "handle_server_id")
	private String handleServerId;

	@Column(name = "create_time")
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

	@Column(name = "handle_status")
	private Integer handleStatus;

	@Column(name = "handle_descr")
	private String handleDescr;

	@Column(name = "handle_start_time")
	private java.util.Date handleStartTime;

	@Column(name = "handle_end_time")
	private java.util.Date handleEndTime;

}
