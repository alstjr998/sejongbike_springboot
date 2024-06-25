package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.dto.MemberDTO;
import com.sejong.sejongbike.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberApiController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    //Member 전체 항목 받아오기
    @GetMapping(value = "/member")
    public ResponseEntity<List<MemberDTO>> getMemberIndex(){
        List<MemberDTO> memberDTOIndex = memberService.getMemberIndex();
        return ResponseEntity.status(HttpStatus.OK).body(memberDTOIndex);
    }

    //Member 한개 받아오기
    @GetMapping(value = "/member/{id}")
    public ResponseEntity<MemberDTO> getMember(@PathVariable Long id){
        MemberDTO memberDTO = memberService.getMember(id);
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }

    //Member 등록하기
    @PostMapping(value = "/member")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO){
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        MemberDTO createdMemberDTO = memberService.createMember(memberDTO);
        if(createdMemberDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(createdMemberDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Member 수정하기
    @PatchMapping(value = "/member/{id}")
    public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberDTO memberDTO,
                                                  @PathVariable Long id){
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        MemberDTO updatedMemberDTO = memberService.updateMember(memberDTO, id);
        if(updatedMemberDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(updatedMemberDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    //Member 삭제하기
    @DeleteMapping(value = "/member/{id}")
    public ResponseEntity<MemberDTO> deleteMember(@PathVariable Long id){
        MemberDTO deletedMemberDTO = memberService.deleteMember(id);
        if(deletedMemberDTO != null){
            return ResponseEntity.status(HttpStatus.OK).body(deletedMemberDTO);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



    //Login 상태 확인 창 - 로그인 상태
    @GetMapping(value = "/login/home")
    public String loginReqPage(){
        return "Login Access.";
    }

    //Login 상태 확인 창 - 로그인 상태 아님
    @GetMapping(value = "/login/error")
    public String loginNotReqPage(){
        return "Not Login User.";
    }

    //Login 상태 확인 창 - ADMIN 권한 보유자만 접속 가능
    @GetMapping(value = "/login/admin")
    public String loginAdminReqPage(){
        return "ADMIN Login Access.";
    }

    //Login 상태 확인 창 - 현재 자신의 회원 정보 확인
    @GetMapping(value = "/mypage")
    public ResponseEntity<MemberDTO> authenticateMemberInfo(){
        MemberDTO memberDTO = memberService.getMemberInfo();
        return ResponseEntity.status(HttpStatus.OK).body(memberDTO);
    }
}
