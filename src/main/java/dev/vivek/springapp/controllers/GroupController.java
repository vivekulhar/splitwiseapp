package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.ResponseStatus;
import dev.vivek.springapp.dtos.SettleGroupRequestDTO;
import dev.vivek.springapp.dtos.SettleGroupResponseDTO;
import org.springframework.stereotype.Controller;

@Controller
public class GroupController {

    public SettleGroupResponseDTO settleGroup(SettleGroupRequestDTO requestDTO){
        SettleGroupResponseDTO responseDTO = new SettleGroupResponseDTO();
        responseDTO.setMessage("Not implemented yet");
        responseDTO.setResponseStatus(ResponseStatus.FAILURE);
        return responseDTO;
    }
}
