package com.greenmile.presentation.query.service.hourlog.repository;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.repo.ReadOnlyRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HourLogReadOnlyRepository extends ReadOnlyRepository<HourLogEntity, String>, HourLogReadOnlyRepositoryCustom {

}
