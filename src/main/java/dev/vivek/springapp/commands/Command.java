package dev.vivek.springapp.commands;

public interface Command {
    boolean canExecute(String input);
    void execute(String input);
}
