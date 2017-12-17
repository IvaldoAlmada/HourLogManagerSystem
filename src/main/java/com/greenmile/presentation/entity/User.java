package com.greenmile.presentation.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<HourLog> hourLog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<HourLog> getHourLog() {
        return hourLog;
    }

    public void setHourLog(Set<HourLog> hourLog) {
        this.hourLog = hourLog;
    }
}
