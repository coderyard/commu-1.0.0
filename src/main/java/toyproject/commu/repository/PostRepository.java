package toyproject.commu.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import toyproject.commu.domain.Post;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final EntityManager em;

    public Long save(Post post){
        em.persist(post);
        return post.getId();
    }

    public Post findOne(Long id){
        return em.find(Post.class, id);
    }


}
