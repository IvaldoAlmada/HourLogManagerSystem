package com.greenmile.presentation.query.service.hourlog.entity;

import java.util.Date;

public class WorkedTimeEntity {

    private String userId;
    private Long workedTime;

    public WorkedTimeEntity(String userId, Long workedTime) {
        this.userId = userId;
        this.workedTime = workedTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getWorkedTime() {
        return workedTime;
    }

    public void setWorkedTime(Long workedTime) {
        this.workedTime = workedTime;
    }
}
