package net.mshop;

import java.io.Serializable;

/**
 * Created by Panfuhao on 2016/9/29.
 */
public class Principal implements Serializable{
    private Long id;
    private String username;

    public Principal(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
