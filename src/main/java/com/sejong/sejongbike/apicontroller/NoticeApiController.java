package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.dto.NoticeCommentDTO;
import com.sejong.sejongbike.dto.NoticeDTO;
import com.sejong.sejongbike.entity.Notice;
import com.sejong.sejongbike.service.NoticeCommentService;
import com.sejong.sejongbike.service.NoticeService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeApiController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeCommentService noticeCommentService;

    @GetMapping(value = "/test")
    public String apiTest(){
        return "test.";
    }

    //Notice 전체 항목 받아오기
    @GetMapping(value = "/notice")
    public ResponseEntity<List<NoticeDTO>> getNoticeIndex(){
        List<NoticeDTO> noticeIndex = noticeService.getNoticeIndex();
        return ResponseEntity.status(HttpStatus.OK).body(noticeIndex);
    }

    //Notice 한개 받아오기
    @GetMapping(value = "/notice/{id}")
    public ResponseEntity<NoticeDTO> getNotice(@PathVariable("id") Long id){
        NoticeDTO noticeDTO = noticeService.getNotice(id);
        return ResponseEntity.status(HttpStatus.OK).body(noticeDTO);
    }

    //Notice 등록하기
    @PostMapping(value = "/notice/request")
    public ResponseEntity<NoticeDTO> postNotice(@RequestBody NoticeDTO noticeDTO){
        NoticeDTO createdNoticeDTO = noticeService.createNotice(noticeDTO);
        if(createdNoticeDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(createdNoticeDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Notice 수정하기
    @PatchMapping(value = "/notice/request/{id}")
    public ResponseEntity<NoticeDTO> updateNotice(@RequestBody NoticeDTO noticeDTO,
                                                  @PathVariable Long id){
        NoticeDTO updatedNoticeDTO = noticeService.updateNotice(noticeDTO, id);
        if(updatedNoticeDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedNoticeDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Notice 삭제하기 + 종속된 NoticeComment가 있을 경우 함께 삭제
    @DeleteMapping(value = "/notice/request/{id}")
    public ResponseEntity<NoticeDTO> deleteNotice(@PathVariable Long id){
        List<NoticeCommentDTO> deleteNoticeCommentBundle = noticeCommentService.deleteNoticeCommentBundle(id);
        NoticeDTO deletedNoticeDTO = noticeService.deleteNotice(id);
        if(deletedNoticeDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(deletedNoticeDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

}
