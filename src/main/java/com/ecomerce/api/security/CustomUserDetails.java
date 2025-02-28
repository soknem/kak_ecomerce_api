package com.ecomerce.api.security;

import com.ecomerce.api.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CustomUserDetails implements UserDetails {


    private User user;
    private String rawPassword;

    public CustomUserDetails(User user, String rawPassword) {
        this.rawPassword = rawPassword;
        this.user = user;
    }

    public String getRole(){
        return user.getRole().getRoleName();
    }


    public String getUserUuid() {
        return user.getUuid();
    }

    public String getUserUsername() {
        return user.getUsername();
    }


    // make the proper format for the authorities
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        // create a list of authorities
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return authorities;
    }


    @Override
    public String getPassword() {
        return user.getPassword();
    }


    @Override
    public String getUsername() {
        return user.getEmail();
    }


    // will add it tmr!
    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }


    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }


    @Override
    public boolean isEnabled() {
        return !user.getIsDeleted();
    }


}