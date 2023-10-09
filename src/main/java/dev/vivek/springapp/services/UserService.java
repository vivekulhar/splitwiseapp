package dev.vivek.springapp.services;

import dev.vivek.springapp.models.User;
import dev.vivek.springapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User createUser(String name, String uname, String pwd){
        User user = new User();
        user.setName(name);
        user.setUname(uname);
        user.setPassword(pwd);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User getUser(Long userId) throws Exception {
        //return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new Exception("User not found");
        } else {
            return userOptional.get();
        }
    }
}
