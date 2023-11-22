package dev.vivek.springapp.repositories;

import dev.vivek.springapp.models.Expense;
import dev.vivek.springapp.models.User;
import dev.vivek.springapp.models.UserExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserExpenseRepository extends JpaRepository<UserExpense, Long> {
    List<UserExpense> findAllByUser(User user);
    List<UserExpense> findAllByExpenseIn(List<Expense> expenses);
}
