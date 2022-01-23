package com.thbs.realestate.service;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyServiceInterface {

    @Autowired
    private PropertyRepository propertyRepository;
    

    //Method to get property details from database
    @Override
    public List<Property> listAll(){
        return (List<Property>) propertyRepository.findAll();
    }


    //Method to delete property by id
    @Override
    public void delete(Integer id)  {
        propertyRepository.deleteById(id);
    }

    //Method to get property details by id
    @Override
    public Property getDetailsById(Integer id) {
        return propertyRepository.findById(id).get();
    }


    //Method used to update property inside database
    @Override
    public void updatePropertyToDB(MultipartFile file, int propertyId, String propertyName, String category, String description, String price, String address, String facilities, String ownerName, long contactNo, String email) {
        Property property=new Property();
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a valid file");
        }
        try {
            property.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        property.setPropertyId(propertyId);
        property.setPropertyName(propertyName);
        property.setCategory(category);
        property.setDescription(description);
        property.setPrice(price);
        property.setAddress(address);
        property.setFacilities(facilities);
        property.setOwnerName(ownerName);
        property.setContactNo(contactNo);
        property.setEmail(email);
        propertyRepository.save(property);

    }


    //Method to save new property in database
    @Override
    public void savePropertyToDB(MultipartFile file, java.lang.String propertyName, java.lang.String category, java.lang.String description, java.lang.String price, java.lang.String address, java.lang.String facilities, java.lang.String ownerName, long contactNo, java.lang.String email) {
        Property property=new Property();
        String fileName= StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a valid file");
        }
        try {
            property.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        property.setPropertyName(propertyName);
        property.setCategory(category);
        property.setDescription(description);
        property.setPrice(price);
        property.setAddress(address);
        property.setFacilities(facilities);
        property.setOwnerName(ownerName);
        property.setContactNo(contactNo);
        property.setEmail(email);
        propertyRepository.save(property);

    }

    //Method to get property details filtered by Email
    public List<Property> useradd(String email)
    {
        return propertyRepository.findByEmail(email);
    }


}
