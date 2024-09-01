package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateItemCarrinhoDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.entities.*;
import com.squad11.locadora.exceptions.*;
import com.squad11.locadora.repositories.itemCarrinhoRepository;
import com.squad11.locadora.repositories.CarrinhoRepository;
import com.squad11.locadora.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.squad11.locadora.utils.DateUtils.formatStringToDate;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    CarroService carroService;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    itemCarrinhoRepository itemCarrinhoRepository;

    @Autowired
    MotoristaService motoristaService;

    @Autowired
    ApoliceService apoliceService;


    @Override
    public Carrinho showCarrinhoById(Long id) {
        return this.findCarrinhoById(id);
    }

    @Transactional
    @Override
    public Carrinho create(CreateCarrinhoDTO createCarrinhoDTO) {

        Long motoristaId = createCarrinhoDTO.motoristaId();

        Motorista motorista = motoristaService.findByIdAtivo(motoristaId);

        Optional<Carrinho> existingCarrinho = carrinhoRepository.findById(motoristaId);

        Carrinho newCarrinho = existingCarrinho
                .orElseGet(() -> carrinhoRepository.save(new Carrinho(motorista)));

        if(createCarrinhoDTO.carro() != null) {
            createItemCarrinho(createCarrinhoDTO.carro(), newCarrinho);
        }

        return newCarrinho;
    }

    @Override
    public Carrinho addAndUpateCarro(Long id, CreateItemCarrinhoDTO itemCarrinhoDTO) {

       Carrinho existingCarrinho = this.findCarrinhoById(id);

        createItemCarrinho(itemCarrinhoDTO, existingCarrinho);

        return existingCarrinho;
    }

    @Transactional
    @Override
    public void deleteCarro(Long carrinhoId, Long carroId) {
        Carrinho existingCarrinho = findCarrinhoById(carrinhoId);

        Carrinho carrinho = existingCarrinho;

        Optional<ItemCarrinho> itemCarrinho = carrinho.getItemCarrinhos()
                .stream().filter(c -> c.getCarro().getId().equals(carroId)).findFirst();

        if (itemCarrinho.isEmpty()) {
            throw new CarInCartNotFoundException();
        }

        carrinho.removeItemCarrinho(itemCarrinho.get());

        itemCarrinhoRepository.delete(itemCarrinho.get());
    }

    @Override
    public void deleteCarrinho(Long carrinhoId) {
        Carrinho existingCarrinho = this.findCarrinhoById(carrinhoId);

        carrinhoRepository.delete(existingCarrinho);
    }

    private Carrinho findCarrinhoById(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(CartNotFoundException::new);
    }

    private void createItemCarrinho(CreateItemCarrinhoDTO itemCarrinhoDTO, Carrinho carrinho) {
        LocalDate dataInicio = formatStringToDate(itemCarrinhoDTO.dataInicio());
        LocalDate dataTermino = formatStringToDate(itemCarrinhoDTO.dataTermino());

        Carro carro = carroService.findByIdDisponivel(itemCarrinhoDTO.carroId());

        Long apoliceId = itemCarrinhoDTO.apoliceId();

        Apolice apolice = apoliceService.findById(apoliceId);

        if (apolice.getAluguel() != null) {
            throw new PolicyAlreadyInUseException(apoliceId);
        }

        carrinho.getItemCarrinhos()
                .stream()
                .filter(i -> i.getApolice().equals(apolice))
                .findFirst()
                .ifPresent(item -> {
                    throw new PolicyAlreadyInUseException(apolice.getId());
                });


        ItemCarrinho itemCarrinho = new ItemCarrinho(
                carrinho,
                carro,
                dataInicio,
                dataTermino,
                apolice
        );


        carrinho.addItemCarrinho(itemCarrinho);

        itemCarrinhoRepository.save(itemCarrinho);
    }

}
