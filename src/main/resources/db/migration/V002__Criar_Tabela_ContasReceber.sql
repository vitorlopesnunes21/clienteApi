create table contasreceber(
    id int not null auto_increment primary key,
    dataconta date not null,
    idcliente int not null,
    valorconta decimal(12,2) not null
);