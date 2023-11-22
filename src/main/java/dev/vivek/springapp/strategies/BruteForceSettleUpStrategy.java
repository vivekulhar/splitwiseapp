package dev.vivek.springapp.strategies;

import dev.vivek.springapp.models.Expense;
import dev.vivek.springapp.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BruteForceSettleUpStrategy implements SettleUpStrategy{

    @Override
    public List<Transaction> settle(List<Expense> expenses) {
        return null;
    }
}
