package com.thbs.realestate.controller;

import com.thbs.realestate.model.Property;
import com.thbs.realestate.service.PropertyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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

import java.util.LinkedList;
import java.util.List;

@Controller
public class UserAdminController {
    @Autowired
    private PropertyServiceImpl propertyService;

    @Autowired
    KafkaTemplate<String, Property> kafkaTemplate;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplateString;

    @Autowired
    KafkaTemplate<String, List<Property>> kafkaTemplateList;

    public  final String TOPIC = "Kafka_Example";

    //Add property

    @GetMapping("/addProperty1")
    public String addProperty(Model model ,@AuthenticationPrincipal User user){
        String email=user.getUsername();
        Property property= new Property();
        property.setEmail(email);
        model.addAttribute("property", property);
        model.addAttribute("pageTitle", "Add New Property");
        kafkaTemplateString.send(TOPIC,"user "+email+" adding new property");
        return "addproperty1";
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
        kafkaTemplateString.send(TOPIC," Updated details of property with property id = "+propertyId+"");
        Property property=propertyService.getDetailsById(propertyId);
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "redirect:/propertylist1";
    }

    //update property by propertyId

    @GetMapping("/propertylist1/edit/{id}")
    public String editProperty(@PathVariable("id") Integer propertyId, Model model){
        Property property=propertyService.getDetailsById(propertyId);
        model.addAttribute("property", property);
        model.addAttribute("pageTitle", "Edit Property (ID: "+propertyId+")");
        kafkaTemplateString.send(TOPIC,"Edit property with property id = "+propertyId+"");

        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "updateproperty1";
    }

    //user add property----->
    @GetMapping("/propertylist1")
    public String useradd(@AuthenticationPrincipal User user, Model model){
        String email=user.getUsername();
        List<Property> list= propertyService.useradd(email);
        List<Property>properties=new LinkedList<>();
        for(Property p:list){
            properties.add(new Property(p.getPropertyId(),p.getCategory(),p.getPropertyName(),p.getDescription(),p.getPrice(),
                    p.getAddress(),p.getFacilities(),p.getOwnerName(),p.getContactNo(),p.getEmail()));
        }


        model.addAttribute("userproperty",list);
        kafkaTemplateString.send(TOPIC,"Welcome user "+email+"!!!!!!");
        kafkaTemplateString.send(TOPIC,"List of properties of user "+email+"");
        kafkaTemplateList.send(TOPIC,properties);

        return "propertylist1";
    }
//
    //delete property by id

    @GetMapping("/delete1/{id}")
    public String deleteProperty(@PathVariable("id") Integer id){
        propertyService.delete(id);
        kafkaTemplateString.send(TOPIC,"Property with property id = "+id+" is deleted");
        return "redirect:/propertylist1";
    }

    //get details of property by propertyId

    @GetMapping("/details1/{propertyId}")
    public String getPropertyById(@PathVariable("propertyId") Integer id,Model model){
        Property property=propertyService.getDetailsById(id);
        model.addAttribute("details",property);
        model.addAttribute("pageTitle","Property Details" );
        kafkaTemplateString.send(TOPIC,"Details of property with property id = "+property.getPropertyId());
        Property property1=new Property(property.getPropertyId(),property.getCategory(),property.getPropertyName(),
                property.getDescription(),property.getPrice(),property.getAddress(),property.getFacilities(),property.getOwnerName(),
                property.getContactNo(),property.getEmail());
        kafkaTemplate.send(TOPIC,property1);
        return "details1";

    }

}
