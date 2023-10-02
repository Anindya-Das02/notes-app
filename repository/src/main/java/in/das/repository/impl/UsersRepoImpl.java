package in.das.repository.impl;

import in.das.entity.Users;
import in.das.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsersRepoImpl {

    @Autowired
    private UserRepository userRepository;

    public Users saveUsers(final Users users){
        return userRepository.save(users);
    }

    public Optional<Users> findUserByUsername(final String username){
        return userRepository.findByUsername(username);
    }


}
