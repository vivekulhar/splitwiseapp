package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.dtos.ResponseStatus;
import dev.vivek.springapp.exception.GroupAlreadExistsException;
import dev.vivek.springapp.exception.GroupNotFoundException;
import dev.vivek.springapp.exception.UserAlreadyExistsException;
import dev.vivek.springapp.exception.UserNotFoundException;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.services.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {
    GroupService groupService;
    public GroupController(GroupService groupService){
        this.groupService = groupService;
    }
    public SettleGroupResponseDTO settleGroup(SettleGroupRequestDTO requestDTO){
        SettleGroupResponseDTO responseDTO = new SettleGroupResponseDTO();
        responseDTO.setMessage("Not implemented yet");
        responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        return responseDTO;
    }
    @PostMapping("/create/{userId}")
    public CreateGroupResponseDTO createGroup(@RequestBody CreateGroupRequestDTO requestDTO, @PathVariable(name = "userId") Long userId) {
        CreateGroupResponseDTO responseDTO = new CreateGroupResponseDTO();
        try{
            responseDTO = groupService.createGroup(requestDTO.getName(), requestDTO.getDescription(), userId);
            responseDTO.setMessage("Group created successfully");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(UserAlreadyExistsException ex) {
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return responseDTO;
    }
    @GetMapping("/getById")
    public CreateGroupResponseDTO getGroup(@RequestParam() Long groupId) throws GroupNotFoundException {

        CreateGroupResponseDTO responseDTO = groupService.getGroup(groupId);
        responseDTO.setMessage("Group found successfully");
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }

    @GetMapping("/getAll")
    public List<CreateGroupResponseDTO> getAllGroups(){
        List<CreateGroupResponseDTO> responseDTOS = groupService.getAllGroups();

        return responseDTOS;
    }
}
