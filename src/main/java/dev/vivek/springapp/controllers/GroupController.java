package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.dtos.ResponseStatus;
import dev.vivek.springapp.exception.GroupAlreadExistsException;
import dev.vivek.springapp.exception.UserAlreadyExistsException;
import dev.vivek.springapp.models.Group;
import dev.vivek.springapp.services.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/create")
    public CreateGroupResponseDTO createGroup(CreateGroupRequestDTO requestDTO) {
        CreateGroupResponseDTO responseDTO = new CreateGroupResponseDTO();
        try{
            Group savedGroup = groupService.createGroup(requestDTO.getGroupName(), requestDTO.getGroupDescription(), requestDTO.getUserIds());
            responseDTO.setGroupId(savedGroup.getId());
            responseDTO.setMessage("Group created successfully");
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(GroupAlreadExistsException ex) {
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        } catch(UserAlreadyExistsException ex) {
            responseDTO.setMessage(ex.getMessage());
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }
    @GetMapping("/getById")
    public GetGroupResponseDTO getGroup(@RequestParam() Long groupId){
        Group group = groupService.getGroup(groupId);
        GetGroupResponseDTO responseDTO = new GetGroupResponseDTO();
        responseDTO.setGroup(group);
        responseDTO.setMessage("Group found successfully");
        responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }
}
