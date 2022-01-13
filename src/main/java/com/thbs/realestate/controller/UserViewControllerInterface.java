package com.thbs.realestate.controller;

import org.springframework.ui.Model;

public interface UserViewControllerInterface {

    public String showPropertyListByRent(Model model);
    public String showPropertyListForLand(Model model);
    public String showPropertyListForVilla(Model model);
    public String showPropertyListForApartment(Model model);
    public String getPropertyDetailsById( Integer id,Model model);

}
