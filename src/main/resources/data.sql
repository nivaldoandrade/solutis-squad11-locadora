INSERT INTO fabricante (nome) VALUES ('Toyota');
INSERT INTO fabricante (nome) VALUES ('Honda');
INSERT INTO fabricante (nome) VALUES ('Ford');


INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Corolla', 'SEDAN_MEDIO', 1);
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Civic', 'SEDAN_MEDIO', 2);
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Mustang', 'ESPORTIVO', 3);

INSERT INTO carro (placa, chassi, valor_diario, modelo_id, status) VALUES ('ABC1234', '1HGCM82633A004352', 150.00, 1, 'DISPONIVEL');
INSERT INTO carro (placa, chassi, valor_diario, modelo_id, status) VALUES ('XYZ5678', '1HGCM82633A004353', 180.00, 2, 'DISPONIVEL');
INSERT INTO carro (placa, chassi, valor_diario, modelo_id, status) VALUES ('LMN9012', '1HGCM82633A004354', 250.00, 3, 'DISPONIVEL');

INSERT INTO acessorio (descricao) VALUES ('Ar Condicionado');
INSERT INTO acessorio (descricao) VALUES ('Airbag');
INSERT INTO acessorio (descricao) VALUES ('Freios ABS');

INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (1, 1); -- Carro 1 com Ar Condicionado
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (1, 2); -- Carro 1 com Airbag
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (2, 1); -- Carro 2 com Ar Condicionado
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (2, 3); -- Carro 2 com Freios ABS
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (3, 2); -- Carro 3 com Airbag
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (3, 3); -- Carro 3 com Freios ABS


-- Inserir uma apólice com valor de franquia de 500 e todas as proteções ativadas
INSERT INTO apolice (valor_franquia, protecao_terceiro, protecao_causas_naturais, protecao_roubo)
VALUES (500.00, TRUE, TRUE, TRUE);

-- Inserir uma apólice com valor de franquia de 1000 e proteções básicas ativadas
INSERT INTO apolice (valor_franquia, protecao_terceiro, protecao_causas_naturais, protecao_roubo)
VALUES (1000.00, TRUE, FALSE, TRUE);

-- Inserir uma apólice com valor de franquia de 200 e apenas proteção contra roubo ativada
INSERT INTO apolice (valor_franquia, protecao_terceiro, protecao_causas_naturais, protecao_roubo)
VALUES (200.00, FALSE, FALSE, TRUE);

-- Inserir uma apólice com valor de franquia de 750 e proteção contra terceiros ativada
INSERT INTO apolice (valor_franquia, protecao_terceiro, protecao_causas_naturais, protecao_roubo)
VALUES (750.00, TRUE, FALSE, FALSE);