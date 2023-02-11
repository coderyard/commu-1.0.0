package toyproject.commu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toyproject.commu.domain.Member;
import toyproject.commu.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 개인 정보 확인

    // 회원 가입
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 이름 검증 로직
        Long saveId = memberRepository.save(member);
        return saveId;
    }

    // 로그인 -> 서비스에 만드는 것이 좋을까? 아니면 Repository로 보내서 받아오는게 나을까?
//    public String login(String name, String password){
//        // 인증 정보 조회 후 맞으면 토큰 리턴, 틀리면 Exception을 던질 것!
//    }



    private void validateDuplicateMember(Member member){
        List<Member> members = memberRepository.findByName(member.getName());
        if(!members.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    // 특정 멤버 조회 (by Id, 비밀 번호 말고 이름, 나이, 성별)
    public Member findMember(Long id){
        return memberRepository.findOne(id);
    }

}