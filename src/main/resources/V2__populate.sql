insert into users (id) values (1);
insert into accounts (id, user_id, name, account_status) VALUES (1,1,'Yura', 'ACTIVE');
insert into files (id, user_id, file_status) values (1,1,'ACTIVE');
insert into events (id,user_id, name) VALUES (1,1,'Miting');
insert into access (id,email,first_name,last_name,password,role,status)
 VALUES (1,'admin@mail.ru','Admin','Adminov',
         '$2y$12$PEiyzJ0PePdlFfX3jJAwgOGG1nMdMZyEXCYWwsaozWx1C97YHfF7u',
         'ADMIN','ACTIVE'),
        (2,'user@mail.ru','User','Userov',
         '$2y$12$Ud1AwtXOJ21faUqk4FSXS.mtooIIDLWYyfEb0JXkeUYiqAaC4lS8q',
         'USER','BANNED');