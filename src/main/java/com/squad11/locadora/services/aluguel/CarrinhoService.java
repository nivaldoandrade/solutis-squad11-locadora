package com.squad11.locadora.services.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.aluguel.request.CreateCarrinhoDTO;
import com.squad11.locadora.entities.aluguel.Carrinho;

public interface CarrinhoService {

    Carrinho showCarrinhoById(Long id);

    Carrinho create(CreateCarrinhoDTO createCarrinhoDTO);

    Carrinho addAndUpateCarro(Long id, CreateItemCarrinhoDTO carrinhoCarroDTO);

    void deleteCarro(Long id, Long carroId);

    void deleteCarrinho(Long id);
}
