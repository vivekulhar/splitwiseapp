package dev.vivek.springapp.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserResponseDTO {
    private String phoneNumber;
    private String uname;

    private ResponseStatus responseStatus;
    private String message;

    @Override
    public String toString() {
        return "GetUserResponseDTO{" +
                "name='" + phoneNumber + '\'' +
                ", uname='" + uname + '\'' +
                ", responseStatus=" + responseStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
