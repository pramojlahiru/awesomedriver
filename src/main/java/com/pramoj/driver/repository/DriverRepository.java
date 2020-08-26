package com.pramoj.driver.repository;

import com.pramoj.driver.model.DriverEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends CrudRepository<DriverEntity, Long> {

}