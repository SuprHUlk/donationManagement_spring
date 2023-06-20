package com.project.donationmanagement.service;

import com.project.donationmanagement.entity.JwtRequest;
import com.project.donationmanagement.entity.JwtResponse;
import com.project.donationmanagement.repository.UserRepository;
import com.project.donationmanagement.util.JwtUtil;
import com.project.donationmanagement.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName=jwtRequest.getUserName();
        String pass=jwtRequest.getPass();
        authenticate(userName, pass);
        UserDetails userDetails= loadUserByUsername(userName);
        String newGeneratedToken=jwtUtil.generateToken(userDetails);
        User user=userRepository.findById(userName).get();

        return new JwtResponse(user, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        com.project.donationmanagement.entity.User user=userRepository.findById(userName).get();
        if(user!=null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getPass(),
                    getAuthority(user)
            );
        }
        else {
            throw new UsernameNotFoundException("Username not found"+userName);
        }
    }

    private Set getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities=new HashSet<>();
        user.getRole().forEach(role->{
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String pass) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, pass));
        }
        catch (DisabledException e) {
            throw new Exception("User is disabled");
        }
        catch (BadCredentialsException e) {
            throw new Exception("Bad credentials from user");
        }
    }
}
