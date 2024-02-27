create table users
(
    id bigint not null primary key auto_increment,
    uuid binary(16) not null,
    username varchar(64) not null,
    password varchar(64) not null,
    enabled  boolean     not null,
    modified_time datetime not null default now(),
    created_time datetime
);
create unique index ix_username on users(username);

create table authorities
(
    id bigint not null primary key auto_increment,
    username  varchar(64) not null,
    authority varchar(64) not null,
    constraint fk_authorities_users foreign key (username) references users (username)
);
create unique index ix_username_authority on authorities (username, authority);