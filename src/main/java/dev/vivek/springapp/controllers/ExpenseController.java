package dev.vivek.springapp.controllers;

import dev.vivek.springapp.dtos.*;
import dev.vivek.springapp.models.Transaction;
import dev.vivek.springapp.services.ExpenseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }
    @PostMapping("/api/settleUpUser")
    public SettleUpUserResponseDto settleUpUser(@RequestBody SettleUpUserRequestDto request) {
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

    @PostMapping("/api/addExpense")
    public AddExpenseResponseDto addExpense(@RequestBody AddExpenseRequestDto request) {
        Transaction transaction = expenseService.addExpense(request.getGroupId(), request.getPaidBy(), request.getSplitType(), request.getAmount(), request.getSplitDetails());

        AddExpenseResponseDto response = new AddExpenseResponseDto();
        response.setResponseStatus(ResponseStatus.SUCCESS);
        response.setTransaction(transaction);

        return response;
    }


}
