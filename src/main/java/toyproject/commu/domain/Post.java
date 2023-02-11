package toyproject.commu.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import toyproject.commu.exception.LikeNegativeException;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Post {
    @Id
    @GeneratedValue
    @Column(name="post_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // 작성자!

    @Column(length=100)
    private String title;
    @Column(length=400)
    private String content;

    private int likes;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // == 연관 관계 메서드
    public void setMember(Member member){
        this.member = member;
        member.getPosts().add(this);
    }

    // == create method
    public static Post createPost(Member member, String title, String content){
        Post post = new Post();
        post.setMember(member);
        post.createdAt = LocalDateTime.now();
        post.updatedAt = LocalDateTime.now();
        post.title = title;
        post.content = content;
        post.likes = 0;

        return post;
    }

    public void addLike(){
        this.likes += 1;
    }

    public void cancelLike(){
        if (this.likes == 0){
            throw new LikeNegativeException("Like is zero");
        }
        else{
            this.likes -= 1;
        }
    }
}
