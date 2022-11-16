package adrian.codefellowship.controllers;

import adrian.codefellowship.models.ApplicationUser;
import adrian.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	private HttpServletRequest httpServletRequest;

	@GetMapping("/users")
	public String getUsersPage(Model model)
	{
		model.addAttribute("users", applicationUserRepository.findAll());
		return "users";
	}
	@PutMapping("/follow-user/{id}")
	public RedirectView followUser(@PathVariable Long id, Principal principal)
	{
		ApplicationUser userToFollow = applicationUserRepository.findById(id).orElseThrow(); // get user to follow
		ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName()); // get current user

		if (userToFollow.getUsername().equals(currentUser.getUsername()))
		{
			throw new IllegalArgumentException("Following yourself is a bad idea!");
		}

		currentUser.getFollowing().add(userToFollow); // follows the selected user
		applicationUserRepository.save(currentUser);

		return new RedirectView("/user/" + id);
	}

	@GetMapping("/myprofile")
	public RedirectView getMyProfile(Principal principal)
	{
		// find the user currently logged in
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(principal.getName());
		Long userId = applicationUser.getId();

		return new RedirectView("/user/" + userId);
	}

	@GetMapping("/user/{id}")
	public String getUserInfo(@PathVariable Long id, Model model, Principal principal)
	{
		ApplicationUser applicationUser = applicationUserRepository.findByUsername(principal.getName());
		model.addAttribute("applicationUser", applicationUser);


		ApplicationUser viewedUser = applicationUserRepository.findById(id).orElseThrow();
		model.addAttribute("viewedUser", viewedUser);

		return "user-info";
	}

	@PutMapping("/user/{id}")
	public RedirectView updateUserInfo(@PathVariable Long id, String username, Model model, Principal principal,
	                                   RedirectAttributes redirectAttributes) throws ServletException
	{
		if (principal.getName().equals(username))
		{
			ApplicationUser updatedUser = applicationUserRepository.findById(id).orElseThrow();
			updatedUser.setUsername(username);
			applicationUserRepository.save(updatedUser);

			httpServletRequest.logout();
		}
		else
		{
			redirectAttributes.addFlashAttribute("errorMessage", "You can only update your own username.");
		}
		return new RedirectView("/users/" + id);
	}


}
