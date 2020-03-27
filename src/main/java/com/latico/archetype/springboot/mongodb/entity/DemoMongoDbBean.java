package com.latico.archetype.springboot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
public class DemoMongoDbBean implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    private String username;
    private Integer age;

}
