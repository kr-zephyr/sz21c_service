package com.sz21c.admin.dao;

import com.sz21c.admin.domain.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<AirplaneEntity, Long> {
}
