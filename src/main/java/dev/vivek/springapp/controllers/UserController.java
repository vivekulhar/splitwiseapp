package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.exception.UserNotFoundException;
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
    public ResponseEntity<UserDto> createUser(@RequestBody() CreateUserRequestDTO requestDTO){

        try {
            UserDto user = userService.createUser(requestDTO.getPhoneNumber(), requestDTO.getUname(), requestDTO.getPwd());

            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch(Exception ex) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/getById/")
    public ResponseEntity<UserDto> getUser(@RequestParam() Long userId){


        try {
            UserDto userDto = userService.getUser(userId);

            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @GetMapping("/getAll")
    public ResponseEntity<List<GetUserResponseDTO>> getAllUsers(){

        List<GetUserResponseDTO> users= userService.getAllUsers();

        return new ResponseEntity(users, HttpStatus.OK);
    }

    @PostMapping("/addUsersToGroup/{groupId}")
    public ResponseEntity<GroupDto> addUsersToGroup(@RequestBody  AddUsersToGroupDto requestDto, @PathVariable(name = "groupId") Long groupId) throws Exception {
        GroupDto responseDTO = userService.addUsersToGroup(requestDto,groupId);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
