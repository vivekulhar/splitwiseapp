package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SettleUpUserResponseDto {
    private String phoneNumber;
    private String uname;

    private ResponseStatus responseStatus;
    private String message;
    List<Transaction> transactions;
    @Override
    public String toString() {
        return "SettleUpUserResponseDto{" +
                "name='" + phoneNumber + '\'' +
                ", uname='" + uname + '\'' +
                ", responseStatus=" + responseStatus +
                ", message='" + message + '\'' +
                '}';
    }

}
