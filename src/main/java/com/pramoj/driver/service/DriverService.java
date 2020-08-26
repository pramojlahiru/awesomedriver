package com.pramoj.driver.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.pramoj.driver.model.DriverEntity;
import com.pramoj.driver.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pramoj.driver.exception.RecordNotFoundException;

@Service
public class DriverService {

    @Autowired
    DriverRepository repository;

    public List<DriverEntity> getAllDrivers() {
        List<DriverEntity> result = (List<DriverEntity>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return new ArrayList<DriverEntity>();
        }
    }

    public DriverEntity getDriverById(Long id) throws RecordNotFoundException {
        Optional<DriverEntity> driver = repository.findById(id);

        if (driver.isPresent()) {
            return driver.get();
        } else {
            throw new RecordNotFoundException("No driver record exist for given id");
        }
    }

    public DriverEntity createOrUpdateDriver(DriverEntity entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<DriverEntity> driver = repository.findById(entity.getId());

            if (driver.isPresent()) {
                DriverEntity newEntity = driver.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteDriverById(Long id) throws RecordNotFoundException {
        Optional<DriverEntity> driver = repository.findById(id);

        if (driver.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No driver record exist for given id");
        }
    }
}
