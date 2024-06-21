package com.sejong.sejongbike.entity;

import com.sejong.sejongbike.constant.Role;
import com.sejong.sejongbike.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phoneNum;

    @Column
    private LocalDateTime ticketExpireDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;

    public void memberUpdate(MemberDTO memberDTO){
        if(this.id != memberDTO.getId()){
            throw new IllegalArgumentException("잘못된 ID입니다.");
        }
        if(memberDTO.getEmail() != null){
            this.email = memberDTO.getEmail();
        }
        if(memberDTO.getPassword() != null){
            this.password = memberDTO.getPassword();
        }
        if(memberDTO.getName() != null){
            this.name = memberDTO.getName();
        }
        if(memberDTO.getAddress() != null){
            this.address = memberDTO.getAddress();
        }
        if(memberDTO.getPhoneNum() != null){
            this.phoneNum = memberDTO.getPhoneNum();
        }
        if(memberDTO.getTicketExpireDate() != null){
            this.ticketExpireDate = memberDTO.getTicketExpireDate();
        }
        if(memberDTO.getRole() != null){
            this.role = memberDTO.getRole();
        }
    }

    public static Member toMemberEntity(MemberDTO memberDTO){
        if(memberDTO.getId() != null){
            throw new IllegalArgumentException("DTO에 ID가 없어 엔티티로 변환 불가.");
        }
        return new Member(
                memberDTO.getId(),
                memberDTO.getEmail(),
                memberDTO.getPassword(),
                memberDTO.getName(),
                memberDTO.getAddress(),
                memberDTO.getPhoneNum(),
                memberDTO.getTicketExpireDate(),
                memberDTO.getRole()
        );
    }
}
