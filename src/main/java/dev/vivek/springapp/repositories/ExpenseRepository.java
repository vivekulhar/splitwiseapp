package dev.vivek.springapp.repositories;

import dev.vivek.springapp.models.Expense;
import dev.vivek.springapp.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findAllByGroup(Group group);
}
