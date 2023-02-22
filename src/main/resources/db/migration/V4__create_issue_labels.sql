create table "issue_labels"
(
    "issue_id" bigint not null references "issue",
    "labels"   varchar(255)
);
