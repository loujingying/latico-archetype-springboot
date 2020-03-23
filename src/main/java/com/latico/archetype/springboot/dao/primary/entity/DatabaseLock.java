package com.latico.archetype.springboot.dao.primary.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 11:55:17
 * @version: 1.0
 */
@Data
@Entity
@Table(name = "database_lock")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DatabaseLock implements Serializable {

	@Column(name = "auto_id", updatable = false, insertable = false)
	private Integer autoId;

	@Id
	private String id;

	@Column(name = "lock_key")
	private String lockKey;

	@Column(name = "lock_value")
	private String lockValue;

	@Column(name = "lock_status")
	private Integer lockStatus;

	@Column(name = "expire_time")
	private Long expireTime;

	private String descr;

	@Column(name = "lock_holder")
	private String lockHolder;

	@Column(name = "create_time", updatable = false)
	private java.util.Date createTime;

	@Column(name = "update_time")
	private java.util.Date updateTime;

}
