insert into users(username, password, enabled) values ( 'user', '$2a$12$FZWvunKyXzLa5L9mtMjcH.eGVokx3wbJcUuHJ60oJdZckJhEEUPPK', true );
insert into users(username, password, enabled) values ( 'admin', '$2a$12$FZWvunKyXzLa5L9mtMjcH.eGVokx3wbJcUuHJ60oJdZckJhEEUPPK', true );

insert into authorities(username, authority) values ( 'user', 'USER' );
insert into authorities(username, authority) values ( 'admin', 'ADMIN' );

insert into MEMBER (USER_ID, name, password, role, HEIGHT, RATING)
values ('hoo788', 'leegyuchan',
        '$2a$12$FZWvunKyXzLa5L9mtMjcH.eGVokx3wbJcUuHJ60oJdZckJhEEUPPK', 'USER', 175.0, 3);

insert into MEMBER (USER_ID, name, password, role, HEIGHT, RATING)
values ('dodobird', 'sonheungmin',
        '$2a$12$FZWvunKyXzLa5L9mtMjcH.eGVokx3wbJcUuHJ60oJdZckJhEEUPPK', 'ADMIN', 185.0, 5);