create sequence "seq_contributor";

create table "contributor"
(
    "id"          bigint not null,
    "name"        varchar(255),
    "qty_commits" bigint,
    "user"        varchar(255),
    "stats_id"  bigint references "stats",
    primary key ("id")
);
