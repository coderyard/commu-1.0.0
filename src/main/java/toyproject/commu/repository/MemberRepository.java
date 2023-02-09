package toyproject.commu.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import toyproject.commu.domain.Member;

import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Member member){
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.username=:name")
                .getResultList();
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m")
                .getResultList();
    }

}