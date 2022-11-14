package adrian.codefellowship.controllers;

import adrian.codefellowship.models.ApplicationUser;
import adrian.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.Date;

@Controller
public class ApplicationUserController
{
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@GetMapping("/")
	public String getHome(Model model, Principal principal)
	{
		if (principal != null)
		{
			String username = principal.getName();
			ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

			model.addAttribute("userName", username);
			model.addAttribute("firstName", applicationUser.getFirstName());
		}

		return "index";
	}

	@GetMapping("/login")
	public String getLoginPage()
	{
		return "login";
	}

	@GetMapping("/signup")
	public String getSignupPage()
	{
		return "signup";
	}

	@PostMapping("/signup")
	public RedirectView createUser(String username, String password, String firstName, Date dateOfBirth)
	{
		// hash password

		// create new user

		// save new user

		// auto login will use httpServletRequest

		return new RedirectView("/");
	}


}
