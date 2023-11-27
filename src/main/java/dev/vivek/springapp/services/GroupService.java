package dev.vivek.springapp.services;

import dev.vivek.springapp.exception.GroupAlreadExistsException;
import dev.vivek.springapp.exception.UserAlreadyExistsException;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.repositories.GroupRepository;
import dev.vivek.springapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group createGroup(String groupName, String groupDescription, String[] userIds) throws GroupAlreadExistsException, UserAlreadyExistsException{
        Optional<Group> groupOptional = groupRepository.findByName(groupName);
        if (groupOptional.isPresent()) {
            throw new GroupAlreadExistsException("Group already exists");
        }
        Group group = new Group();
        group.setName(groupName);
        group.setDescription(groupDescription);
        List<User> groupMembers = new ArrayList<>();
        for(String userId:userIds){
            Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));
            if(!userOptional.isPresent()){
                throw new UserAlreadyExistsException("User not found");
            }
            groupMembers.add(userOptional.get());
        }
        group.setGroup_members(groupMembers);
        groupRepository.save(group);
        return group;
    }

    public Group getGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()){
            return null;
        } else {
            return groupOptional.get();
        }
    }
}
