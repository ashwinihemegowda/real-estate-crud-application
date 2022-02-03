package com.thbs.realestate.repository;

import com.thbs.realestate.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Integer> {

    //Abstract method to get list of property filtered by specified category
    public List<Property> findByCategory(String category);

    //Abstract method to get list of property filtered by specified email
    public List<Property> findByEmail(String email);
    public List<Property> findByCategoryIsStartingWith(String category);

    //Abstract method to get list of property filtered by property name
    public List<Property> findByPropertyNameIsStartingWith(String propertyName);


    public List<Property> findByPriceIsStartingWith(String price);

}
