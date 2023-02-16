insert into member(member_email, member_name, member_password)
values ('a@gmail.com', 'a', 'a'),
       ('b@gmail.com', 'b', 'b'),
       ('c@gmail.com', 'c', 'c');

insert into account(account_money, account_number, member_id)
values (10000, 'aaaaaa-11-aaaaaa', 1),
       (20000, 'aaaaaa-22-aaaaaa', 1),
       (10000, 'bbbbbb-11-bbbbbb', 2),
       (20000, 'bbbbbb-22-bbbbbb', 2),
       (10000, 'cccccc-11-cccccc', 3),
       (20000, 'cccccc-22-cccccc', 3);

insert into friend(friend_account_number, friend_name, member_id)
values ('ffffff-11-aaaaaa', 'f-a', 1),
       ('ffffff-22-aaaaaa', 'f-a', 1),
       ('ffffff-11-bbbbbb', 'f-b', 2),
       ('ffffff-22-bbbbbb', 'f-b', 2),
       ('ffffff-11-cccccc', 'f-c', 3),
       ('ffffff-22-cccccc', 'f-c', 3);
