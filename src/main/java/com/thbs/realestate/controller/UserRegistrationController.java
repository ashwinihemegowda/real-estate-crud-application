package com.thbs.realestate.controller;

import com.thbs.realestate.repository.UserRepository;
import com.thbs.realestate.service.UserService;
import com.thbs.realestate.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplateString;

	public  final String TOPIC = "Kafka_Example";

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto) {
		if(!userService.userExistsByEmail(registrationDto.getEmail())) {
			userService.save(registrationDto);
			kafkaTemplateString.send(TOPIC,"User "+registrationDto.getEmail()+" registered successfully");
			return "redirect:/registration?success";
		}
		else{
			kafkaTemplateString.send(TOPIC,"username "+registrationDto.getEmail()+"already exists");
			return "redirect:/registration?failed";
		}

	}
}
