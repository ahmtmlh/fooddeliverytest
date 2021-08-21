package com.test.fooddeliverytest.service;

import com.test.fooddeliverytest.dao.UserRepository;
import com.test.fooddeliverytest.model.User;
import com.test.fooddeliverytest.model.UserData;
import com.test.fooddeliverytest.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> userDetails(String username){
        return Optional.ofNullable(userRepository.findByUsername(username));
    }

    public boolean userExist(String username){
        return userDetails(username).isPresent();
    }

    public Optional<User> createUser(String username, String password){
        return Optional.of(userRepository.save(new User(User.UserType.NORMAL, username, password)));
    }

    public Optional<User> createUserWithData(String username, String password, UserData userData){
        User user = new User(User.UserType.NORMAL, username, password);
        user.setUserData(userData);
        return Optional.of(userRepository.save(user));
    }

    public Optional<User> createAdmin(String username, String password){
        return Optional.of(userRepository.save(new User(User.UserType.ADMIN, username, password)));
    }

    public UserDetails getUserDetailsById(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow( () -> new UsernameNotFoundException("User not found!") );

        return UserPrincipal.create(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDetails(username);
        user.orElseThrow(() -> new UsernameNotFoundException("Login failed"));
        return UserPrincipal.create(user.get());
    }

}
