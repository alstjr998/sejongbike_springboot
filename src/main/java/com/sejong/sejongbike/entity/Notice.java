package com.sejong.sejongbike.entity;

import com.sejong.sejongbike.dto.NoticeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notice")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Notice extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void noticeUpdate(NoticeDTO noticeDTO){
        if(this.id != noticeDTO.getId()){
            throw new IllegalArgumentException("잘못된 ID입니다.");
        }
        if(noticeDTO.getTitle() != null){
            this.title = noticeDTO.getTitle();
        }
        if(noticeDTO.getContent() != null){
            this.content = noticeDTO.getContent();
        }
    }

    public static Notice toNoticeEntity(NoticeDTO noticeDTO){
        if(noticeDTO.getId() != null){
            throw new IllegalArgumentException("공지사항의 게시글 ID가 없어야 작성 가능합니다.");
        }
        return new Notice(
                noticeDTO.getId(),
                noticeDTO.getTitle(),
                noticeDTO.getContent()
        );
    }
}
