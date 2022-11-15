package adrian.codefellowship.repositories;

import adrian.codefellowship.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>
{

}