use smarthome_iot;

-- insert role
insert into role(name) values("admin");
insert into role(name) values("user");

-- insert user
insert into user(username, password, fullname, role_id)
values("admin", "123", "Admin", 1);

insert into user(username, password, fullname, role_id)
values("duongnv", "123", "Nguyễn Văn Đương", 2);
