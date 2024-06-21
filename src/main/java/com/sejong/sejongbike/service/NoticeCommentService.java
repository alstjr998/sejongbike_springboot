package com.sejong.sejongbike.service;

import com.sejong.sejongbike.dto.NoticeCommentDTO;
import com.sejong.sejongbike.entity.Notice;
import com.sejong.sejongbike.entity.NoticeComment;
import com.sejong.sejongbike.repository.MemberRepository;
import com.sejong.sejongbike.repository.NoticeCommentRepository;
import com.sejong.sejongbike.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeCommentService {
    @Autowired
    private NoticeCommentRepository noticeCommentRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private MemberRepository memberRepository;

    //NoticeComment 전체 항목 받아오기
    public List<NoticeCommentDTO> getNoticeCommentIndex(){
        List<NoticeCommentDTO> noticeCommentDTOList = noticeCommentRepository.findAll()
                .stream()
                .map(noticeComment -> NoticeCommentDTO.toNoticeCommentDTO(noticeComment))
                .collect(Collectors.toList());
        return noticeCommentDTOList;
    }

    //NoticeComment 중 특정 Notice에 소속된 항목 받아오기
    public List<NoticeCommentDTO> getNoticeComment(Long noticeId){
        List<NoticeCommentDTO> noticeCommentDTOList = noticeCommentRepository.findByNoticeId(noticeId)
                .stream()
                .map(noticeComment -> NoticeCommentDTO.toNoticeCommentDTO(noticeComment))
                .collect(Collectors.toList());
        return noticeCommentDTOList;
    }

    //NoticeComment 등록하기
    @Transactional
    public NoticeCommentDTO createNoticeComment(NoticeCommentDTO noticeCommentDTO, Long noticeId){
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 생성할 게시글이 없습니다."));
        NoticeComment noticeComment = NoticeComment.toNoticeComment(noticeCommentDTO, notice);
        NoticeComment createdNoticeComment = noticeCommentRepository.save(noticeComment);
        return NoticeCommentDTO.toNoticeCommentDTO(createdNoticeComment);
    }

    //NoticeComment 수정하기
    @Transactional
    public NoticeCommentDTO updateNoticeComment(NoticeCommentDTO noticeCommentDTO, Long id){
        NoticeComment noticeComment = noticeCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));
        noticeComment.noticeCommentUpdate(noticeCommentDTO);
        NoticeComment updatedNoticeComment = noticeCommentRepository.save(noticeComment);
        NoticeCommentDTO updatedNoticeCommentDTO = NoticeCommentDTO.toNoticeCommentDTO(updatedNoticeComment);
        return updatedNoticeCommentDTO;
    }

    //NoticeComment 삭제하기
    @Transactional
    public NoticeCommentDTO deleteNoticeComment(Long id){
        NoticeComment noticeComment = noticeCommentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 댓글이 없습니다."));
        if(noticeComment == null){
            return null;
        }
        noticeCommentRepository.delete(noticeComment);
        return NoticeCommentDTO.toNoticeCommentDTO(noticeComment);
    }

    //Notice에 종속된 NoticeComment 전체 삭제하기
    @Transactional
    public List<NoticeCommentDTO> deleteNoticeCommentBundle(Long noticeId){
        List<NoticeComment> noticeCommentList = noticeCommentRepository.findByNoticeId(noticeId);
        if(noticeCommentList != null){
            for(int i = 0; i < noticeCommentList.size(); i++) {
                NoticeComment noticeComment = noticeCommentList.get(i);
                noticeCommentRepository.delete(noticeComment);
            }
            List<NoticeCommentDTO> noticeCommentDTOList = noticeCommentList.stream()
                    .map(noticeComment -> NoticeCommentDTO.toNoticeCommentDTO(noticeComment))
                    .collect(Collectors.toList());
            return noticeCommentDTOList;
        }
        else {
            throw new IllegalArgumentException("게시글에 삭제할 댓글이 없습니다.");
        }
    }
}
