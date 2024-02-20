drop table if exists authorities;
drop table if exists users;

create table users(
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null -- 계정 활성화 여부
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null, -- 권한
    constraint fk_authorities_users foreign key (username) references users(username)
);

create unique index ix_auth_username on authorities (username,authority);
-- 유니크 인덱스 걸기