package com.estacao.ferroviaria.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service; 

import com.estacao.ferroviaria.model.Users;
import com.estacao.ferroviaria.repository.UserRepository;


@Service
public class CustomUserDetailsService  implements UserDetailsService{
	 @Autowired
     private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
          if(user==null){
                 new UsernameNotFoundException("User not exists by Username");
               }
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getNome()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),authorities);
    }
    public String findUsernameByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getNome();
        }
        return null;
    }

}
