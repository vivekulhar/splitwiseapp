package dev.vivek.springapp.services;

import dev.vivek.springapp.dtos.CreateGroupResponseDTO;
import dev.vivek.springapp.dtos.MappingClass;
import dev.vivek.springapp.exception.*;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.repositories.GroupRepository;
import dev.vivek.springapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    private MappingClass mappingClass = new MappingClass();
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public CreateGroupResponseDTO createGroup(String groupName, String groupDescription, Long userId)
            throws UserAlreadyExistsException, UserNotFoundException {
        // Check if group already exists
        groupRepository.findByName(groupName).ifPresent(group -> {
            try {
                throw new GroupAlreadyExistsException("Group already exists");
            } catch (GroupAlreadyExistsException e) {
                throw new RuntimeException(e);
            }
        });

        // Fetch the user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User does not exist"));

        // Create group
        Group group = new Group();
        group.setName(groupName);
        group.setDescription(groupDescription);
        group.setCreatedBy(user);
        List<User> users = new ArrayList<>();
        users.add(user);
        group.setUsers(users); // Set user as the only member
        group.setCreatedAt(new Date());

        // Save group
        group = groupRepository.save(group);

        // Update user's groups
//        user.getGroups().add(group);
//        userRepository.save(user);

        return mappingClass.createGroupDto(group);
    }

    public CreateGroupResponseDTO getGroup(Long groupId) throws GroupNotFoundException {
        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isPresent()){

            return mappingClass.createGroupDto(groupOptional.get());
        }
        throw  new GroupNotFoundException("Group not found");
    }

    public List<CreateGroupResponseDTO> getAllGroups() {

        List<Group> groups= groupRepository.findAll();

        List<CreateGroupResponseDTO> groupResponseDTOS = new ArrayList<>();

        for(Group group : groups){
            groupResponseDTOS.add(mappingClass.createGroupDto(group));
        }
        return groupResponseDTOS;
    }
}
