INSERT INTO notice(title, content, sub_time, update_time) VALUES('첫번째 공지사항', '첫번째 공지사항의 내용입니다.', '2024-01-01 10:00:00', '2024-01-01 10:00:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('두번째 공지사항', '두번째 공지사항의 내용입니다.', '2024-02-02 12:30:00', '2024-02-02 12:30:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('세번째 공지사항', '세번째 공지사항의 내용입니다.', '2024-03-05 17:30:00', '2024-03-05 17:30:00');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('네번째 공지사항', '네번째 공지사항의 내용입니다.', '2024-04-13 14:28:10', '2024-04-13 14:28:10');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('다섯번째 공지사항', '다섯번째 공지사항의 내용입니다.', '2024-05-24 21:46:23', '2024-05-24 21:46:23');
INSERT INTO notice(title, content, sub_time, update_time) VALUES('여섯번째 공지사항', '여섯번째 공지사항의 내용입니다.', now(), now());


INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test1@test.com', '$2a$10$eqnT3Kk3F1oG7twaWyfGNukYuKu5nPhb.9CcXYtm3t.4mf1bUYgW6', '운영자', '나성동', '010-1111-1111', '2024-08-08 10:00:00', 'ADMIN');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test2@test.com', '$2a$10$OTj6Mf1138pPa.OUFTuUyOvnQCnwruB3i0g7MPnI0KFE3vtTgUiqi', '성춘향', '해밀동', '010-2222-2222', '2024-09-10 12:00:00', 'USER');
INSERT INTO member(email, password, name, address, phone_num, ticket_expire_date, role) VALUES('test3@test.com', '$2a$10$ZXxU4kvZLd39xJP7M7VYSOhtkARFqp5JD82WCuhDdQPhOzsD4IEZe', '이몽룡', '나성동', '010-3333-3333', '2024-02-03 17:00:00', 'USER');
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



