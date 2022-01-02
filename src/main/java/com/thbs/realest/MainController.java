package com.thbs.realest;

import com.thbs.realest.property.Properties;
import com.thbs.realest.property.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private PropertyService service;

    @GetMapping("/index")
    public  String index()
    {
        return "index";
    }

}
