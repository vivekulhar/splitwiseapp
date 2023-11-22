package dev.vivek.springapp.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseModel{
    private User from;
    private User to;
    private int amount;

    @Override
    public String toString() {
        return from + " should pay " + amount + " to " + to + "\n";
    }

    public Transaction(User from, User to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
