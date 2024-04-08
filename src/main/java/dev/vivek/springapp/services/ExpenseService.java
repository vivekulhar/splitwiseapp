package dev.vivek.springapp.services;

import dev.vivek.springapp.dtos.AddExpenseRequestDto;
import dev.vivek.springapp.dtos.ExpenseDto;
import dev.vivek.springapp.dtos.MappingClass;
import dev.vivek.springapp.models.*;
import dev.vivek.springapp.repositories.ExpenseRepository;
import dev.vivek.springapp.repositories.GroupRepository;
import dev.vivek.springapp.repositories.UserExpenseRepository;
import dev.vivek.springapp.repositories.UserRepository;
import dev.vivek.springapp.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private UserRepository userRepository;
    private UserExpenseRepository userExpenseRepository;

    private SettleUpStrategy settleUpStrategy;
    private GroupRepository groupRepository;
    private ExpenseRepository expenseRepository;
    private MappingClass mappingClass = new MappingClass();
    @Autowired
    public ExpenseService(UserRepository userRepository,
                          UserExpenseRepository userExpenseRepository,
                          @Qualifier("twoSetsSettleUpStrategy") SettleUpStrategy settleUpStrategy,
                          GroupRepository groupRepository,
                          ExpenseRepository expenseRepository) {
        this.userRepository = userRepository;
        this.userExpenseRepository = userExpenseRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
    }

    public List<Transaction> settleUpUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            // throw Exception
            return null;
        }

        List<UserExpense> userExpenses = userExpenseRepository.findAllByUser(userOptional.get());

        List<Expense> expensesInvolvingUser = new ArrayList<>();
        for (UserExpense userExpense: userExpenses) {
            expensesInvolvingUser.add(userExpense.getExpense());
        }

        List<Transaction> transactions = settleUpStrategy.settle(expensesInvolvingUser);

        List<Transaction> filteredTransactions = new ArrayList<>();

        for (Transaction transaction: transactions) {
            if (transaction.getFrom().equals(userOptional.get()) || transaction.getTo().equals(userOptional.get())) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public List<Transaction> settleUpGroup(Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);

        if (groupOptional.isEmpty()) {
            // throw nexception
            return null;
        }

        List<Expense> expenses = expenseRepository.findAllByGroup(groupOptional.get());

        List<Transaction> transactions = settleUpStrategy.settle(
                expenses
        );

        return transactions;
    }

    public ExpenseDto addExpense(AddExpenseRequestDto requestDto) {

        Expense expense = new Expense();
        expense.setName(requestDto.getName());
        expense.setDescription(requestDto.getDescription());
        expense.setAmount(requestDto.getAmount());
        Optional<User> user1 = userRepository.findById(Long.parseLong(requestDto.getCreatedBy()));
        if(user1.isPresent()){
            expense.setCreatedBy(user1.get());
        }
        Optional<Group> group = groupRepository.findById(Long.parseLong(requestDto.getGroupId()));
        if(group.isPresent()){
            expense.setGroup(group.get());
        }
        expense.setCreatedAt(new Date());
        expense.setExpenseType(requestDto.getExpenseType());

        // Set other properties like date, expense type, etc.

        // Save the expense
        expense = expenseRepository.save(expense);

        // Create corresponding UserExpense entries
        for (User user : group.get().getUsers()) {
            // Calculate each user's share or contribution based on the split type
            int userAmount = calculateUserAmount(user, group.get().getUsers().size(), requestDto.getAmount(), requestDto.getSplitType());
            // Determine the user expense type (paid or owes)
            UserExpenseType userExpenseType = determineUserExpenseType(user, expense.getCreatedBy(), requestDto.getSplitType());
            // Save the UserExpense entry
            saveUserExpense(user, expense, userAmount, userExpenseType);
        }
        ExpenseDto expenseDto = mappingClass.mapExpenseToDto(expense);
        return expenseDto;
    }
    public void saveUserExpense(User user, Expense expense, int userAmount, UserExpenseType userExpenseType) {
        UserExpense userExpense = new UserExpense();
        userExpense.setUser(user);
        userExpense.setExpense(expense);
        userExpense.setAmount(userAmount);
        userExpense.setUserExpenseType(userExpenseType);

        // Save the user expense entity to the repository
        userExpenseRepository.save(userExpense);
    }
    private UserExpenseType determineUserExpenseType(User user, User createdBy, SplitType splitType) {
        if (user.equals(createdBy)) {
            // If the user is the one who created the expense, consider it as paid
            return UserExpenseType.PAID;
        } else {
            // Otherwise, determine based on the split type
            switch (splitType) {
                case EQUAL_SPLIT:
                    // For equal split, everyone owes an equal share except the creator
                    return UserExpenseType.HAD_TO_PAY;
//                case PERCENTAGE_SPLIT:
//                    // For percentage split, check if the user is one of the payers
//                    if (user.getPaidBy().contains(createdBy)) {
//                        return UserExpenseType.PAID;
//                    } else {
//                        return UserExpenseType.HAD_TO_PAY;
//                    }
                default:
                    // Handle other split types if necessary
                    return UserExpenseType.HAD_TO_PAY;
            }
        }
    }
    public int calculateUserAmount(User user, int totalUsers, int totalAmount, SplitType splitType) {
        int userAmount = 0;

        switch (splitType) {
            case EQUAL_SPLIT:
                userAmount = totalAmount / totalUsers;
                break;
            case PERCENTAGE_SPLIT:
                // You need to handle percentage split separately based on the split details
                // For simplicity, let's assume each user's percentage is equal
                int userPercentage = 100 / totalUsers;
                userAmount = (totalAmount * userPercentage) / 100;
                break;
//            case CUSTOM_SPLIT:
//                // You need to parse the split details and calculate the user's amount accordingly
//                // For simplicity, let's assume all users share the cost equally in custom split
//                userAmount = totalAmount / totalUsers;
//                break;
        }

        return userAmount;
    }
    // Method to parse split details for different types
    public static void parseSplitDetails(String splitType, String splitDetails) {
        switch (splitType) {
            case "EQUAL_SPLIT":
                // For equal split, only need the total amount and number of users
                System.out.println("Equal Split: Total amount is " + splitDetails);
                break;
            case "PERCENTAGE_SPLIT":
                // For percentage split, extract percentages for each user
                String[] percentages = splitDetails.split(",");
                for (String percentage : percentages) {
                    String[] parts = percentage.trim().split(":");
                    String user = parts[0].trim();
                    String percent = parts[1].trim();
                    System.out.println("User: " + user + ", Percentage: " + percent);
                }
                break;
            case "CUSTOM_SPLIT":
                // For custom split, extract specified amounts for each user
                String[] customAmounts = splitDetails.split(",");
                for (String amount : customAmounts) {
                    String[] parts = amount.trim().split(":");
                    String user = parts[0].trim();
                    String amountValue = parts[1].trim();
                    System.out.println("User: " + user + ", Amount: " + amountValue);
                }
                break;
            default:
                System.out.println("Unknown split type.");
        }
    }
}
