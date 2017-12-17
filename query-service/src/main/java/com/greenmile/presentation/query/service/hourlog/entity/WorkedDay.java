package com.greenmile.presentation.query.service.hourlog.entity;

import java.util.Date;

public class WorkedDay {

    private Date inDate;
    private Date outDate;

    public Date getInDate() {
        return inDate;
    }

    public void setInDate(Date inDate) {
        this.inDate = inDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
