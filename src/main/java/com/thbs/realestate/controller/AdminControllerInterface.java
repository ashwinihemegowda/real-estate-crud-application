package com.thbs.realestate.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface AdminControllerInterface {
    public String showPropertyList(Model model);
    public String addProperty(Model model);
    public String saveNewProperty(MultipartFile file, String propertyName, String category, String description, String price, String address,
                                  String facilities, String ownerName, long contactNo, String email, RedirectAttributes ra);
    public String saveUpdatedProperty(MultipartFile file, int propertyId, String propertyName, String category, String description, String price,
                                      String address, String facilities, String ownerName, long contactNo, String email, RedirectAttributes ra);
    public String editProperty(@PathVariable("id") Integer id, Model model);
    public String deleteProperty(@PathVariable("id") Integer id);
    public String getPropertyById(@PathVariable("propertyId") Integer id,Model model);
}
