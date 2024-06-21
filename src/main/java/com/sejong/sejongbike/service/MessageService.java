package com.sejong.sejongbike.service;

import com.sejong.sejongbike.dto.MessageDTO;
import com.sejong.sejongbike.dto.MessageRequestDTO;
import com.sejong.sejongbike.entity.Member;
import com.sejong.sejongbike.entity.Message;
import com.sejong.sejongbike.repository.MemberRepository;
import com.sejong.sejongbike.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MemberRepository memberRepository;

    //받은 Message 전부 확인
    public List<MessageDTO> getMessageIndex(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if(member == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllByReceiver(member);

        for(Message message : messageList){
            if(!message.isDeletedByReceiver()){
                messageDTOList.add(MessageDTO.toMessageDTO(message));
            }
        }
        return messageDTOList;
    }

    //받은 Message 한 개 확인
    public MessageDTO getMessage(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메세지가 존재하지 않습니다."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if(member == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        if(message.getReceiver() != member){
            throw new IllegalArgumentException("대상이 일치하지 않습니다.");
        }

        if(message.isDeletedByReceiver()){
            throw new IllegalArgumentException("메세지가 삭제되어 조회할 수 없습니다.");
        }
        MessageDTO messageDTO = MessageDTO.toMessageDTO(message);
        return messageDTO;
    }

    //Message 생성 및 전송하기
    @Transactional
    public MessageDTO createMessage(MessageRequestDTO req){
        Member receiver = memberRepository.findByEmail(req.getReceiverEmail());
        if(receiver == null){
            throw new UsernameNotFoundException(req.getReceiverEmail());
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member sender = memberRepository.findByEmail(authentication.getName());
        if(sender == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        Message message = new Message(
                req.getTitle(),
                req.getContent(),
                sender,
                receiver
        );
        messageRepository.save(message);
        MessageDTO createdMessageDTO = MessageDTO.toMessageDTO(message);
        return createdMessageDTO;
    }

    //보낸 Message 전부 확인
    public List<MessageDTO> getSendMessageIndex() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if (member == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }

        List<MessageDTO> messageDTOList = new ArrayList<>();
        List<Message> messageList = messageRepository.findAllBySender(member);

        for (Message message : messageList) {
            if (!message.isDeletedBySender()) {
                messageDTOList.add(MessageDTO.toMessageDTO(message));
            }
        }
        return messageDTOList;
    }

    //보낸 Message 한 개 확인
    public MessageDTO getSendMessage(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("메세지가 존재하지 않습니다."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if(member == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        if(message.getSender() != member){
            throw new IllegalArgumentException("대상이 일치하지 않습니다.");
        }

        if(message.isDeletedBySender()){
            throw new IllegalArgumentException("메세지가 삭제되어 조회할 수 없습니다.");
        }
        MessageDTO messageDTO = MessageDTO.toMessageDTO(message);
        return messageDTO;
    }

    //받은 쪽지 삭제
    @Transactional
    public MessageDTO deleteMessageByReceiver(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메세지가 없습니다."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if(member == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        if(message.getReceiver() == member){
            message.deleteByReceiver();
        }
        else {
            throw new IllegalArgumentException("메세지 삭제 권한이 없습니다.");
        }

        //양쪽 모두 삭제했을 경우, 완전히 DB에서 제거
        if(message.isMessageDeleted()){
            messageRepository.delete(message);
        }

        return MessageDTO.toMessageDTO(message);
    }

    //보낸 쪽지 삭제
    @Transactional
    public MessageDTO deleteMessageBySender(Long id){
        Message message = messageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 메세지가 없습니다."));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if(member == null){
            throw new UsernameNotFoundException(authentication.getName());
        }

        if(message.getSender() == member){
            message.deleteBySender();
        }
        else {
            throw new IllegalArgumentException("메세지 삭제 권한이 없습니다.");
        }

        //양쪽 모두 삭제했을 경우, 완전히 DB에서 제거
        if(message.isMessageDeleted()){
            messageRepository.delete(message);
        }

        return MessageDTO.toMessageDTO(message);
    }
}
