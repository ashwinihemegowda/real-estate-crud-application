package com.thbs.realestate.service;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceInterfaceImpl implements ViewServiceInterface{
    @Autowired
    PropertyRepository repo;


    public List<Property> listAll(){
        return (List<Property>) repo.findAll();
    }
    //for rent
    public List<Property> getPropertyByRent(){
        return repo.findByCategory("rent");
    }
    //Land
    public List<Property> getPropertyByLand(){
        return repo.findByCategory("land");
    }
    // Villa
    public List<Property> getPropertyByVilla(){
        return repo.findByCategory("villa");
    }
    // apartment
    public List<Property> getPropertyByApartment(){
        return repo.findByCategory("Apartment");
    }

    public Property getDetailsById1(Integer id) {
        return repo.findById(id).get();
    }

}
