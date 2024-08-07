INSERT INTO notice(title, content, sub_time, update_time) VALUES('첫번째 공지사항', '첫번째 공지사항의 내용입니다.', '2024-01-01 10:00:00', '2024-01-01 10:00:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('두번째 공지사항', '두번째 공지사항의 내용입니다.', '2024-02-02 12:30:00', '2024-02-02 12:30:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('세번째 공지사항', '세번째 공지사항의 내용입니다.', '2024-03-05 17:30:00', '2024-03-05 17:30:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('네번째 공지사항', '네번째 공지사항의 내용입니다.', '2024-04-13 14:28:10', '2024-04-13 14:28:10');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('다섯번째 공지사항', '다섯번째 공지사항의 내용입니다.', '2024-05-24 21:46:23', '2024-05-24 21:46:23');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('여섯번째 공지사항', '여섯번째 공지사항의 내용입니다.', '2024-05-31 12:13:20', '2024-05-31 12:13:20');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('일곱번째 공지사항', '일곱번째 공지사항의 내용입니다.', '2024-06-04 11:26:43', '2024-06-04 11:26:43');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('[어울링 중단기간에 대한 보상 완료 안내]', '[어울링 중단기간에 대한 보상 완료 안내]<br><br>5월 13일 발생한 장애로 인해 정기권 소지 회원분들께 3일 연장 및 일일권 대상 이용자분들께 환불을 완료하였습니다.', '2024-06-17 19:54:29', '2024-06-17 19:54:29');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('[이응패스 어울링 서비스 연계 작업에 따른 일시 이용 중단 안내]', '우리시가 추진하는 이응패스와 어울링 서비스의 연계를 위한 API 배포로 인하여 어울링 서비스가 일시 중단 됨을 알려드립니다.<br><br>너른 양해를 부탁드립니다. 감사합니다.', '2024-06-22 01:16:57', '2024-06-22 01:16:57');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('[어울링 앱 방화벽 작업으로 인한 서비스 일시 중단 안내]', '[어울링 앱 방화벽 작업으로 인한 서비스 일시 중단 안내]<br><br>금일 22시에 어울링 앱 방화벽 관련 작업으로 인해, 1~2분간 서비스가 중단될 수 있으니 이용에 참고하여 주시기 바랍니다. 감사합니다.', '2024-06-23 15:36:54', '2024-06-23 15:36:54');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('[어진동 일부 대여소 폐쇄 안내]', '[어진동 일부 대여소 폐쇄 안내]<br>6.15(토)~6.18.(화) 간 드라마 촬영으로 인해 아래 대여소가 폐쇄되어 이용이 불가능할 예정이오니,이용에 참고하여 주시기 바랍니다.<br>이용에 불편끼쳐 드려 죄송합니다.<br><br>1) 212.어진동_호수공원수변공원<br>2) 214.세종동_호수공원매화공연장<br>3) 281.어진동_문화체육관광부(15동-3)', '2024-06-24 23:49:31', '2024-06-24 23:49:31');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('어울링 대여시, QR코드 사기(큐싱) 주의 안내', '※최근, QR코드 위에 가짜 QR코드 스티커를 덧붙여 악성어플 다운을 유도하는 큐싱범죄가 전국적으로 발생하고 있으니, 어울링 이용에 각별히 주의하여 주시기 바랍니다.<br>※QR코드 사기 예방법<br>1. 어울링을 이용할 때는 가짜 QR코드가 있는지 살펴보세요.<br>▶ 어울링 대여시 핸들에 있는 QR코드 사용 권장<br>2. 어울링 대여는 반드시 어울링 앱 실행 후 단말기의 QR 코드를 스캔해야만 대여가 가능합니다.<br>▶ 어울링 대여시 연결되는 링크 및 악성어플 다운로드 없음<br>3. 어울링 대여시 개인정보, 계좌번호를 요구하지 않습니다.<br>▶ 어울링 대여시 개인정보 및 금융정보는 절대 입력하지 마세요', now(), now());


INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test1@test.com', '$2a$10$eqnT3Kk3F1oG7twaWyfGNukYuKu5nPhb.9CcXYtm3t.4mf1bUYgW6', '운영자', '나성동', '010-1111-1111', '2024-08-08 10:00:00', 'ADMIN');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test2@test.com', '$2a$10$OTj6Mf1138pPa.OUFTuUyOvnQCnwruB3i0g7MPnI0KFE3vtTgUiqi', '홍길동', '해밀동', '010-2222-2222', '2024-09-10 12:00:00', 'USER');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test3@test.com', '$2a$10$ZXxU4kvZLd39xJP7M7VYSOhtkARFqp5JD82WCuhDdQPhOzsD4IEZe', '성춘향', '나성동', '010-3333-3333', '2024-02-03 17:00:00', 'USER');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('example1@example.com', '$2a$10$BScpMiuCqZt7qTlNbfc3GOJqTg1kgHmiGN0Ow0SrFKdKU.EpiSoCu', '김민수', '해밀동', '010-1111-2222', '2025-01-03 07:00:00', 'ADMIN');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('example2@example.com', '$2a$10$MEADSoq5T08sYfh70qxuQuyEHaMClpPcaUSziZvVGhmoVLp5YX.Ri', '황지혜', '집현동', '010-2222-1111', '2024-05-02 16:00:00', 'USER');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('example3@example.com', '$2a$10$wPbNiGpjR3nBXDKrnPFdCOPxwM5ks/AJJE5edziJGBUYXiimVwsLq', '백상현', '보람동', '010-4444-4444', '2023-12-30 19:00:00', 'USER');

INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('첫번째 공지의 첫번째 댓글입니다.', 1, 1, '2024-01-01 11:00:00', '2024-01-01 11:00:00');
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('첫번째 공지의 두번째 댓글입니다.', 1, 2, '2024-01-04 12:00:00', '2024-01-04 12:00:00');
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('첫번째 공지의 세번째 댓글입니다.', 1, 3, now(), now());

INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('두번째 공지의 첫번째 댓글입니다.', 2, 4, '2024-02-03 12:50:20', '2024-02-03 12:50:20');
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('두번째 공지의 두번째 댓글입니다.', 2, 1, '2024-02-08 13:20:00', '2024-02-08 13:20:00');
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('두번째 공지의 세번째 댓글입니다.', 2, 2, now(), now());

INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('세번째 공지의 첫번째 댓글입니다.', 3, 5, now(), now());
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('세번째 공지의 두번째 댓글입니다.', 3, 6, now(), now());
INSERT INTO notice_comment(comment, notice_id, member_id, sub_time, update_time) VALUES('세번째 공지의 세번째 댓글입니다.', 3, 5, now(), now());

INSERT INTO message(title, content, deleted_by_sender, deleted_by_receiver, sender_id, receiver_id) VALUES('1번이 3번에게', '안녕하세요?', false, false, 1, 3);
INSERT INTO message(title, content, deleted_by_sender, deleted_by_receiver, sender_id, receiver_id) VALUES('3번이 1번에게', '안녕하지 않습니다.', false, false, 3, 1);



