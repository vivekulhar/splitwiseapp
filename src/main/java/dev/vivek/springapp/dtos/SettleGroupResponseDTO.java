package dev.vivek.springapp.dtos;

import dev.vivek.springapp.models.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class SettleGroupResponseDTO {
    private List<Transaction> transactions;
    private ResponseStatus responseStatus;
    private String message;
}
