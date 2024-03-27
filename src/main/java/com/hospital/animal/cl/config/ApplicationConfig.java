package com.hospital.animal.cl.config;

import com.hospital.animal.cl.services.firebase.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.concurrent.ExecutionException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Autowired
    UserServiceImpl userService;
    @Bean
    public UserDetailsService userDetailsService(){
        try{
            return username -> {
                try {
                    return userService.getByEmail(username);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };
        }catch (UsernameNotFoundException e){
            return username -> null;
        }
    }

}
