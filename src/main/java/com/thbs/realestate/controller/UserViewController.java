package com.thbs.realestate.controller;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.service.ViewServiceInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserViewController{

    @Autowired
    private ViewServiceInterfaceImpl service;

    //filter by category rent
    @GetMapping("/byRent")
    public String showPropertyListByRent(Model model) {
        List<Property> listProperty=service.getPropertyByRent();
        model.addAttribute("listProperties", listProperty);
        return "rentex";
    }

    //filter by category land
    @GetMapping("/byLand")
    public String showPropertyListForLand(Model model) {
        List<Property> listProperty=service.getPropertyByLand();
        model.addAttribute("listProperties", listProperty);
        return "land";
    }

    //filter by category villa
    @GetMapping("/byVilla")
    public String showPropertyListForVilla(Model model) {
        List<Property> listProperty=service.getPropertyByVilla();
        model.addAttribute("listProperties", listProperty);
        return "villa";
    }

    //filter by category Apartment
    @GetMapping("/byApartment")
    public String showPropertyListForApartment(Model model) {
        List<Property> listProperty=service.getPropertyByApartment();
        model.addAttribute("listProperties", listProperty);
        return "apartment";
    }

    //view details of property by propertyId
    @GetMapping("/viewdetails/{propertyId}")
    public String getPropertyDetailsById(@PathVariable("propertyId")Integer id, Model model) {
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        return "detailsuser";
    }

}