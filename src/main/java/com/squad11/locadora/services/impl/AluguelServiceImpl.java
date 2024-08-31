package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.*;
import com.squad11.locadora.repositories.AluguelRepository;
import com.squad11.locadora.repositories.ApoliceRepository;
import com.squad11.locadora.services.AluguelService;
import com.squad11.locadora.services.ApoliceService;
import com.squad11.locadora.services.CarrinhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class AluguelServiceImpl implements AluguelService {

    @Autowired
    CarrinhoService carrinhoService;

    @Autowired
    ApoliceService apoliceService;

    @Autowired
    AluguelRepository aluguelRepository;

    @Transactional
    @Override
    public List<Aluguel> create(Long carrinhoId) {
        Carrinho carrinho = carrinhoService.show(carrinhoId);

        Motorista motorista = carrinho.getMotorista();

        List<Aluguel> alugueis = new ArrayList<>();

        carrinho.getCarrinhoCarros().forEach(c -> {
            Apolice apolice = apoliceService.findById(c.getApolice().getId());

            Aluguel aluguel = new Aluguel();
            aluguel.setMotorista(motorista);
            aluguel.setDataPedido(LocalDate.now());
            aluguel.setDataEntrega(c.getDataInicio());
            aluguel.setDataDevolucao(c.getDataTermino());
            aluguel.setCarro(c.getCarro());
            aluguel.setApolice(apolice);

            alugueis.add(aluguel);
        });

        return aluguelRepository.saveAll(alugueis);

    }
}
