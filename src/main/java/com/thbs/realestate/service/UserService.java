package com.thbs.realestate.service;

import com.thbs.realestate.model.User;
import com.thbs.realestate.web.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
