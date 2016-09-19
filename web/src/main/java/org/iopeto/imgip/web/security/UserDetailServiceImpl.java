package org.iopeto.imgip.web.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;


@Repository
public class UserDetailServiceImpl implements UserDetailsService  {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {

        if ( !this.username.equals(username) ){
            throw new UsernameNotFoundException("Cannot find user with username " + username);
        }

        return new User(
                this.username,
                this.password,
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("superadmin"))
        );
    }
}