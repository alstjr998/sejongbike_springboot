package com.sejong.sejongbike.service;

import com.sejong.sejongbike.dto.MemberDTO;
import com.sejong.sejongbike.entity.Member;
import com.sejong.sejongbike.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    //Member 전체 항목 받아오기
    public List<MemberDTO> getMemberIndex() {
        List<MemberDTO> memberDTOList = memberRepository.findAll()
                .stream()
                .map(member -> MemberDTO.toMemberDTO(member))
                .collect(Collectors.toList());
        return memberDTOList;
    }

    //Member 한개 받아오기
    public MemberDTO getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 회원이 존재하지 않습니다."));
        MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
        return memberDTO;
    }

    //Member 등록하기
    @Transactional
    public MemberDTO createMember(MemberDTO memberDTO) {
        Member member = Member.toMemberEntity(memberDTO);
        if (member.getId() != null) {
            return null;
        }
        Member createdMember = memberRepository.save(member);
        MemberDTO createdMemberDTO = MemberDTO.toMemberDTO(createdMember);
        return createdMemberDTO;
    }

    //Member 수정하기
    @Transactional
    public MemberDTO updateMember(MemberDTO memberDTO, Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 회원이 존재하지 않습니다."));
        member.memberUpdate(memberDTO);
        Member updatedMember = memberRepository.save(member);
        MemberDTO updatedMemberDTO = MemberDTO.toMemberDTO(updatedMember);
        return updatedMemberDTO;
    }

    //Member 삭제하기
    @Transactional
    public MemberDTO deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("대상 회원이 존재하지 않습니다."));
        if (member == null) {
            return null;
        }
        memberRepository.delete(member);
        return MemberDTO.toMemberDTO(member);
    }

    //현재 자신의 정보 확인
    public MemberDTO getMemberInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = memberRepository.findByEmail(authentication.getName());
        if (member == null) {
            throw new UsernameNotFoundException(authentication.getName());
        }
        MemberDTO memberDTO = MemberDTO.toMemberDTO(member);
        return memberDTO;
    }


    public Member findByEmail(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException(email);
        }
        return member;
    }
}
