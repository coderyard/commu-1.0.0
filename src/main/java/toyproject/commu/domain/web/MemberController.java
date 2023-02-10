package toyproject.commu.domain.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import toyproject.commu.domain.Member;
import toyproject.commu.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 멤버 단건 조회

    // 멤버 모두 조회
    @GetMapping("members")
    public String list(Model model){
        List<Member> list = memberService.findMembers();
        model.addAttribute("members", list);
        return "/members/memberList";
    }

    // 회원 가입
    @GetMapping("members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "/members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(@Valid MemberForm form, BindingResult result){
        if (result.hasErrors()){
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/members";
    }
}
