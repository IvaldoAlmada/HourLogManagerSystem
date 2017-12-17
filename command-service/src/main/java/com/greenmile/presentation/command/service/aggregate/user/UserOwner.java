package com.greenmile.presentation.command.service.aggregate.user;

public class UserOwner {
    private String name;

    public UserOwner() {}

    public UserOwner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
