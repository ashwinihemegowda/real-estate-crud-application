package com.thbs.realestate.repository;

import com.thbs.realestate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {
    public List<Property> findByCategory(String category);
    public List<Property> findByEmail(String email);

}
