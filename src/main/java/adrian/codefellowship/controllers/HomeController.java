package adrian.codefellowship.controllers;

import adrian.codefellowship.models.ApplicationUser;
import adrian.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController
{
	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@GetMapping("/")
	public String getHome(Model model, Principal principal)
	{
		if (principal != null)
		{
			String username = principal.getName();
			ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);

			model.addAttribute("username", username);
			model.addAttribute("firstname", applicationUser.getFirstName());
		}

		return "index";
	}
}
