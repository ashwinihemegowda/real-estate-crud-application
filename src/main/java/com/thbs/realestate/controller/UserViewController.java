package com.thbs.realestate.controller;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.service.ViewServiceInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserViewController{

    @Autowired
    private ViewServiceInterfaceImpl service;

    @Autowired
    KafkaTemplate<String, Property> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    KafkaTemplate<String, List<Property>> kafkaTemplateList;

    public  final String TOPIC = "Kafka_Example";

    //filter by category rent
    @GetMapping("/byRent")
    public String showPropertyListByRent(Model model) {
        List<Property> listProperty=service.getPropertyByRent();
        model.addAttribute("listProperties", listProperty);
        kafkaTemplateString.send(TOPIC,"List of all rent properties");
        List<Property>properties=new LinkedList<>();
        for(Property p:listProperty){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }
        kafkaTemplateList.send(TOPIC,properties);
        return "rentex";
    }

    //filter by category land
    @GetMapping("/byLand")
    public String showPropertyListForLand(Model model) {
        List<Property> listProperty=service.getPropertyByLand();
        model.addAttribute("listProperties", listProperty);
        kafkaTemplateString.send(TOPIC,"List of all land properties");
        List<Property>properties=new LinkedList<>();
        for(Property p:listProperty){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }
        kafkaTemplateList.send(TOPIC,properties);
        return "land";
    }

    //filter by category villa
    @GetMapping("/byVilla")
    public String showPropertyListForVilla(Model model) {
        List<Property> listProperty=service.getPropertyByVilla();
        model.addAttribute("listProperties", listProperty);
        kafkaTemplateString.send(TOPIC,"List of all villa properties");
        List<Property>properties=new LinkedList<>();
        for(Property p:listProperty){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }
        kafkaTemplateList.send(TOPIC,properties);
        return "villa";
    }

    //filter by category Apartment
    @GetMapping("/byApartment")
    public String showPropertyListForApartment(Model model) {
        List<Property> listProperty=service.getPropertyByApartment();
        model.addAttribute("listProperties", listProperty);
        kafkaTemplateString.send(TOPIC,"List of all apartment properties");
        List<Property>properties=new LinkedList<>();
        for(Property p:listProperty){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }
        kafkaTemplateList.send(TOPIC,properties);
        return "apartment";
    }

    //view details of Rent by propertyId
    @GetMapping("/viewDetailsRent/{propertyId}")
    public String getRentDetailsById(@PathVariable("propertyId")Integer id, Model model) {
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        kafkaTemplateString.send(TOPIC,"Details of rent property with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "detailsuserrent";
    }

    //view details of Apartment by propertyId
    @GetMapping("/viewDetailsApartment/{propertyId}")
    public String getApartmentDetailsById(@PathVariable("propertyId")Integer id, Model model) {
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        kafkaTemplateString.send(TOPIC,"Details of Apartment with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "detailsuserapartment";
    }

    //view details of Villa by propertyId
    @GetMapping("/viewDetailsVilla/{propertyId}")
    public String getVillaDetailsById(@PathVariable("propertyId")Integer id, Model model) {
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        kafkaTemplateString.send(TOPIC,"Details of Villa with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "detailsuservilla";
    }

    //view details of land by propertyId
    @GetMapping("/viewDetailsLand/{propertyId}")
    public String getLandDetailsById(@PathVariable("propertyId")Integer id, Model model) {
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        kafkaTemplateString.send(TOPIC,"Details of land with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "detailsuserland";
    }

}