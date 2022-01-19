package com.thbs.realestate.controller;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserAdminController {
    @Autowired
    private PropertyServiceImpl propertyService;
    //Get all property



    //Add property

    @GetMapping("/addProperty1")
    public String addProperty(Model model ){
        //String email=user.getUsername();
        Property property= new Property();
        //property.setEmail(email);
        model.addAttribute("property", property);
        model.addAttribute("pageTitle", "Add New Property");
        return "addProperty1";
    }

    //save new property

    @PostMapping("/saveNewProperty1")
    public String saveNewProperty(@RequestParam("file") MultipartFile file,
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
        ra.addFlashAttribute("message","The property has been added successfully");
        return "redirect:/propertylist1";
    }

    //save updated property

    @PostMapping("/saveUpdatedProperty1")
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
        ra.addFlashAttribute("message","The property has been updated successfully");
        return "redirect:/propertylist1";
    }

    //update property by propertyId

    @GetMapping("/propertylist1/edit/{id}")
    public String editProperty(@PathVariable("id") Integer id, Model model){
        Property prop=propertyService.get(id);
        model.addAttribute("property", prop);
        model.addAttribute("pageTitle", "Edit Property (ID: "+id+")");
        return "updateProperty1";
    }

    //user add property----->
    @GetMapping("/propertylist1")
    public String useradd(@AuthenticationPrincipal User user, Model model){
        String email=user.getUsername();
        List<Property> list= propertyService.useradd(email);
        model.addAttribute("userproperty",list);

        return "propertylist1";
    }
//
    //delete property by id

    @GetMapping("/delete1/{id}")
    public String deleteProperty(@PathVariable("id") Integer id){
        propertyService.delete(id);
        return "redirect:/propertylist1";
    }

    //get details of property by propertyId

    @GetMapping("/details1/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Integer id,Model model){
        Property property=propertyService.getDetailsById(id);
        model.addAttribute("details",property);
        model.addAttribute("pageTitle","Property Details" );
        return "details1";

    }

}
