package com.thbs.realestate.service;

import com.thbs.realestate.model.Property;

import java.util.List;

public interface ViewServiceInterface {

    public List<Property> getPropertyByRent();
    public List<Property> getPropertyByLand();
    public List<Property> getPropertyByVilla();
    public List<Property> getPropertyByApartment();
    public Property getDetailsById1(Integer id);


}
