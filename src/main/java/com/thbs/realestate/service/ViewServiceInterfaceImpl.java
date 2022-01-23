package com.thbs.realestate.service;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceInterfaceImpl implements ViewServiceInterface{
    @Autowired
    PropertyRepository propertyRepository;

    //rent
    @Override
    public List<Property> getPropertyByRent() {
        return propertyRepository.findByCategory("rent");
    }

    //land
    @Override
    public List<Property> getPropertyByLand() {
        return propertyRepository.findByCategory("land");
    }

    //villa
    @Override
    public List<Property> getPropertyByVilla() {
        return propertyRepository.findByCategory("villa");
    }

    //apartment
    @Override
    public List<Property> getPropertyByApartment() {
        return propertyRepository.findByCategory("apartment");
    }

    //details of property by id
    @Override
    public Property getDetailsById1(Integer propertyId) {
        return propertyRepository.findById(propertyId).get();
    }


    public List<Property> getPropertyByEmail(String email){
        return propertyRepository.findByEmail(email);
    }

}
