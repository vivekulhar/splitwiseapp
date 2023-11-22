package dev.vivek.springapp.services;

import dev.vivek.springapp.exception.UserAlreadyExistsException;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.models.UserStatus;
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

    public User createUser(String phoneNumber, String uname, String pwd) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByPhone(phoneNumber);

        if (userOptional.isPresent()) {
            if (userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                throw new UserAlreadyExistsException("User already exists");
            } else {
                User user = userOptional.get();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setPassword(pwd);
                return userRepository.save(user);
            }
        }

        User user = new User();
        user.setUname(uname);
        user.setPassword(pwd);
        user.setPhone(phoneNumber);
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
