package com.greenmile.presentation.query.service.user.repository;

import com.greenmile.presentation.query.service.repo.WriteOnlyRepository;
import com.greenmile.presentation.query.service.user.entity.UserEntity;

public interface UserWriteOnlyRepository  extends WriteOnlyRepository<UserEntity, String> {
}
