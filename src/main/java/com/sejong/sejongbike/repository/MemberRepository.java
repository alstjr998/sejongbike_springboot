package com.sejong.sejongbike.repository;

import com.sejong.sejongbike.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    Member findByName(String name);

}
