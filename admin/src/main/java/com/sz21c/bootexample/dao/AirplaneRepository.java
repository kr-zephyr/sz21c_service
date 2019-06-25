package com.sz21c.bootexample.dao;

import com.sz21c.bootexample.domain.AirplaneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirplaneRepository extends JpaRepository<AirplaneEntity, Long> {
}
