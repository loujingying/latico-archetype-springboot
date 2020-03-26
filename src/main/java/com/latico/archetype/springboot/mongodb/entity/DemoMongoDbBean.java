package com.latico.archetype.springboot.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * <PRE>
 *
 * </PRE>
 * @author: latico
 * @date: 2020-03-26 14:20:40
 * @version: 1.0
 */
@Data
@Document(collection = "user")
public class DemoMongoDbBean implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    private String username;
    private Integer age;

    public DemoMongoDbBean() {
    }

    public DemoMongoDbBean(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
}
