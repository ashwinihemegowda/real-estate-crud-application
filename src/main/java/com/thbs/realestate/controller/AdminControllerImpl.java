package com.thbs.realestate.controller;

import com.thbs.realestate.service.PropertyServiceImpl;
import com.thbs.realestate.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdminControllerImpl implements AdminControllerInterface{

    @Autowired
    private PropertyServiceImpl propertyService;

    @Override
    @GetMapping("/propertylist")
    public String showPropertyList(Model model){
        List<Property> listProperty=propertyService.listAll();
        model.addAttribute("listProperties", listProperty);
        return "propertylist";
    }

    @Override
    @GetMapping("/addProperty")
    public String addProperty(Model model){
        model.addAttribute("property", new Property());
        model.addAttribute("pageTitle", "Add New Property");
        return "addProperty";
    }

    @Override
    @PostMapping("/saveNewProperty")
    public String saveNewProperty(@RequestParam("file")MultipartFile file,
                                  @RequestParam("propertyName")String propertyName,
                                  @RequestParam("category") String category,
                                  @RequestParam("description") String description,
                                  @RequestParam("price")String price,
                                  @RequestParam("address") String address,
                                  @RequestParam("facilities") String facilities,
                                  @RequestParam("ownerName") String ownerName,
                                  @RequestParam("contactNo")long contactNo,
                                  @RequestParam("email")String email,
                                  RedirectAttributes ra

    ){
        propertyService.savePropertyToDB(file,propertyName,category,description,price,address,facilities,ownerName,contactNo,email);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/propertylist";
    }

    @Override
    @PostMapping("/saveUpdatedProperty")
    public String saveUpdatedProperty(@RequestParam("file")MultipartFile file,
                                      @RequestParam("propertyId")int propertyId,
                                      @RequestParam("propertyName")String propertyName,
                                      @RequestParam("category") String category,
                                      @RequestParam("description") String description,
                                      @RequestParam("price")String price,
                                      @RequestParam("address") String address,
                                      @RequestParam("facilities") String facilities,
                                      @RequestParam("ownerName") String ownerName,
                                      @RequestParam("contactNo")long contactNo,
                                      @RequestParam("email")String email,
                                      RedirectAttributes ra
    ){
        propertyService.updatePropertyToDB(file,propertyId,propertyName,category,description,price,address,facilities,ownerName,contactNo,email);
        ra.addFlashAttribute("message","The user has been updated successfully");
        return "redirect:/propertylist";
    }

    @Override
    @GetMapping("/propertylist/edit/{id}")
    public String editProperty(@PathVariable("id") Integer id, Model model){
        Property prop=propertyService.get(id);
        model.addAttribute("property", prop);
        model.addAttribute("pageTitle", "Edit Property (ID: "+id+")");
        return "updateProperty";
    }

    @Override
    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") Integer id){
        propertyService.delete(id);
        return "redirect:/propertylist";
    }

    @Override
    @GetMapping("/details/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Integer id,Model model){
        Property property=propertyService.getDetailsById(id);
        model.addAttribute("details",property);
        model.addAttribute("pageTitle","Property Details" );
        return "details";

    }

}