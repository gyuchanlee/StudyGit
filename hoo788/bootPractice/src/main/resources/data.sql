insert into users(username, password, enabled) values ( 'user', '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', true );
insert into users(username, password, enabled) values ( 'admin', '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', true );

insert into authorities(username, authority) values ( 'user', 'USER' );
insert into authorities(username, authority) values ( 'admin', 'ADMIN' );

insert into MEMBER (USER_ID, name, email, password, role, HEIGHT, RATING)
values ('hoo788', 'leegyuchan', 'hoo788@google.com',
        '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', 'USER', 175.0, 3);

insert into MEMBER (USER_ID, name, email, password, role, HEIGHT, RATING)
values ('dodobird', 'sonheungmin',  'dodobird@naver.com',
        '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', 'ADMIN', 185.0, 5);

insert into MEMBER (USER_ID, name, email, password, role, HEIGHT, RATING)
values ('test', 'tester',  'tester@google.com',
        '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', 'USER2', 185.0, 5);