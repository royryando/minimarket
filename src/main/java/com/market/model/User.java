package com.market.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Roy on 14/07/2017.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="userid")
    private Long userId;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name ="enabled")
    private int enabled;

    public User(){

    }

    public User(User user) {
        this.userId = user.userId;
        this.userName = user.userName;
        this.password = user.password;
        this.enabled=user.enabled;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
