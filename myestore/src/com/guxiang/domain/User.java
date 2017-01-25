/**
 * author:guxiang
 * create on 2017 2017年1月15日 下午12:23:48
 */
package com.guxiang.domain;

public class User {
   private int id;
   private String username;
   private String password;
   private String email;
   private String nickname;
   
   private String role;
   private String activecode;
   private int status;

/**
 * @return the activecode
 */
public String getActivecode() {
	return activecode;
}
/**
 * @param activecode the activecode to set
 */
public void setActivecode(String activecode) {
	this.activecode = activecode;
}
/**
 * @return the status
 */
public int getStatus() {
	return status;
}
/**
 * @param status the status to set
 */
public void setStatus(int status) {
	this.status = status;
}
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @return the username
 */
public String getUsername() {
	return username;
}
/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}
/**
 * @return the password
 */
public String getPassword() {
	return password;
}
/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}
/**
 * @return the email
 */
public String getEmail() {
	return email;
}
/**
 * @param email the email to set
 */
public void setEmail(String email) {
	this.email = email;
}
/**
 * @return the nickname
 */
public String getNickname() {
	return nickname;
}
/**
 * @param nickname the nickname to set
 */
public void setNickname(String nickname) {
	this.nickname = nickname;
}
/**
 * @return the role
 */
public String getRole() {
	return role;
}
/**
 * @param role the role to set
 */
public void setRole(String role) {
	this.role = role;
} 
   
}
