package adrian.codefellowship.configs;

import adrian.codefellowship.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserServiceImpl implements UserDetailsService
{
	@Autowired
	ApplicationUserRepository applicationUserRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return applicationUserRepository.findByUsername(username);
	}
}
