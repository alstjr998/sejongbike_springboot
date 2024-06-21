package com.sejong.sejongbike.repository;

import com.sejong.sejongbike.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> {
    List<NoticeComment> findByNoticeId(Long noticeId);
}
