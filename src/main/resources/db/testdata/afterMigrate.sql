set foreign_key_checks = 0;

DELETE FROM cidade;
DELETE FROM cozinha;
DELETE FROM estado;
DELETE FROM forma_pagamento;
DELETE FROM grupo;
DELETE FROM grupo_permissao;
DELETE FROM permissao;
DELETE FROM produto;
DELETE FROM restaurante;
DELETE FROM restaurante_forma_pagamento;
DELETE FROM usuario;
DELETE FROM usuario_grupo;

set foreign_key_checks = 1;

ALTER TABLE cidade auto_increment = 1;
ALTER TABLE cozinha auto_increment = 1;
ALTER TABLE estado auto_increment = 1;
ALTER TABLE forma_pagamento auto_increment = 1;
ALTER TABLE grupo auto_increment = 1;
ALTER TABLE permissao auto_increment = 1;
ALTER TABLE produto auto_increment = 1;
ALTER TABLE restaurante auto_increment = 1;
ALTER TABLE usuario auto_increment = 1;

INSERT INTO cozinha (nome) VALUES ("Tailandesa");
INSERT INTO cozinha (nome) VALUES ("Indiana");

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Uberlândia', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Fortaleza', 3);

insert into restaurante (nome, taxa_frete, cozinha_id, endereco_cidade_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, data_cadastro, data_atualizacao) values ('Thai Gourmet', 10, 1, 3, "Presidente Altino", "06213-040", "torre 1 apto 74", "Rua Zuma de Sá Fernandes", "323", utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values ('Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp);

insert into forma_pagamento (descricao) values ('Cartão de crédito');
insert into forma_pagamento (descricao) values ('Cartão de débito');
insert into forma_pagamento (descricao) values ('Dinheiro');

insert into permissao (nome, descricao) values ('CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (nome, descricao) values ('EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,1), (3,1), (3,2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 30.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 15.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 03", "Descricao produto 03", 10.00, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 15.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 10.00, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 5.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 10.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 03", "Descricao produto 03", 15.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 04", "Descricao produto 04", 20.00, true, 3); 