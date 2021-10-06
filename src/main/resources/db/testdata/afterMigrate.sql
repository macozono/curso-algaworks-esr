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
DELETE FROM restaurante_usuario_responsavel;
DELETE FROM pedido;
DELETE FROM item_pedido;
DELETE FROM foto_produto;
DELETE FROM oauth_client_details;

set foreign_key_checks = 1;

ALTER TABLE pedido auto_increment = 1;
ALTER TABLE item_pedido auto_increment = 1;
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
INSERT INTO cozinha (nome) VALUES ("Brasileira");
INSERT INTO cozinha (nome) VALUES ("Americana");

insert into estado (nome) values ('Minas Gerais');
insert into estado (nome) values ('São Paulo');
insert into estado (nome) values ('Ceará');

insert into cidade (nome, estado_id) values ('Uberlândia', 1);
insert into cidade (nome, estado_id) values ('Belo Horizonte', 1);
insert into cidade (nome, estado_id) values ('São Paulo', 2);
insert into cidade (nome, estado_id) values ('Campinas', 2);
insert into cidade (nome, estado_id) values ('Fortaleza', 3);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 3, utc_timestamp, utc_timestamp, true, true);

insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão de crédito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Cartão de débito', utc_timestamp);
insert into forma_pagamento (descricao, data_atualizacao) values ('Dinheiro', utc_timestamp);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
insert into permissao (id, nome, descricao) values (3, 'CONSULTAR_FORMAS_PAGAMENTO', 'Permite consultar formas de pagamento');
insert into permissao (id, nome, descricao) values (4, 'EDITAR_FORMAS_PAGAMENTO', 'Permite criar ou editar formas de pagamento');
insert into permissao (id, nome, descricao) values (5, 'CONSULTAR_CIDADES', 'Permite consultar cidades');
insert into permissao (id, nome, descricao) values (6, 'EDITAR_CIDADES', 'Permite criar ou editar cidades');
insert into permissao (id, nome, descricao) values (7, 'CONSULTAR_ESTADOS', 'Permite consultar estados');
insert into permissao (id, nome, descricao) values (8, 'EDITAR_ESTADOS', 'Permite criar ou editar estados');
insert into permissao (id, nome, descricao) values (9, 'CONSULTAR_USUARIOS', 'Permite consultar usuários');
insert into permissao (id, nome, descricao) values (10, 'EDITAR_USUARIOS', 'Permite criar ou editar usuários');
insert into permissao (id, nome, descricao) values (11, 'CONSULTAR_RESTAURANTES', 'Permite consultar restaurantes');
insert into permissao (id, nome, descricao) values (12, 'EDITAR_RESTAURANTES', 'Permite criar, editar ou gerenciar restaurantes');
insert into permissao (id, nome, descricao) values (13, 'CONSULTAR_PRODUTOS', 'Permite consultar produtos');
insert into permissao (id, nome, descricao) values (14, 'EDITAR_PRODUTOS', 'Permite criar ou editar produtos');
insert into permissao (id, nome, descricao) values (15, 'CONSULTAR_PEDIDOS', 'Permite consultar pedidos');
insert into permissao (id, nome, descricao) values (16, 'GERENCIAR_PEDIDOS', 'Permite gerenciar pedidos');
insert into permissao (id, nome, descricao) values (17, 'GERAR_RELATORIOS', 'Permite gerar relatórios');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (1,3), (2,1), (3,1), (3,2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 30.00, true, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 15.00, false, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 03", "Descricao produto 03", 10.00, true, 1);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 15.00, true, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 10.00, true, 2);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 01", "Descricao produto 01", 5.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 02", "Descricao produto 02", 10.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 03", "Descricao produto 03", 15.00, true, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ("Produto 04", "Descricao produto 04", 20.00, true, 3); 

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

# Adiciona todas as permissoes no grupo do gerente
insert into grupo_permissao (grupo_id, permissao_id)
select 1, id from permissao;

# Adiciona permissoes no grupo do vendedor
insert into grupo_permissao (grupo_id, permissao_id)
select 2, id from permissao where nome like 'CONSULTAR_%';

insert into grupo_permissao (grupo_id, permissao_id) values (2, 14);

# Adiciona permissoes no grupo do auxiliar
insert into grupo_permissao (grupo_id, permissao_id)
select 3, id from permissao where nome like 'CONSULTAR_%';

# Adiciona permissoes no grupo cadastrador
insert into grupo_permissao (grupo_id, permissao_id)
select 4, id from permissao where nome like '%_RESTAURANTES' or nome like '%_PRODUTOS';

insert into usuario (id, nome, email, senha, data_cadastro) values
	(1, 'Marcel Cozono', 'marcel.cozono@gmail.com', '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG', utc_timestamp),
	(2, 'Maria Joaquina', 'maria.vnd@algafood.com', '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG', utc_timestamp),
	(3, 'José Souza', 'jose.aux@algafood.com', '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG', utc_timestamp),
	(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG', utc_timestamp);
	
	
insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '123', utc_timestamp);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
    status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, 'a1de8a73-8fa4-4c20-83c3-a510e6a92fa0', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');


insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro,
        status, data_criacao, subtotal, taxa_frete, valor_total) 
values (2, '93381de3-6125-4873-839c-4ece0ff0915b', 4, 1, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (3, 2, 6, 1, 79, 79, 'Ao ponto');

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'algafood-web', null, '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG',
  'READ,WRITE', 'password', null, null,
  60 * 60 * 6, 60 * 24 * 60 * 60, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'foodanalytics', null, '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG',
  'READ,WRITE', 'authorization_code', 'http://www.foodanalytics.local:8082', null,
  null, null, null
);

insert into oauth_client_details (
  client_id, resource_ids, client_secret, 
  scope, authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove
)
values (
  'faturamento', null, '$2a$12$wY3BDr8HCwS7Pi1TyVABMezXaTn6T/ufi0JgzkRdEKiiv9xvrAPnG',
  'READ,WRITE', 'client_credentials', null, 'CONSULTAR_PEDIDOS,GERAR_RELATORIOS',
  null, null, null
);