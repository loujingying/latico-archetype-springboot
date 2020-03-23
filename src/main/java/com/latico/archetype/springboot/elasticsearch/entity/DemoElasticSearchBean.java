package com.latico.archetype.springboot.elasticsearch.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * <PRE>
 * indexName是索引的名称，相当于数据库，
 * type相当于表
 * Accessorsk注解可以在setter后返回自身对象，这样就可以使用链式编程
 * NoArgsConstructor注解可以生成一个无参构造函数，需要结合Data注解
 * 注意：elasticsearch7以上，不支持type
 * </PRE>
 *
 * @author: latico
 * @date: 2020-03-10 10:47
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
// @Document(indexName = "idx_demo", type="demo", shards = 5, replicas = 1)
@Document(indexName = "idx_demo", shards = 5, replicas = 1)
public class DemoElasticSearchBean {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String firstCode;

    @Field(type = FieldType.Keyword)
    private String secordCode;

    // @Field(type = FieldType.Text, analyzer = "ik_max_word")
    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Integer)
    private Integer type;

    public DemoElasticSearchBean(String id, String firstCode, String secordCode, String content, Integer type){
        this.id=id;
        this.firstCode=firstCode;
        this.secordCode=secordCode;
        this.content=content;
        this.type=type;
    }
}
