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
values ('bbbbbb-11-bbbbbb', 'a-b-1', 1),
       ('cccccc-11-cccccc', 'a-c-1', 1),
       ('aaaaaa-11-aaaaaa', 'b-a-1', 2),
       ('cccccc-22-cccccc', 'b-c-2', 2),
       ('aaaaaa-22-aaaaaa', 'c-a-2', 3),
       ('bbbbbb-22-bbbbbb', 'c-b-2', 3);
