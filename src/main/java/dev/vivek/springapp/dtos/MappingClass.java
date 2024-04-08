package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.Expense;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MappingClass {

    public UserDto createUserDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setUname(user.getUname());
        userDto.setPhoneNumber(user.getPhone());
        userDto.setUserStatus(user.getUserStatus());
        userDto.setUserType(user.getUserType());
        return userDto;
    }


//    public GetUserResponseDTO createUserWithGroupDto(User user) {
//        GetUserResponseDTO userResponseDTO = new GetUserResponseDTO();
//
//        List<Group> groupList = new ArrayList<>();
//
//        userResponseDTO.setUname(user.getUname());
//        userResponseDTO.setPhoneNumber(user.getPhone());
//        for(Group group:user.getGroups()){
//            groupList.add(group);
//        }
//
//        userResponseDTO.setGroups(groupList);
//        return userResponseDTO;
//    }
    public GetUserResponseDTO createUserWithGroupDto(User user) {
        GetUserResponseDTO userResponseDTO = new GetUserResponseDTO();

        userResponseDTO.setUname(user.getUname());
        userResponseDTO.setPhoneNumber(user.getPhone());
        List<Group> groups = user.getGroups();
        List<GroupDto> groupDtos = mapGroupsToDto(groups);
        userResponseDTO.setGroups(groupDtos);

        return userResponseDTO;
    }

    // Method to map Group entities to GroupDto objects
    private List<GroupDto> mapGroupsToDto(List<Group> groups) {
        List<GroupDto> groupDtos = new ArrayList<>();
        for (Group group : groups) {
            GroupDto groupDto = new GroupDto();
            // Map relevant attributes from Group entity to GroupDto
            groupDto.setName(group.getName());
            groupDto.setDescription(group.getDescription());
            // Add more mappings as needed
            User user = group.getCreatedBy();
            if(user!=null){
                UserDto userDto = createUserDto(user);
                groupDto.setCreatedBy(userDto);
            }

            groupDtos.add(groupDto);
        }
        return groupDtos;
    }
    public CreateGroupResponseDTO createGroupDto(Group group) {
        CreateGroupResponseDTO groupResponseDTO = new CreateGroupResponseDTO();


        groupResponseDTO.setGroupId(group.getId());
        groupResponseDTO.setGroupDescription(group.getDescription());
        List<User> users = group.getUsers();

        List<UserDto> userDtos = new ArrayList<>();

        for(User user1: users){
            userDtos.add(createUserDto(user1));
        }
        groupResponseDTO.setUsers(userDtos);
        User user = group.getCreatedBy();
        if(user!=null){
            UserDto userDto = createUserDto(user);
            groupResponseDTO.setCreatedBy(userDto);
        }
        return groupResponseDTO;
    }

    public GroupDto mapGroupToDto(Group group) {
        GroupDto groupDto = new GroupDto();

        groupDto.setName(group.getName());
        groupDto.setDescription(group.getDescription());
        User user  = group.getCreatedBy();
        UserDto userDto = createUserDto(user);
        groupDto.setCreatedBy(userDto);

        List<User> users = group.getUsers();
        System.out.println("This is users list" + users);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user1 : users) {
            UserDto userDto1 = createUserDto(user1);
            userDtos.add(userDto1);
        }
        groupDto.setUsers(userDtos);

        return groupDto;
    }

    public ExpenseDto mapExpenseToDto(Expense expense) {
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setName(expense.getName());
        expenseDto.setDescription(expense.getDescription());
        expenseDto.setAmount(expense.getAmount());
        expenseDto.setExpenseType(expense.getExpenseType());
        User user = expense.getCreatedBy();
        if(user!=null){
            UserDto userDto = createUserDto(user);
            expenseDto.setCreatedBy(userDto);
        }
        Group group = expense.getGroup();
        if(group!=null){
            GroupDto groupDto = mapGroupToDto(group);
            expenseDto.setGroup(groupDto);
        }
        return expenseDto;
    }
}
