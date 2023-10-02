package in.das.security.service;

import in.das.entity.Account;
import in.das.entity.Users;
import in.das.repository.impl.AccountRepoImpl;
import in.das.repository.impl.UsersRepoImpl;
import in.das.security.exceptions.JwtAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DbUserDetailService implements UserDetailsService {

    @Autowired
    private UsersRepoImpl usersRepoImpl;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepoImpl.findUserByUsername(username)
                .orElseThrow(() -> new JwtAuthenticationException("Authentication Error! No user record found during jwt authentication"));
        log.info("db fetched from users : account-id:{} username:{}",users.getId(), users.getUsername());
        return users;
    }
}
