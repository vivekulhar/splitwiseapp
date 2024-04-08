package dev.vivek.springapp.exception;

public class GroupAlreadyExistsException extends Throwable {
    public GroupAlreadyExistsException(String groupAlreadyExists) {
        super(groupAlreadyExists);
    }
}
