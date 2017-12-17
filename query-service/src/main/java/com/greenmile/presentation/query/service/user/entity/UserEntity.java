package com.greenmile.presentation.query.service.user.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity() {}

    public UserEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
