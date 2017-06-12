create table users (
user_id int primary key auto_increment not null,
user_name varchar (30) not null,
address varchar (60) not null
);

create table movies (
movie_id int primary key auto_increment not null,
title varchar (50) not null,
director varchar (50) not null,
available boolean not null
);

create table orders (
order_id int primary key auto_increment not null,
ouser_id int not null,
omovie_id int not null,
date_from date,
foreign key (ouser_id) references users (user_id),
foreign key (omovie_id) references movies (movie_id)
);

insert into users (user_name, address) values ('user1', 'Krakow');
insert into users (user_name, address) values ('user2', 'Krakow');
insert into users (user_name, address) values ('user3', 'Warszawa');
insert into users (user_name, address) values ('user4', 'Lublin');

insert into movies (title, director, available) values ('Split', 'M. Night Shyamalan', true);
insert into movies (title, director, available) values ('Get Out', 'Jordan Peele', true);
insert into movies (title, director, available) values ('The Circle', 'James Ponsoldt', true);
insert into movies (title, director, available) values ('Czas surferów', 'Jacek Gąsiorowski', true);
insert into movies (title, director, available) values ('The Wizard of Lies', 'Barry Levinson', true);
insert into movies (title, director, available) values ('Life', 'Danies Espinosa', true);
insert into movies (title, director, available) values ('Logan', 'James Mangold', true);
insert into movies (title, director, available) values ('Goodfellas', 'Martin Scorsese', true);
insert into movies (title, director, available) values ('The Green Mile', 'Frank Darabont', true);
insert into movies (title, director, available) values ('The Goodfather', 'Francis Ford Coppola', true);
