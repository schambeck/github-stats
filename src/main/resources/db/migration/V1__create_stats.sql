create sequence "seq_stats";

create table "stats"
(
    "id"         bigint not null,
    "repository" varchar(255),
    "user"       varchar(255),
    primary key ("id")
);
