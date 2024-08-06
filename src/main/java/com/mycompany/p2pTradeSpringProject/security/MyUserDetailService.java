package com.mycompany.p2pTradeSpringProject.security;

import com.mycompany.p2pTradeSpringProject.persistence.daointerfaces.IDAOUser;
import com.mycompany.p2pTradeSpringProject.persistence.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    IDAOUser daoUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = daoUser.findByUsername(username);

        MyUserDetails myUserDetails = user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        Logger.getGlobal().info("Username: " + username + " Password: " + myUserDetails.getPassword() );
        return myUserDetails;
    }

}
