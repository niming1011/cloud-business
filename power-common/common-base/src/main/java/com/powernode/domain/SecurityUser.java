package com.powernode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityUser implements UserDetails {
    //loginSysUser信息
    private Long userId;
    private String username;
    private String password;
    private Integer status;
    private Long shopId;
    private String loginType;
    private Set<String> perms;
    //loginMemberUser信息


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities= new ArrayList<>();
        this.perms.forEach((permission)->{
            authorities.add(new SimpleGrantedAuthority(permission));
        });

        return authorities;
    }

    @Override
    public @Nullable String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.loginType+this.userId;
    }

    @Override
    public boolean isEnabled() {
        return this.status==1;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.status==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.status==1;
    }

    public Set<String> getPerms() {
        Set<String> finalPerms = new HashSet<String>();
        this.perms.forEach(perm->{
            if(perm.contains(",")){
                String[] split = perm.split(",");
                for (String s : split) {
                    finalPerms.add(s);
                }
            }else{
                finalPerms.add(perm);
            }
        });
        return finalPerms;
    }
}
