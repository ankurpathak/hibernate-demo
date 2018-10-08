CREATE TABLE users (
  id int auto_increment,
  first_name varchar,
  last_name varchar,
  email varchar not null,
  constraint users_id_pk primary key(id),
  constraint users_email_ck unique(email)
);