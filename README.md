# 백엔드 REST-API

어울링 페이지 리메이크 프로젝트(<https://github.com/alstjr998/sejongbike_react>)에 연결해서 사용해보기 위한 개인 스프링부트 프로젝트입니다.

---
## 제작 순서
#### 1. SpringBoot intializr(<https://start.spring.io/>)를 이용해 프로젝트 생성
#### 2. MySQL에 스키마 생성 및 데이터 연결
#### 3. 컨트롤러 및 엔드포인트 작성
#### 4. SpringSecurity를 이용하여 비밀번호 데이터를 암호화 후 DB에 저장
#### 5. JWT 발급 및 재발급 기능 추가, 로그인 및 로그아웃 엔드포인트를 JWT 발급 및 파기로 대체
#### 6. 프론트 페이지에 맞추어 주소 및 권한 수정

---
## 사용 가능한 기능
#### 1. 공지사항 테이블
- 조회(GET)
- 작성(POST)
- 수정(PATCH)
- 삭제(DELETE)
#### 2. 공지사항-댓글 테이블
- 조회(GET)
- 작성(POST)
- 수정(PATCH)
- 삭제(DELETE)
#### 3. 회원 정보 테이블
- 조회(GET)
- 작성(POST)
- 수정(PATCH)
- 삭제(DELETE)
#### 4. 쪽지 테이블
- 조회(GET)
- 작성(POST)
- 삭제(DELETE)