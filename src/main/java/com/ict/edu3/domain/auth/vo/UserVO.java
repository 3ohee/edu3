package com.ict.edu3.domain.auth.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class UserVO implements UserDetails {
    private String m_id = "";
    private String m_pw = "";
    private String email = "";
    private String phone = "";
    private String name = "";
    private String provider = "";
    private String kakao = "";
    private String naver = "";
    private String google = "";

    private List<GrantedAuthority> authorities = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return m_pw;
    }

    @Override
    public String getUsername() {
       return m_id;
    }
    
}