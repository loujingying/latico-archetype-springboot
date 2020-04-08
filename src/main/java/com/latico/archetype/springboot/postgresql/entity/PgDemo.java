package com.latico.archetype.springboot.postgresql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 * @date: 2020-04-08 15:40:12
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pg_demo")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PgDemo implements Serializable {

	@Id
	@Column(name = "auto_id")
	private Integer autoId;

	private String id;

	private String name;

	private Double price;

}
