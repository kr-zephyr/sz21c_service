package com.sz21c.bootexample.dao;

import com.sz21c.bootexample.domain.ManufactureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufactureRepository extends JpaRepository<ManufactureEntity, Long> {
	ManufactureEntity findByName(String name);
}
