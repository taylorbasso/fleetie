create table customers
(
    id            bigint      not null primary key auto_increment,
    uuid          binary(16)  not null,
    name          varchar(64) not null,
    enabled       boolean     not null,
    modified_time datetime             default now(),
    created_time  datetime    not null default now()
);
create unique index ix_uuid on customers (uuid);
create unique index ix_name on customers (uuid);

create table users
(
    id            bigint      not null primary key auto_increment,
    uuid          binary(16)  not null,
    username      varchar(64) not null,
    firstName     varchar(64) not null,
    lastName      varchar(64) not null,
    password      varchar(64) not null,
    enabled       boolean     not null,
    customer_id   bigint      not null,
    modified_time datetime,
    created_time  datetime default now(),
    constraint fk_customer_id foreign key (customer_id) references customers (id)
);
create unique index ix_username on users (username);
create unique index ix_uuid on users (uuid);

create table roles
(
    id       bigint      not null primary key auto_increment,
    role     varchar(64) not null
);
insert into roles values (null, 'ADMIN'), (null, 'CUSTOMER');

create table user_roles (
    id bigint not null primary key auto_increment,
    user_id bigint not null,
    role_id bigint not null,
    constraint fk_user_id foreign key (user_id) references users (id),
    constraint fk_role_id foreign key (role_id) references roles (id)
)