package com.sejong.sejongbike.repository;

import com.sejong.sejongbike.entity.Member;
import com.sejong.sejongbike.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findAllByReceiver(Member member);

    List<Message> findAllBySender(Member member);
}
