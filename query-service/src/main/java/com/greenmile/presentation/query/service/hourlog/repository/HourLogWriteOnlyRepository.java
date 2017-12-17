package com.greenmile.presentation.query.service.hourlog.repository;

import com.greenmile.presentation.query.service.hourlog.entity.HourLogEntity;
import com.greenmile.presentation.query.service.repo.WriteOnlyRepository;

public interface HourLogWriteOnlyRepository extends WriteOnlyRepository<HourLogEntity, String> {
}
