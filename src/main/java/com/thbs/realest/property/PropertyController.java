package com.thbs.realest.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@Controller
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping("/properties")
    public String showPropertyList(Model model){
        List<Properties> listProperty=service.listAll();
        model.addAttribute("listProperties", listProperty);
        return "properties";
    }

    @GetMapping("/new")
    public String addProperty(Model model){
        Properties prop=new Properties();
        model.addAttribute("property", prop);
        //model.addAttribute("pageTitle", "Add New Property");
        return "addProperty";
    }

    @PostMapping("/save")
    public String saveProperty(Properties properties, RedirectAttributes ra){
        service.save(properties);
        ra.addFlashAttribute("message","The user has been saved successfully");
        return "redirect:/properties";
    }

    @GetMapping("/properties/edit/{id}")
    public String editProperty(@PathVariable("id") Integer id, Model model){
        Properties prop=service.get(id);
        model.addAttribute("property", prop);
        model.addAttribute("pageTitle", "Edit Property (ID: "+id+")");
        return "updateProperty";
    }

    @GetMapping("/delete/{id}")
    public String deleteProperty(@PathVariable("id") Integer id, RedirectAttributes ra){
        try{
            service.delete(id);
            ra.addFlashAttribute("message", "The Property ID "+id+" has been deleted.");
        }
        catch (UserPrincipalNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/properties";
    }
}
