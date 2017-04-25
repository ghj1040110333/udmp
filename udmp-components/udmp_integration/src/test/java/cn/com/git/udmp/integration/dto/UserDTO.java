package cn.com.git.udmp.integration.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;
	
	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    private String index;
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
