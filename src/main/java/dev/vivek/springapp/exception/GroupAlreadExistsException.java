package dev.vivek.springapp.exception;

public class GroupAlreadExistsException extends Throwable {
    public GroupAlreadExistsException(String groupAlreadyExists) {
        super(groupAlreadyExists);
    }
}
