package dev.vivek.springapp.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction extends BaseModel{
    private String from;
    private String to;
    private int amount;

    @Override
    public String toString() {
        return from + " should pay " + amount + " to " + to + "\n";
    }

    public Transaction(String from, String to, int amount){
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
