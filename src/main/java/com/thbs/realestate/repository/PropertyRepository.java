package com.thbs.realestate.repository;

import com.thbs.realestate.model.Property;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropertyRepository extends CrudRepository<Property, Integer> {
    public List<Property> findByCategory(String category);

}
