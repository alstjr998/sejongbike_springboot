package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.dto.MemberDTO;
import com.sejong.sejongbike.dto.NoticeCommentDTO;
import com.sejong.sejongbike.service.NoticeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoticeCommentApiController {
    @Autowired
    NoticeCommentService noticeCommentService;

    //NoticeComment 전체 항목 받아오기
    @GetMapping(value = "/notice/comment")
    public ResponseEntity<List<NoticeCommentDTO>> getNoticeCommentIndex(){
        List<NoticeCommentDTO> noticeCommentIndex = noticeCommentService.getNoticeCommentIndex();
        return ResponseEntity.status(HttpStatus.OK).body(noticeCommentIndex);
    }

    //NoticeComment 중 특정 Notice에 소속된 항목 받아오기
    @GetMapping(value = "/notice/{noticeId}/comment")
    public ResponseEntity<List<NoticeCommentDTO>> getNoticeComment(@PathVariable Long noticeId){
        List<NoticeCommentDTO> noticeComment = noticeCommentService.getNoticeComment(noticeId);
        return ResponseEntity.status(HttpStatus.OK).body(noticeComment);
    }

    //NoticeComment 등록하기
    @PostMapping(value = "/notice/{noticeId}/comment")
    public ResponseEntity<NoticeCommentDTO> postNoticeComment(@RequestBody NoticeCommentDTO noticeCommentDTO,
                                                              @PathVariable Long noticeId){

        //NoticeCommentDTO 에 memberId 변수 추가하고, 로그인 상태에서는 세션의 사용자 정보를 꺼내서 사용자ID를 현 CommentDTO에 SET 한 이후에 아래 create를 수행한다
        NoticeCommentDTO createdNoticeCommentDTO = noticeCommentService.createNoticeComment(noticeCommentDTO, noticeId);
        if(createdNoticeCommentDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(createdNoticeCommentDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //NoticeComment 수정하기
    @PatchMapping(value = "/notice/comment/{id}")
    public ResponseEntity<NoticeCommentDTO> updateNoticeComment(@RequestBody NoticeCommentDTO noticeCommentDTO,
                                                                @PathVariable Long id){
        NoticeCommentDTO updatedNoticeCommentDTO = noticeCommentService.updateNoticeComment(noticeCommentDTO, id);
        if(updatedNoticeCommentDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedNoticeCommentDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //NoticeComment 삭제하기
    @DeleteMapping(value = "/notice/comment/{id}")
    public ResponseEntity<NoticeCommentDTO> deleteNoticeComment(@PathVariable Long id){
        NoticeCommentDTO deletedNoticeCommentDTO = noticeCommentService.deleteNoticeComment(id);
        if(deletedNoticeCommentDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(deletedNoticeCommentDTO);
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
