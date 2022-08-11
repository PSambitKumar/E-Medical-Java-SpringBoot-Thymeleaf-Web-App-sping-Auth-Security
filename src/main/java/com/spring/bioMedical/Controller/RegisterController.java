package com.spring.bioMedical.Controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.spring.bioMedical.entity.User;
import com.spring.bioMedical.service.EmailService;
import com.spring.bioMedical.service.UserService;

@Controller
public class RegisterController {
	
	private UserService userService;
	private EmailService emailService;
	
	@Autowired
	public RegisterController(
			UserService userService, EmailService emailService) {
		this.userService = userService;
		this.emailService = emailService;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)

	public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request) {
		User userExists = userService.findByEmail(user.getEmail());
		System.out.println(userExists);
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("register");		
		} else { // new user so we create user and send confirmation e-mail

			user.setEnabled(false);
			user.setRole("ROLE_USER");
		     user.setConfirmationToken(UUID.randomUUID().toString());
		     userService.saveUser(user);
		    String appUrl = "localhost:8080";
		    SimpleMailMessage registrationEmail = new SimpleMailMessage();
		    registrationEmail.setTo(user.getEmail());
		    registrationEmail.setSubject("Registration Confirmation");
		    registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
		    registrationEmail.setFrom("spring.email.auth@gmail.com");
		    emailService.sendEmail(registrationEmail);
		    modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
		    modelAndView.setViewName("register");
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, @RequestParam("token") String token) {
		User user = userService.findByConfirmationToken(token);
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
		modelAndView.setViewName("confirm");
		return modelAndView;		
	}
	
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView confirmRegistration(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
		modelAndView.setViewName("confirm");
		Zxcvbn passwordCheck = new Zxcvbn();
		Strength strength = passwordCheck.measure(requestParams.get("password"));
		if (strength.getScore() < 3) {
			bindingResult.reject("password");
			redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
			modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
			System.out.println(requestParams.get("token"));
			return modelAndView;
		}
		User user = userService.findByConfirmationToken(requestParams.get("token"));
		user.setPassword(requestParams.get("password"));
		user.setEnabled(true);
		userService.saveUser(user);
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
	
	
	
}