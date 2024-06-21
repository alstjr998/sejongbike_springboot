package com.sejong.sejongbike.dto;

import com.sejong.sejongbike.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO {
    private Long id;
    private String title;
    private String content;
    private boolean deletedBySender;
    private boolean deletedByReceiver;
    private Long senderId;
    private Long receiverId;

    public static MessageDTO toMessageDTO(Message message){
        return new MessageDTO(
                message.getId(),
                message.getTitle(),
                message.getContent(),
                message.isDeletedBySender(),
                message.isDeletedByReceiver(),
                message.getSender().getId(),
                message.getReceiver().getId()
        );
    }
}
