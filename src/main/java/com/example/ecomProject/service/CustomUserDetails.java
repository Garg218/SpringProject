package com.example.ecomProject.service;

import com.example.ecomProject.model.UserPrincipal;
import com.example.ecomProject.model.Users;
import com.example.ecomProject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetails implements UserDetailsService {

    @Autowired
    private UserRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if(user == null)
        { System.out.println("User Not Found");

        throw new UsernameNotFoundException("user not found");}




        return new UserPrincipal(user);

    }
}
