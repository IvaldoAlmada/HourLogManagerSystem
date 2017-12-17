package com.greenmile.presentation.command.service.command.user;

public enum UserCommandFactory {
    INSTANCE;

    public AbstractUserCommand<String> newCreateUserCommand(String id, String name) {
        return new CreateUserCommand(id, name);
    }

}
