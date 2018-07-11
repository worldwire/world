package com.spring.worldwire.model;

import java.io.Serializable;

public class AdminUser implements Serializable{
    private static final long serialVersionUID = -12231136390267238L;

    private Long id;

    private String userName;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

	@Override
	public String toString() {
		return "AdminUser [id=" + id + ", userName=" + userName + ", password=" + password + "]";
	}
    
}