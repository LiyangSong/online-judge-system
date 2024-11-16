package com.liyang.onlinejudgesystem.security;

import com.liyang.onlinejudgesystem.dto.RegisterDto;
import com.liyang.onlinejudgesystem.enums.UserRole;
import com.liyang.onlinejudgesystem.repository.UserRepository;
import com.liyang.onlinejudgesystem.entity.User;
import io.jsonwebtoken.JwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getUserRole().toString());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getUserPassword(),
                authorities
        );
    }

    public UserDetails register(RegisterDto registerDto, String encryptedPassword)  throws JwtException {
        if (userRepository.findByUsername(registerDto.username()).isPresent()) {
            throw new JwtException("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(registerDto.username());
        newUser.setUserPassword(encryptedPassword);
        newUser.setUserRole(UserRole.valueOf(registerDto.userRole()));
        newUser.setCreateTime(new Date());
        newUser.setUpdateTime(new Date());
        newUser.setIsDelete(false);

        User savedUser = userRepository.save(newUser);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(savedUser.getUserRole().toString());
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new org.springframework.security.core.userdetails.User(
                savedUser.getUsername(),
                savedUser.getUserPassword(),
                authorities
        );
    }
}
