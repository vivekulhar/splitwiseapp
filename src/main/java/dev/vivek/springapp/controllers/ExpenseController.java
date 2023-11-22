package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.models.Transaction;
import dev.vivek.springapp.services.ExpenseService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto request) {
        List<Transaction> transactions = expenseService
                .settleUpUser(request.getUserId());


        SettleUpUserResponseDto response = new SettleUpUserResponseDto();
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setTransactions(transactions);

        return response;
    }

    public SettleGroupResponseDTO settleUpGroup(SettleGroupRequestDTO request) {
        return null;
    }
}
