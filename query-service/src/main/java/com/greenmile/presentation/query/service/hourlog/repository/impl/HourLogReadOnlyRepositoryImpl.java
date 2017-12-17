package com.greenmile.presentation.query.service.hourlog.repository.impl;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.hourlog.repository.HourLogReadOnlyRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

public class HourLogReadOnlyRepositoryImpl implements HourLogReadOnlyRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<HourLogEntity> findByUserIdAndActionTime(String userId, Date startDate, Date endDate) {
        Query query = new Query(Criteria.where("userId").is(userId).andOperator(Criteria.where("actionTime").gt(startDate),
                Criteria.where("actionTime").lt(endDate)));
        return mongoTemplate.find(query, HourLogEntity.class);
    }
}
