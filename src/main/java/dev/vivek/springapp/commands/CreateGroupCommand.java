package dev.vivek.springapp.commands;

import dev.vivek.springapp.controllers.GroupController;
import dev.vivek.springapp.dtos.CreateGroupRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class CreateGroupCommand implements Command{
    GroupController groupController;
    public CreateGroupCommand(GroupController groupController){
        this.groupController = groupController;
    }
    @Override
    public boolean canExecute(String input) {
        return input.startsWith("create_group");
    }

    @Override
    public void execute(String input) {
        String[] tokens = input.split(" ");
        CreateGroupRequestDTO requestDTO = new CreateGroupRequestDTO();
        requestDTO.setGroupName(tokens[1]);
        requestDTO.setGroupDescription(tokens[2]);
        requestDTO.setUserIds(tokens[3].split(","));

        groupController.createGroup(requestDTO);
    }
}
