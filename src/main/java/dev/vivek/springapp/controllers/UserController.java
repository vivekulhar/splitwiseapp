package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.dtos.ResponseStatus;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/create/")
    public CreateUserResponseDTO createUser(@RequestBody() CreateUserRequestDTO requestDTO){
        CreateUserResponseDTO responseDTO = new CreateUserResponseDTO();

        try {
            User savedUser = userService.createUser(requestDTO.getPhoneNumber(), requestDTO.getUname(), requestDTO.getPwd());
            responseDTO.setMessage("User created successfully");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setUserId(savedUser.getId());
        } catch(Exception ex) {
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }

    @GetMapping("/getById/")
    public GetUserResponseDTO getUser(@RequestParam() Long userId){
        GetUserResponseDTO responseDTO = new GetUserResponseDTO();

        try {
            User user = userService.getUser(userId);
            responseDTO.setMessage("User found successfully");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);

            responseDTO.setPhoneNumber(user.getPhone());
            responseDTO.setUname(user.getUname());
        } catch(Exception ex) {
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }

        return responseDTO;
    }

    @GetMapping("/getAll")
    public ResponseEntity hello(){

        List<User> users= userService.getAllUsers();

        return new ResponseEntity(users, HttpStatus.OK);
    }
}
