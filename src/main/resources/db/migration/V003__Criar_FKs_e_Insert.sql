alter table contasreceber add constraint fk_contasreceber_cliente foreign key(idcliente) references contasreceber(id);

insert into contasreceber(dataconta, idcliente, valorconta) values('2016-03-01', 1, 1500.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2017-04-22', 2, 3000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2018-05-29', 3, 9000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2019-02-24', 4, 18000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2020-08-04', 5, 36000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2021-10-14', 6, 72000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2022-01-30', 7, 144000.00);
insert into contasreceber(dataconta, idcliente, valorconta) values('2023-06-06', 8, 288000.00);