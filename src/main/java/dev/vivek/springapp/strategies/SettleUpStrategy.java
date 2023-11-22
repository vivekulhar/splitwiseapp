package dev.vivek.springapp.strategies;

import dev.vivek.springapp.models.Expense;
import dev.vivek.springapp.models.Transaction;

import java.util.List;

public interface SettleUpStrategy {
    List<Transaction> settle(List<Expense> expenses);
}
