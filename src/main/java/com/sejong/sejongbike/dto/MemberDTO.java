package com.sejong.sejongbike.dto;

import com.sejong.sejongbike.constant.Role;
import com.sejong.sejongbike.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String phoneNum;
    private LocalDateTime ticketExpireDate;
    private Role role;

    public static MemberDTO toMemberDTO(Member member){
        return new MemberDTO(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getName(),
                member.getAddress(),
                member.getPhoneNum(),
                member.getTicketExpireDate(),
                member.getRole()
        );
    }
}
