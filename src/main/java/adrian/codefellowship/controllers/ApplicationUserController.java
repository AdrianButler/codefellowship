package adrian.codefellowship.controllers;

import adrian.codefellowship.models.ApplicationUser;
import adrian.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class ApplicationUserController
{
	@Autowired
	private ApplicationUserRepository applicationUserRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private HttpServletRequest httpServletRequest;

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

	@GetMapping("/login")
	public String getLoginPage()
	{
		return "login";
	}

	@PostMapping("/login")
	public RedirectView login()
	{

		return new RedirectView("/");
	}

	@GetMapping("/signup")
	public String getSignupPage()
	{
		return "signup";
	}

	@PostMapping("/signup")
	public RedirectView createUser(String username, String password, String firstname, Date dateOfBirth)
	{
		// hash password
		String hashedPassword = passwordEncoder.encode(password);
		// create new user
		ApplicationUser applicationUser = new ApplicationUser(username, hashedPassword, firstname);
		// save new user
		applicationUserRepository.save(applicationUser);
		// auto login will use httpServletRequest
		authWithHttpServletRequest(username, password);
		return new RedirectView("/");
	}
	private void authWithHttpServletRequest(String username, String password)
	{
		try
		{
			httpServletRequest.login(username, password);
		}
		catch (Exception exception)
		{
			exception.printStackTrace();
		}

	}


}
