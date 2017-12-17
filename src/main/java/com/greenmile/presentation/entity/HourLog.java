package com.greenmile.presentation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greenmile.presentation.entity.type.Action;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "hour_logs")
public class HourLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "action_time")
    private Date actionTime;

    @Column(name = "action")
    @Enumerated(EnumType.STRING)
    private Action action;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
