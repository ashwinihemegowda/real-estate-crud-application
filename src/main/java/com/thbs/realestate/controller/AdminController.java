package com.thbs.realestate.controller;

import com.thbs.realestate.service.PropertyServiceImpl;
import com.thbs.realestate.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

@Controller
public class AdminController {


    @Autowired
    private PropertyServiceImpl propertyService;

    @Autowired
    KafkaTemplate<String, Property> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    KafkaTemplate<String, List<Property>> kafkaTemplateList;

    public  final String TOPIC = "Kafka_Example";

    //Get all property from database and return to propertylist page
    @GetMapping("/propertylist")
    public String showPropertyList(Model model){
        List<Property> listProperty=propertyService.listAll();
        model.addAttribute("listProperties", listProperty);
        kafkaTemplateString.send(TOPIC,"Welcome Admin!!!!!!");
        kafkaTemplateString.send(TOPIC,"List of all properties");
        List<Property>properties=new LinkedList<>();
        for(Property p:listProperty){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }
        kafkaTemplateList.send(TOPIC,properties);
        return "propertylist";
    }


    //Add property details into new property object
    @GetMapping("/addProperty")
    public String addProperty(Model model){
        model.addAttribute("property", new Property());
        model.addAttribute("pageTitle", "Add New Property");
        kafkaTemplateString.send(TOPIC,"Add new Property");
        return "addProperty";
    }

    //save new property into database and redirect to propertylist page
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
        ra.addFlashAttribute("message","The property has been added successfully");


        return "redirect:/propertylist";
    }

    //update property by propertyId
    @GetMapping("/propertylist/edit/{id}")
    public String editProperty(@PathVariable("id") Integer propertyId, Model model){
        Property prop=propertyService.getDetailsById(propertyId);
        model.addAttribute("property", prop);
        model.addAttribute("pageTitle", "Edit Property (ID: "+propertyId+")");
        kafkaTemplateString.send(TOPIC,"Edit property with property id = "+propertyId+"");

        Property property=propertyService.getDetailsById(propertyId);
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "updateProperty";
    }


    //save updated property into database and redirect to propertylist page
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
        ra.addFlashAttribute("message","The property has been updated successfully");
        kafkaTemplateString.send(TOPIC," Updated details of property with property id = "+propertyId+"");
        Property property=propertyService.getDetailsById(propertyId);
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "redirect:/propertylist";
    }

    //delete property by id
    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") Integer id){
        propertyService.delete(id);
        kafkaTemplateString.send(TOPIC,"Property with property id = "+id+" is deleted");

        return "redirect:/propertylist";
    }

    //get details of property by propertyId
    @GetMapping("/details/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Integer id,Model model){
        Property property=propertyService.getDetailsById(id);
        model.addAttribute("details",property);
        model.addAttribute("pageTitle","Property Details" );
        kafkaTemplateString.send(TOPIC,"Details of property with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "details";

    }

    @GetMapping("/filter")
    public String filter(HttpServletRequest request,Model model){
        switch (request.getParameter("filter")){
            case "category":
                List<Property> category=propertyService.getByCategory(request.getParameter("word"));
                model.addAttribute("listProperties",category);
                return "propertylist";
            case "propertyName":
                List<Property> propertyName=propertyService.getByPropertyName(request.getParameter("word"));
                model.addAttribute("listProperties",propertyName);
                return "propertylist";
            case "price":
                List<Property> price=propertyService.getByPrice(request.getParameter("word"));
                model.addAttribute("listProperties",price);
                return "propertylist";
        }
        return "propertylist";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam("username")String username,@RequestParam("password")String password,RedirectAttributes ra){
        if(username.equals("admin@gmail.com") && password.equals("admin123")) {
            kafkaTemplateString.send(TOPIC, "Admin logged in successfully!!!");
            return "redirect:/propertylist";
        }
        else {
            ra.addFlashAttribute("message", "Invalid Email or password");
            return "redirect:/adminLogin";
        }

    }

}