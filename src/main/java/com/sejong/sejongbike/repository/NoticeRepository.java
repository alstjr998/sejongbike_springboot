package com.sejong.sejongbike.repository;

import com.sejong.sejongbike.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

}
