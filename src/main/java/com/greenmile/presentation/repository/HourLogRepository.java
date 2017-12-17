package com.greenmile.presentation.repository;

import com.greenmile.presentation.entity.HourLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HourLogRepository extends CrudRepository<HourLog, Long> {

    List<HourLog> findByUserId(int userId);

}
