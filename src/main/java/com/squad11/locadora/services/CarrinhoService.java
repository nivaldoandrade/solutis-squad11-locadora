package com.squad11.locadora.services;

import com.squad11.locadora.dtos.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.entities.Carrinho;

public interface CarrinhoService {

    Carrinho showCarrinhoById(Long id);

    Carrinho create(CreateCarrinhoDTO createCarrinhoDTO);

    Carrinho addAndUpateCarro(Long id, CreateItemCarrinhoDTO carrinhoCarroDTO);

    void deleteCarro(Long id, Long carroId);

    void deleteCarrinho(Long id);
}
