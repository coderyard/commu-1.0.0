package toyproject.commu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.commu.domain.Member;
import toyproject.commu.domain.Post;
import toyproject.commu.repository.MemberRepository;
import toyproject.commu.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long post(Long memberId, String title, String content){
        Member member = memberRepository.findOne(memberId);

        Post post = Post.createPost(member, title, content);
        postRepository.save(post);
        return post.getId();
    }

    public Post findPost(Long postId){
        return postRepository.findOne(postId);
    }

    public List<Post> findPosts(){
        return postRepository.findAll();
    }
}
