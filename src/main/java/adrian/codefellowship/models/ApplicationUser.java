package adrian.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "owner")
	private List<Post> posts;

	@ManyToMany
	@JoinTable
			(
					name = "followers_to_following",
					joinColumns = {@JoinColumn(name = "userWhoFollows")},
					inverseJoinColumns = {@JoinColumn(name = "followedUser")}
			)
	private Set<ApplicationUser> following = new HashSet<>();

	@ManyToMany(mappedBy = "following")
	private Set<ApplicationUser> followers = new HashSet<>();

	private String username;
	private String password;
	private String firstName;
	private Date dateOfBirth;

	public ApplicationUser(String username, String password, String firstName, Date dateOfBirth)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
	}

	public ApplicationUser(String username, String password, String firstName)
	{
		this.username = username;
		this.password = password;
		this.firstName = firstName;
	}

	protected ApplicationUser()
	{

	}

	public Set<ApplicationUser> getFollowing()
	{
		return following;
	}

	public void setFollowing(Set<ApplicationUser> following)
	{
		this.following = following;
	}

	public Set<ApplicationUser> getFollowers()
	{
		return followers;
	}

	public void setFollowers(Set<ApplicationUser> followers)
	{
		this.followers = followers;
	}

	public List<Post> getPosts()
	{
		return posts;
	}

	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}

	public String getUsername()
	{
		return username;
	}

	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return null;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Long getId()
	{
		return id;
	}

}