package com.sejong.sejongbike.service;

import com.sejong.sejongbike.dto.NoticeDTO;
import com.sejong.sejongbike.entity.Notice;
import com.sejong.sejongbike.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    //Notice 전체 항목 받아오기
    public List<NoticeDTO> getNoticeIndex(){
        List<NoticeDTO> noticeDTOList = noticeRepository.findAll()
                .stream()
                .map(notice -> NoticeDTO.toNoticeDTO(notice))
                .collect(Collectors.toList());
        return noticeDTOList;
    }

    //Notice 한개 받아오기
    public NoticeDTO getNotice(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 글이 없습니다."));
        NoticeDTO noticeDTO = NoticeDTO.toNoticeDTO(notice);
        return noticeDTO;
    }

    //Notice 등록하기
    @Transactional
    public NoticeDTO createNotice(NoticeDTO noticeDTO){
        Notice notice = Notice.toNoticeEntity(noticeDTO);
        if(notice.getId() != null){
            return null;
        }
        Notice createdNotice = noticeRepository.save(notice);
        NoticeDTO createdNoticeDTO = NoticeDTO.toNoticeDTO(createdNotice);
        return createdNoticeDTO;
    }

    //Notice 수정하기
    @Transactional
    public NoticeDTO updateNotice(NoticeDTO noticeDTO, Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 글이 없습니다."));
        notice.noticeUpdate(noticeDTO);
        Notice updatedNotice = noticeRepository.save(notice);
        NoticeDTO updatedNoticeDto = NoticeDTO.toNoticeDTO(updatedNotice);
        return updatedNoticeDto;
    }

    //Notice 삭제하기
    @Transactional
    public NoticeDTO deleteNotice(Long id){
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 글이 없습니다."));
        if(notice == null){
            return null;
        }
        noticeRepository.delete(notice);
        return NoticeDTO.toNoticeDTO(notice);
    }
}
