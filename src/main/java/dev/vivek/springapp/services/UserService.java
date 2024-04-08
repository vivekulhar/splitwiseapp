package dev.vivek.springapp.services;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.exception.UserAlreadyExistsException;
import dev.vivek.springapp.exception.UserNotFoundException;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.models.UserStatus;
import dev.vivek.springapp.models.UserType;
import dev.vivek.springapp.repositories.GroupRepository;
import dev.vivek.springapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private UserRepository userRepository;
    private MappingClass mappingClass = new MappingClass();
    private GroupRepository groupRepository;
    @Autowired
    public UserService(UserRepository userRepository, GroupRepository groupRepository){
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
    }

    public UserDto createUser(String phoneNumber, String uname, String pwd) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findByPhone(phoneNumber);

        if (userOptional.isPresent()) {
            if (userOptional.get().getUserStatus().equals(UserStatus.ACTIVE)) {
                throw new UserAlreadyExistsException("User already exists");
            } else {
                User user = userOptional.get();
                user.setUserStatus(UserStatus.ACTIVE);
                user.setPassword(pwd);

                user =  userRepository.save(user);

                return mappingClass.createUserDto(user);
            }
        }

        User user = new User();
        user.setUname(uname);
        user.setPassword(pwd);
        user.setPhone(phoneNumber);
        user.setUserStatus(UserStatus.ACTIVE);
        User savedUser = userRepository.save(user);
        return mappingClass.createUserDto(savedUser);
    }


    public UserDto getUser(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new Exception("User not found");
        } else {
            User user = userOptional.get();
            List<Group> groups = user.getGroups();
            List<GroupDto> groupDtos = new ArrayList<>();

            for(Group group:groups){
                GroupDto groupDto = mappingClass.mapGroupToDto(group);
                groupDtos.add(groupDto);
            }
            user.setGroups(user.getGroups());

            UserDto userDto = mappingClass.createUserDto(user);
            userDto.setGroups(groupDtos);
            return userDto;
        }
    }

    public List<GetUserResponseDTO> getAllUsers() {
        List<User> users =  userRepository.findAll();

        List<GetUserResponseDTO> userResponseDTOList = new ArrayList<>();

        for (User user : users) {
            GetUserResponseDTO userResponseDTO = mappingClass.createUserWithGroupDto(user);
            userResponseDTOList.add(userResponseDTO);
        }

        return userResponseDTOList;
    }

    public GroupDto addUsersToGroup(AddUsersToGroupDto requestDto, Long groupId) throws Exception {
        System.out.println("\nFetch existing User details and assign them to an existing Group." + "\n");
        // create Employee set
        List<User> users = new ArrayList<>();
        // get a Project
        Group group = this.groupRepository.getReferenceById(groupId);
        System.out.println("\nGroup details :: " + group.toString() + "\n");

        // assign Employee Set to Project
        List<User> groupsUsers = group.getUsers();

        for(Long userId:requestDto.getUserId()){
            // get first Employee
            User user2= (this.getUserForAdding(userId));
            user2.getGroups().add(group);
//            user2 = userRepository.save(user2);

            groupsUsers.add(user2);
        }



        group.setUsers(groupsUsers);

        // save Project
        group = groupRepository.save(group);
        GroupDto groupDto = mappingClass.mapGroupToDto(group);
        System.out.println("Users assigned to the Group." + "\n");

        return groupDto;

    }

    private User getUserForAdding(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new Exception("User not found");
        } else {

            return userOptional.get();
        }

    }
}
