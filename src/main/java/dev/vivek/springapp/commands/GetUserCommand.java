package dev.vivek.springapp.commands;

import dev.vivek.springapp.controllers.UserController;
import dev.vivek.springapp.dtos.GetUserRequestDTO;
import dev.vivek.springapp.dtos.GetUserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserCommand implements Command{
    private UserController userController;

    @Autowired
    public GetUserCommand(UserController userController){
        this.userController = userController;
    }

    @Override
    public boolean canExecute(String input){
        if(!input.startsWith("get-user")){
            return false;
        }

        if(input.split(" ").length!=2){
            return false;
        }
        return true;
    }

    @Override
    public void execute(String input){
        String[] parts = input.split(" ");
        GetUserRequestDTO requestDTO = new GetUserRequestDTO();
        requestDTO.setUserId(Long.parseLong(parts[1]));

        GetUserResponseDTO responseDTO = userController.getUser(Long.parseLong(parts[1]));
        System.out.println(responseDTO);
    }
}
