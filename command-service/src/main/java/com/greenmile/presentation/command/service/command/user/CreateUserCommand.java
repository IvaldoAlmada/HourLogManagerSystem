package com.greenmile.presentation.command.service.command.user;

public class CreateUserCommand extends AbstractUserCommand<String> {
    private final String name;

    public CreateUserCommand(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
