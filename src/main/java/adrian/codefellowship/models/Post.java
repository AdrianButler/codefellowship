package adrian.codefellowship.models;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Post
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private ApplicationUser owner;

	private String body;
	private Date createdAt;

	 protected Post()
	{
	}

	public Post(String body, Date createdAt, ApplicationUser owner)
	{
		this.owner = owner;
		this.body = body;
		this.createdAt = createdAt;
	}

	public ApplicationUser getOwner()
	{
		return owner;
	}

	public void setOwner(ApplicationUser owner)
	{
		this.owner = owner;
	}

	public String getBody()
	{
		return body;
	}

	public void setBody(String body)
	{
		this.body = body;
	}

	public Date getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

}