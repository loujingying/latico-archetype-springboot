package com.latico.archetype.springboot.dao.secondary.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author landingdong
 * @since 2019-02-26
 */
public class Demo2 implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private Boolean administrator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        return "Demo2{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", administrator=" + administrator +
        "}";
    }
}
