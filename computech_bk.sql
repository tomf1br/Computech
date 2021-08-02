
/**
* projeto 4: Ordem de serviços - Computech
* Versão 1.0
* @author Igor Jordao / Jerry Ferreira
**/


create database dbcomputech;
use dbcomputech;
show tables;


create table tbusuarios (
    iduser int primary key auto_increment,
    usuario varchar(50) not null,
    login varchar(15) not null unique,
    senha varchar(250) not null,
    perfil varchar(20) not null
);


describe tbusuarios;
select * from tbusuarios;
insert into tbusuarios(usuario,login,senha,perfil)
values ('Sirlene','Sirlene',md5('123'),'user');
-- md5 = comando para criptografar conteúdo

insert into tbusuarios(usuario,login,senha,perfil)
values ('Robson','vava',md5('1234'),'admin');

insert into tbusuarios(usuario,login,senha,perfil)
values ('José','admin',md5('123456'),'admin');

-- autenticando (recuperando) usuário e senha / tela de login
select * from tbusuarios where login = 'sirlene' and senha = md5('123');
select * from tbusuarios where login = 'admin' and senha = md5('123456');

select * from tbusuarios;

delete from tbusuarios where iduser='6';

create table tbcliente (
    idcli int primary key auto_increment,
    nomecli varchar(50) not null,
    cep varchar(9),
    logradouro varchar(100) not null,
    numero varchar(10)not null,
    complemento varchar(20),
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf char(2),
    rg varchar(15) unique,
    cpf varchar(15) not null unique,
    fonecli varchar(15) not null,
    fonecli2 varchar(15),
    emailcli varchar(50),
    obs varchar(250)
);

select * from tbcliente;

describe tbcliente;

insert into tbcliente (nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs)
values ('Joaquim','01250000', 'Rua Tuiuti','279','Casa 02','Tatuapé','São Paulo','SP','25135987','25436598751','32569-5587','99857-4758','joaquim@email.com','Cliente VIP');

insert into tbcliente (nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs)
values ('João','01250001', 'Rua Aurora','371','Sem Complemento','Sta Igigênia','São Paulo','SP','25136958','52149856254','3226-2255','98547-2896','joao@email.com','Cliente Mea Boca');

insert into tbcliente (nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs)
values ('José','03589-520', 'Rua Amazonas','1987','Casa Fundos','Marezinha','Amazonas','AM','65987524','56985647812','85475-4433','91368-5547','jose@email.com','Cliente Caroço');

insert into tbcliente (nomecli,cep,logradouro,numero,complemento,bairro,cidade,uf,rg,cpf,fonecli,fonecli2,emailcli,obs)
values ('Jorge Ramos','85698010', 'Rua Bolivia','1237','Ap 02','Tiradentes','Espirito Santo','ES','65287458','51245896574','4636-2887','99237-8852','jorge@email.com','Cliente VIP');

-- consulta com filtro ( pelas letras )

select * from tbcliente where nomecli like 'jo%';


create table tbos (
    os int primary key auto_increment,
    dataOs timestamp default current_timestamp,
    tipoOs varchar(15) not null,
    statusOs varchar(20) not null,
    tecnico varchar(30) not null,
    modelo varchar(50) not null,
    marca varchar(50) not null,
    serialNumber varchar(50),
    senha varchar(20),
    complementos varchar(150),
    sistema varchar(15) not null,
    defeitoCli varchar(250) not null,
    diagnostico varchar(250),
    valor decimal(10,2),
    sinal decimal(10,2),
    dataPrevista date,
    dataRetirada date,
    formPag varchar(20),
    garantiaData date,
    idcli int not null,
    foreign key(idcli) references tbcliente(idcli)
);

describe tbos;

create table tbTecnicos(
idtec int primary key auto_increment,
nomeTec varchar(30) not null);

select * from tbTecnicos;

describe tbTecnicos;










