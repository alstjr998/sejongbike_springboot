package com.sejong.sejongbike.entity;

import com.sejong.sejongbike.dto.NoticeCommentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notice_comment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeComment extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String comment;

    @ManyToOne
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @Column
    private Long memberId;



    public void noticeCommentUpdate(NoticeCommentDTO noticeCommentDTO){
        if(this.id != noticeCommentDTO.getId()){
            throw new IllegalArgumentException("잘못된 ID입니다.");
        }
        /*if(this.memberId != noticeCommentDTO.getMemberId()){
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }*/
        if(noticeCommentDTO.getComment() != null){
            this.comment = noticeCommentDTO.getComment();
        }
    }

    public static NoticeComment toNoticeComment(NoticeCommentDTO noticeCommentDTO, Notice notice){
        if(noticeCommentDTO.getId() != null){
            throw new IllegalArgumentException("댓글 ID가 없어야 작성 가능합니다.");
        }
       /* if(noticeCommentDTO.getNoticeId() != notice.getId()) {
            throw new IllegalArgumentException("공지사항의 게시글 ID와 다릅니다.");
        }*/
        return new NoticeComment(noticeCommentDTO.getId(), noticeCommentDTO.getComment(), notice, noticeCommentDTO.getMemberId());
    }
}
