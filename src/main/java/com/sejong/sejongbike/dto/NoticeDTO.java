package com.sejong.sejongbike.dto;

import com.sejong.sejongbike.entity.Notice;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime subTime;
    private LocalDateTime updateTime;

    public Notice toNoticeEntity(){
        return new Notice(id, title, content);
    }

    public static NoticeDTO toNoticeDTO(Notice notice){
        return new NoticeDTO(
                notice.getId(),
                notice.getTitle(),
                notice.getContent(),
                notice.getSubTime(),
                notice.getUpdateTime()
        );
    }

}
