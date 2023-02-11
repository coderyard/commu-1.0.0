package toyproject.commu.domain.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import toyproject.commu.domain.Member;
import toyproject.commu.domain.Post;
import toyproject.commu.service.MemberService;
import toyproject.commu.service.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final MemberService memberService;
    private final PostService postService;

    // 포스트 조회
    @GetMapping("/posts")
    public String list(Model model){
        List<Post> posts = postService.findPosts();
        model.addAttribute("posts", posts);
        return "/posts/postList";
    }

    // 포스트 작성
    @GetMapping("/posts/new")
    public String createPostForm(Model model){
        model.addAttribute("postForm", new PostForm());
        model.addAttribute("members", memberService.findMembers());
        return "/posts/createPostForm";
    }

    @PostMapping("/posts/new")
    public String create(@RequestParam("memberId") Long memberId, PostForm form){
        postService.post(memberId, form.getTitle(), form.getContent());
        return "redirect:/posts";
    }
}
