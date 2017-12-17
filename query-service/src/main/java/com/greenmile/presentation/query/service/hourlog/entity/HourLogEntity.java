package com.greenmile.presentation.query.service.hourlog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "hourLogs")
public class HourLogEntity {

    private String id;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date actionTime;
    private String action;
    private String userId;

    public HourLogEntity() {

    }

    public HourLogEntity(String id, Date actionTime, String action, String userId) {
        this.id = id;
        this.actionTime = actionTime;
        this.action = action;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
