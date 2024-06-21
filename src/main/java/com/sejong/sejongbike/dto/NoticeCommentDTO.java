package com.sejong.sejongbike.dto;

import com.sejong.sejongbike.entity.NoticeComment;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCommentDTO {
    private Long id;
    private String comment;
    private Long noticeId;
    private Long memberId;

    public static NoticeCommentDTO toNoticeCommentDTO(NoticeComment noticeComment){
        return new NoticeCommentDTO(
                noticeComment.getId(),
                noticeComment.getComment(),
                noticeComment.getNotice().getId(),
                noticeComment.getMemberId()
        );
    }
}
