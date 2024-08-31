INSERT INTO fabricante (nome) VALUES ('Toyota');
INSERT INTO fabricante (nome) VALUES ('Honda');
INSERT INTO fabricante (nome) VALUES ('Ford');


INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Corolla', 'SEDAN_MEDIO', 1);
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Civic', 'SEDAN_MEDIO', 2);
INSERT INTO modelo_carro (descricao, categoria, fabricante_id) VALUES ('Mustang', 'ESPORTIVO', 3);

INSERT INTO carro (placa, chassi, valor_diario, modelo_id) VALUES ('ABC1234', '1HGCM82633A004352', 150.00, 1);
INSERT INTO carro (placa, chassi, valor_diario, modelo_id) VALUES ('XYZ5678', '1HGCM82633A004353', 180.00, 2);
INSERT INTO carro (placa, chassi, valor_diario, modelo_id) VALUES ('LMN9012', '1HGCM82633A004354', 250.00, 3);

INSERT INTO acessorio (descricao) VALUES ('Ar Condicionado');
INSERT INTO acessorio (descricao) VALUES ('Airbag');
INSERT INTO acessorio (descricao) VALUES ('Freios ABS');

INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (1, 1); -- Carro 1 com Ar Condicionado
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (1, 2); -- Carro 1 com Airbag
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (2, 1); -- Carro 2 com Ar Condicionado
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (2, 3); -- Carro 2 com Freios ABS
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (3, 2); -- Carro 3 com Airbag
INSERT INTO carro_acessorios (carro_id, acessorio_id) VALUES (3, 3); -- Carro 3 com Freios ABS