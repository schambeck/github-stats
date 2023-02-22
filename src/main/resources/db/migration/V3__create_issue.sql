create sequence "seq_issue";

create table "issue"
(
    "id"         bigint not null,
    "author"     varchar(255),
    "title"      varchar(255),
    "stats_id" bigint references "stats",
    primary key ("id")
);
