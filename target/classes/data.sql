insert into user_detail (ID, NAME, BIRTH_DATE) values (1000, 'zahra', CURRENT_DATE());
insert into user_detail (ID, NAME, BIRTH_DATE) values (1001, 'amin', CURRENT_DATE());
insert into user_detail (ID, NAME, BIRTH_DATE) values (1002, 'yaser', CURRENT_DATE());

insert into post (ID, USER_ID, DESCRIPTION) values (2000, 1000, 'this is my first post');
insert into post (ID, USER_ID, DESCRIPTION) values (2001, 1000, 'this is my second post');
insert into post (ID, USER_ID, DESCRIPTION) values (2002, 1001, 'I want to learn spring boot');
insert into post (ID, USER_ID, DESCRIPTION) values (2003, 1002, 'I want to become senior developer');