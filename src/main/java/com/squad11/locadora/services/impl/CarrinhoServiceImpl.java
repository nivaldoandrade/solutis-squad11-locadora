package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateCarrinhoCarroDTO;
import com.squad11.locadora.dtos.CreateCarrinhoDTO;
import com.squad11.locadora.entities.*;
import com.squad11.locadora.exceptions.*;
import com.squad11.locadora.repositories.CarrinhoCarroRepository;
import com.squad11.locadora.repositories.CarrinhoRepository;
import com.squad11.locadora.repositories.CarroRepository;
import com.squad11.locadora.services.ApoliceService;
import com.squad11.locadora.services.CarrinhoService;
import com.squad11.locadora.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static com.squad11.locadora.utils.DateUtils.formatStringToDate;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    @Autowired
    CarroRepository carroRepository;

    @Autowired
    CarrinhoRepository carrinhoRepository;

    @Autowired
    CarrinhoCarroRepository carrinhoCarroRepository;

    @Autowired
    MotoristaService motoristaService;

    @Autowired
    ApoliceService apoliceService;

    @Override
    public Carrinho show(Long id) {
        return this.findById(id);
    }

    @Transactional
    @Override
    public Carrinho create(CreateCarrinhoDTO createCarrinhoDTO) {

        Long motoristaId = createCarrinhoDTO.motoristaId();

        Motorista motorista = motoristaService.findById(motoristaId);

        if(!motorista.isAtivo()) {
            throw new UnconfirmedRegistrationException();
        }

        Optional<Carrinho> existingCarrinho = carrinhoRepository.findById(motoristaId);

        Carrinho newCarrinho = existingCarrinho
                .orElseGet(() -> carrinhoRepository.save(new Carrinho(motorista)));

        if(createCarrinhoDTO.carro() != null) {
            createCarrinhoCarro(createCarrinhoDTO.carro(), newCarrinho);
        }

        return newCarrinho;
    }

    @Override
    public Carrinho createAndUpateCarro(Long id, CreateCarrinhoCarroDTO carrinhoCarroDTO) {

        Optional<Carrinho> existingCarrinho = carrinhoRepository.findById(id);

        if(existingCarrinho.isEmpty()) {
            throw new CartNotFoundException();
        }

        createCarrinhoCarro(carrinhoCarroDTO, existingCarrinho.get());


        return existingCarrinho.get();
    }

    @Transactional
    @Override
    public void deleteCarroCarrinho(Long id, Long carroId) {
        Optional<Carrinho> existingCarrinho = carrinhoRepository.findById(id);

        if(existingCarrinho.isEmpty()) {
            throw new CarInCartNotFoundException();
        }

        Carrinho carrinho = existingCarrinho.get();

        Optional<CarrinhoCarro> carro = carrinho.getCarrinhoCarros()
                .stream().filter(c -> c.getCarro().getId().equals(carroId)).findFirst();

        if (carro.isEmpty()) {
            throw new CarInCartNotFoundException();
        }

        carrinhoCarroRepository.delete(carro.get());
    }

    @Override
    public void deleteCarrinho(Long id) {
        Optional<Carrinho> existingCarrinho = carrinhoRepository.findById(id);

        if(existingCarrinho.isEmpty()) {
            throw new CartNotFoundException();
        }

        carrinhoRepository.delete(existingCarrinho.get());
    }

    private Carrinho findById(Long carrinhoId) {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(CartNotFoundException::new);
    }

    private void createCarrinhoCarro(CreateCarrinhoCarroDTO carrinhoCarroDTO, Carrinho carrinho) {
        LocalDate dataInicio = formatStringToDate(carrinhoCarroDTO.dataInicio());
        LocalDate dataTermino = formatStringToDate(carrinhoCarroDTO.dataTermino());

        Long apoliceId = carrinhoCarroDTO.apoliceId();

        Apolice apolice = apoliceService.findById(apoliceId);

        Optional<Carro> carro = carroRepository.findById(carrinhoCarroDTO.carroId());

        if(carro.isEmpty()) {
            throw new CarNotFoundException();
        }

        Optional<CarrinhoCarro> apoliceEmUso = carrinhoCarroRepository.findByApolice(apolice);

        if(apoliceEmUso.isPresent() && !apoliceEmUso.get().getCarro().equals(carro.get())) {
            throw new PolicyAlreadyInUseException();
        }


        CarrinhoCarro carrinhoCarro = new CarrinhoCarro(
                carrinho,
                carro.get(),dataInicio,
                dataTermino,
                apolice
        );

        carrinho.getCarrinhoCarros().add(carrinhoCarro);

        carrinhoCarroRepository.save(carrinhoCarro);
    }

}
