package com.thbs.realestate.service;

import com.thbs.realestate.model.Property;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PropertyServiceInterface {

    //Abstract Method to list all property
    public List<Property> listAll();

    //Abstract Method to get property by propertyId
    public Property get(Integer id);

    //Abstract Method to delete property by propertyId
    public void delete(Integer id);

    //Abstract Method to get details of property by propertyId
    public Property getDetailsById(Integer id);

    //Abstract Method to save updated property in database
    public void updatePropertyToDB(MultipartFile file, int propertyId, String propertyName, String category, String description, String price, String address, String facilities,
                                   String ownerName, long contactNo, String email);


    //Abstract Method to save new property in database
    public void savePropertyToDB(MultipartFile file,String propertyName,String category, String description, String price,String address, String facilities,
                                 String ownerName, long contactNo, String email);
}
