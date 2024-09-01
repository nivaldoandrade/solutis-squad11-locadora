package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateAluguelDTO;
import com.squad11.locadora.entities.*;
import com.squad11.locadora.exceptions.*;
import com.squad11.locadora.repositories.AluguelRepository;
import com.squad11.locadora.services.AluguelService;
import com.squad11.locadora.services.ApoliceSeguroService;
import com.squad11.locadora.services.CarrinhoService;
import com.squad11.locadora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AluguelServiceImpl implements AluguelService {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ApoliceSeguroService apoliceSeguroService;

    @Autowired
    AluguelRepository aluguelRepository;

    @Autowired
    CarroService carroService;

    @Override
    public Aluguel show(Long aluguelId) {
        return aluguelRepository.findById(aluguelId)
                .orElseThrow(RentNotFoundException::new);
    }

    @Transactional
    @Override
    public List<Aluguel> create(Long carrinhoId, CreateAluguelDTO createAluguelDTO) {
        Carrinho carrinho = carrinhoService.showCarrinhoById(carrinhoId);

        if(carrinho.getItemCarrinhos().isEmpty()) {
            throw new EmptyCartException();
        }

        Motorista motorista = carrinho.getMotorista();

        List<Aluguel> alugueis = new ArrayList<>();

        carrinho.getItemCarrinhos().forEach(c -> {
            carroService.checkCarroDisponivel(c.getCarro().getId());
            Apolice apolice = checkAvailabilityPolicy(c.getApolice().getId());

            Aluguel aluguel = new Aluguel();
            aluguel.setMotorista(motorista);
            aluguel.setDataPedido(LocalDate.now());
            aluguel.setDataEntrega(c.getDataInicio());
            aluguel.setDataDevolucao(c.getDataTermino());
            aluguel.setCarro(c.getCarro());
            aluguel.setApolice(apolice);
            aluguel.setValorTotal(c.getValorTotal());
            aluguel.setAceitouTermos(createAluguelDTO.aceitouTermos());

            aluguel.getApolice().setAluguel(aluguel);

            alugueis.add(aluguel);
        });

        carrinhoService.deleteCarrinho(carrinho.getId());
        return aluguelRepository.saveAll(alugueis);
    }

    private Aluguel findById(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
    }

    private Apolice checkAvailabilityPolicy(Long apoliceId) {
        Apolice apolice = apoliceSeguroService.show(apoliceId);

        if(apolice.getAluguel() != null) {
            throw new PolicyAlreadyInUseException(apoliceId);
        }

        return apolice;
    }
}
