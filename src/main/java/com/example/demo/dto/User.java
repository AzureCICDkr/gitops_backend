package com.example.demo.dto;
import java.io.Serializable;


public class User implements Serializable{

    private Long userId;
    private String email;
    private String name;
    private String picture;
    private String provider;
    private String role = "ROLE_USER";
    private boolean enabled = true;


  public User(){}

   public User(String name, boolean isEnabled ){
       this.email = name;
       this.enabled = isEnabled;
   
   }


    // Getter/Setter
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPicture() { return picture; }
    public void setPicture(String picture) { this.picture = picture; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}

