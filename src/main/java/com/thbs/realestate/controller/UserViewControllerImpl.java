package com.thbs.realestate.controller;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.service.PropertyServiceImpl;
import com.thbs.realestate.service.UserServiceImpl;
import com.thbs.realestate.service.ViewServiceInterfaceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class UserViewControllerImpl {

    @Autowired
    private ViewServiceInterfaceImpl service;

    // Rent
    @GetMapping("/byRent")
    public String showPropertyListByRent(Model model){
        List<Property> listProperty=service.getPropertyByRent();
        model.addAttribute("listProperties", listProperty);
        return "rentex";
    }

    // Land
    @GetMapping("/byLand")
    public String showPropertyListForLand(Model model){
        List<Property> listProperty=service.getPropertyByLand();
        model.addAttribute("listProperties", listProperty);
        return "land";
    }
    //Villa
    @GetMapping("/byVilla")
    public String showPropertyListForVilla(Model model){
        List<Property> listProperty=service.getPropertyByVilla();
        model.addAttribute("listProperties", listProperty);
        return "villa";

    }
    //Apartment
    @GetMapping("/byApartment")
    public String showPropertyListForApartment(Model model){
        List<Property> listProperty=service.getPropertyByApartment();
        model.addAttribute("listProperties", listProperty);
        return "apartment";
    }

    @GetMapping("/viewdetails/{propertyId}")
    public String getPropertyDetailsById(@PathVariable("propertyId") Integer id,Model model){
        Property property=service.getDetailsById1(id);
        model.addAttribute("details",property);
        return "detailsuser";

    }



//    @GetMapping("/propertylist")
//    public String showPropertyList(Model model){
//        List<Property> listProperty=service.listAll();
//        model.addAttribute("listProperties", listProperty);
//        return "propertylist";
//        // return "rentex";
//        //return "rent";
//    }
//
//    @GetMapping("/new")
//    public String addProperty(Model model){
//        Property prop=new Property();
//        model.addAttribute("property", prop);
//        model.addAttribute("pageTitle", "Add New Property");
//        return "addProperty";
//    }
//
//    @PostMapping("/save")
//    public String saveProperty(Property property, RedirectAttributes ra){
//        service.save(property);
//        ra.addFlashAttribute("message","The user has been saved successfully");
//        return "redirect:/propertylist";
//    }
//
//    @GetMapping("/propertylist/edit/{id}")
//    public String editProperty(@PathVariable("id") Integer id, Model model){
//        Property prop=service.get(id);
//        model.addAttribute("property", prop);
//        model.addAttribute("pageTitle", "Edit Property (ID: "+id+")");
//        return "updateProperty";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteProperty(@PathVariable("id") Integer id){
//            service.delete(id);
//            return "redirect:/propertylist";
//    }




}