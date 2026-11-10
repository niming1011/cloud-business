package com.powernode.strategy.impl;

import com.powernode.strategy.LoginStrategy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("memberLogin")
public class MemberLoginStrategy implements LoginStrategy {
    @Override
    public UserDetails realLogin(String username) {

        return null;
    }
}
