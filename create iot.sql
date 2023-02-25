drop schema if exists smarthome_iot;
create schema smarthome_iot;

use smarthome_iot;

drop table if exists role;
create table role (
	id bigint primary key not null auto_increment,
    name varchar(255)
);

drop table if exists user;
create table user (
	id bigint primary key not null auto_increment,
    username varchar(255) null,
    password varchar(255) null,
    fullname varchar(255) null,
    role_id bigint null
);

alter table user add constraint fk_user_role foreign key(role_id) references role(id);

drop table if exists home;
create table home (
	id bigint primary key not null auto_increment,
    name varchar(255) null,
    location varchar(255) null
);

drop table if exists user_home;
create table user_home (
	id bigint primary key not null auto_increment,
    user_id bigint not null,
    home_id bigint not null
);

alter table user_home add constraint fk_user_id foreign key(user_id) references user(id);
alter table user_home add constraint fk_home_id foreign key(home_id) references home(id);

drop table if exists room;
create table room (
	id bigint primary key not null auto_increment,
    name varchar(255) null,
    home_id bigint null
);

alter table room add constraint fk_room_home foreign key(home_id) references home(id);

drop table if exists device;
create table device (
	id bigint primary key not null auto_increment,
    room_id bigint not null,
    status boolean
);

alter table device add constraint fk_device_room foreign key(room_id) references room(id);

drop table if exists sensor_data;
create table sensor_data (
	id bigint primary key not null auto_increment,
    device_id bigint not null,
    location varchar(255) null,
    temp double null,
    humid double null,
    time datetime null
);

alter table sensor_data add constraint fk_sensor_data_device foreign key(device_id) references device(id);