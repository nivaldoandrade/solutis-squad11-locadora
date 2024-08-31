package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CarroDTO;
import com.squad11.locadora.dtos.CreateCarrinhoCarroDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.entities.Carrinho;
import com.squad11.locadora.entities.CarrinhoCarro;

public interface CarrinhoService {

    Carrinho show(Long id);

    Carrinho create(CreateCarrinhoDTO createCarrinhoDTO);

    Carrinho createAndUpateCarro(Long id, CreateCarrinhoCarroDTO carrinhoCarroDTO);

    void deleteCarroCarrinho(Long id, Long carroId);

    void deleteCarrinho(Long id);
}
