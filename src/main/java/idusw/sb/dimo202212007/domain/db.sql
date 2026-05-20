-- ddl(data definition): CREATE, DROP, ALTER
-- dml(data manipulation): INSERT, SELECT, UPDATE, DELETE
-- dcl(data control): GRANT, REVOKE

-- 0. 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS db_202212007;

-- 1. 데이터베이스 선택
USE db_202212007;

-- 2. 기존 테이블 삭제
DROP TABLE IF EXISTS member;

-- 3. 테이블 생성
CREATE TABLE IF NOT EXISTS member (
                                      id INT NOT NULL AUTO_INCREMENT,
                                      email VARCHAR(30) NOT NULL UNIQUE,
                                      pw VARCHAR(30) NOT NULL,
                                      fullname VARCHAR(30) NOT NULL,
                                      phone VARCHAR(20),
                                      address VARCHAR(100),
                                      PRIMARY KEY (id)
);

-- 4. 테스트 데이터 삽입
INSERT INTO member (email, pw, fullname, phone, address) VALUES
                                                             ('admin@induk.ac.kr', 'cometrue', 'admin', NULL, NULL),
                                                             ('id1@induk.ac.kr', 'cometrue', 'id1', '010-7623-9000', NULL),
                                                             ('id2@induk.ac.kr', 'cometrue', 'id2', '010-7624-9000', NULL),
                                                             ('id3@induk.ac.kr', 'cometrue', 'id3', '010-7625-9500', NULL),
                                                             ('id4@induk.ac.kr', 'cometrue', 'id4', '010-7626-9500', NULL),
                                                             ('id5@induk.ac.kr', 'cometrue', 'id5', '010-7627-9500', NULL),
                                                             ('id6@induk.ac.kr', 'cometrue', 'id6', '010-7628-9000', NULL),
                                                             ('id7@induk.ac.kr', 'cometrue', 'id7', '010-7620-9510', NULL),
                                                             ('id8@induk.ac.kr', 'cometrue', 'id8', '010-7638-9520', NULL),
                                                             ('id9@induk.ac.kr', 'cometrue', 'id9', '010-7639-9530', NULL),
                                                             ('id10@induk.ac.kr', 'cometrue', 'id10', '010-7621-9540', NULL);

-- 5. 결과 확인
SELECT * FROM member;

SELECT * FROM member WHERE phone LIKE '%9500'