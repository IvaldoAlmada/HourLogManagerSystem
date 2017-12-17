package com.greenmile.presentation.query.service.hourlog.repository;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HourLogReadOnlyRepositoryCustom {

    List<HourLogEntity> findByUserIdAndActionTime(String userId, Date startDate, Date endDate);
}
