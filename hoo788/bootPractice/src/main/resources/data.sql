insert into users(username, password, enabled) values ( 'user', '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', true );
insert into users(username, password, enabled) values ( 'admin', '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', true );

insert into authorities(username, authority) values ( 'user', 'USER' );
insert into authorities(username, authority) values ( 'admin', 'ADMIN' );

insert into MEMBER (USER_ID, name, password, role, HEIGHT, RATING)
values ('hoo788', 'leegyuchan',
        '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', 'ROLE_USER', 175.0, 3);

insert into MEMBER (USER_ID, name, password, role, HEIGHT, RATING)
values ('dodobird', 'sonheungmin',
        '$2a$12$sj2CgldV4sybiH7mZY/tZ.qF6VlMG1NNOs.xiw1rhKbMlO3Tp4DpS', 'ROLE_ADMIN', 185.0, 5);