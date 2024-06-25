package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.dto.MessageDTO;
import com.sejong.sejongbike.dto.MessageRequestDTO;
import com.sejong.sejongbike.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageApiController {
    @Autowired
    private MessageService messageService;

    //인증된 유저가 수신한 Message 전부 받아오기
    @GetMapping(value = "/message/receive")
    public ResponseEntity<List<MessageDTO>> receiveMessageIndex(){
        List<MessageDTO> messageDTOIndex = messageService.getMessageIndex();
        return ResponseEntity.status(HttpStatus.OK).body(messageDTOIndex);
    }

    //인증된 유저가 수신한 Message 한 개 받아오기
    @GetMapping(value = "/message/receive/{id}")
    public ResponseEntity<MessageDTO> receiveMessage(@PathVariable Long id){
        MessageDTO messageDTO = messageService.getMessage(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }

    //인증된 유저가 송신한 Message 전부 받아오기
    @GetMapping(value = "/message/send")
    public ResponseEntity<List<MessageDTO>> sendMessageIndex(){
        List<MessageDTO> messageDTOIndex = messageService.getSendMessageIndex();
        return ResponseEntity.status(HttpStatus.OK).body(messageDTOIndex);
    }

    //인증된 유저가 송신한 Message 한 개 받아오기
    @GetMapping(value = "/message/send/{id}")
    public ResponseEntity<MessageDTO> sendMessage(@PathVariable Long id){
        MessageDTO messageDTO = messageService.getSendMessage(id);
        return ResponseEntity.status(HttpStatus.OK).body(messageDTO);
    }

    //Message 생성 및 전송하기
    @PostMapping(value = "/message")
    public ResponseEntity<MessageDTO> createMessage(@Valid @RequestBody MessageRequestDTO req){
        try {
            MessageDTO createdMessageDTO = messageService.createMessage(req);
            if(createdMessageDTO != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(createdMessageDTO);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //인증한 유저가 수신한 Message 삭제하기
    @DeleteMapping(value = "/message/receive/{id}")
    public ResponseEntity<MessageDTO> deleteReceiveMessage(@PathVariable Long id){
        try {
            MessageDTO deletedMessageDTO = messageService.deleteMessageByReceiver(id);
            if(deletedMessageDTO != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(deletedMessageDTO);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    //인증한 유저가 송신한 Message 삭제하기
    @DeleteMapping(value = "/message/send/{id}")
    public ResponseEntity<MessageDTO> deleteSendMessage(@PathVariable Long id){
        try {
            MessageDTO deletedMessageDTO = messageService.deleteMessageBySender(id);
            if(deletedMessageDTO != null){
                return ResponseEntity.status(HttpStatus.CREATED).body(deletedMessageDTO);
            }
            else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
        catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
