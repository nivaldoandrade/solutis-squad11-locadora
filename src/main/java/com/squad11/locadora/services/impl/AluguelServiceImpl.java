package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.*;
import com.squad11.locadora.exceptions.CarNotAvailableForRentalException;
import com.squad11.locadora.exceptions.RentNotFoundException;
import com.squad11.locadora.repositories.AluguelRepository;
import com.squad11.locadora.repositories.ApoliceRepository;
import com.squad11.locadora.services.AluguelService;
import com.squad11.locadora.services.ApoliceService;
import com.squad11.locadora.services.CarrinhoService;
import com.squad11.locadora.services.CarroService;
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

    @Autowired
    CarroService carroService;

    @Transactional
    @Override
    public List<Aluguel> create(Long carrinhoId) {
        Carrinho carrinho = carrinhoService.show(carrinhoId);

        Motorista motorista = carrinho.getMotorista();

        List<Aluguel> alugueis = new ArrayList<>();

        carrinho.getCarrinhoCarros().forEach(c -> {
            Apolice apolice = apoliceService.findById(c.getApolice().getId());
            Carro carro = carroService.findById(c.getCarro().getId());

            if(carro.getStatus().equals(StatusCarroEnum.RESERVADO)) {
                throw new CarNotAvailableForRentalException();
            }

            Aluguel aluguel = new Aluguel();
            aluguel.setMotorista(motorista);
            aluguel.setDataPedido(LocalDate.now());
            aluguel.setDataEntrega(c.getDataInicio());
            aluguel.setDataDevolucao(c.getDataTermino());
            aluguel.setCarro(c.getCarro());
            aluguel.setApolice(apolice);
            aluguel.setValorTotal(c.getValorTotal());

//            c.getCarro().setStatus(StatusCarroEnum.RESERVADO);

            alugueis.add(aluguel);
        });

        carrinhoService.deleteCarrinho(carrinho.getId());

        return aluguelRepository.saveAll(alugueis);

    }

    @Override
    public void payment(Long aluguelId) {
        Aluguel aluguel = this.findById(aluguelId);

        aluguel.setStatus(StatusAluguelEnum.CONCLUIDO);
        aluguel.getCarro().setStatus(StatusCarroEnum.RESERVADO);

        aluguelRepository.save(aluguel);
    }


    private Aluguel findById(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
    }
}
