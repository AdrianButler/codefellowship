package adrian.codefellowship.controllers;

import adrian.codefellowship.models.ApplicationUser;
import adrian.codefellowship.models.Post;
import adrian.codefellowship.repositories.ApplicationUserRepository;
import adrian.codefellowship.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.*;

@Controller
public class PostController
{
	@Autowired
	ApplicationUserRepository applicationUserRepository;

	@Autowired
	PostRepository postRepository;

	@PostMapping("/post")
	public RedirectView createPost(String postBody, Principal principal)
	{
		if (postBody.length() == 0)
		{
			//todo return an error saying post must have content
			return new RedirectView("/myprofile");
		}

		Date createdAt = new Date();

		ApplicationUser user = applicationUserRepository.findByUsername(principal.getName()); // find user by username

		Post post = new Post(postBody, createdAt, user);
		postRepository.save(post);

		return new RedirectView("/myprofile"); // send the user back to their profile
	}

	@GetMapping("/feed")
	public String getFeedPage(Principal principal, Model model)
	{
		ApplicationUser currentUser = applicationUserRepository.findByUsername(principal.getName());

		Set<Post> feed = new HashSet<>();
		// iterate through every user that is followed and add there posts to the feed

		for (ApplicationUser user : currentUser.getFollowing())
		{
			feed.addAll(user.getPosts());
		}

		model.addAttribute("posts", feed);
		return "feed";
	}
}
