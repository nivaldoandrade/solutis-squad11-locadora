package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateAluguelDTO;
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
    public List<Aluguel> create(Long carrinhoId,  CreateAluguelDTO createAluguelDTO) {
        Carrinho carrinho = carrinhoService.show(carrinhoId);

        Motorista motorista = carrinho.getMotorista();

        List<Aluguel> alugueis = new ArrayList<>();

        carrinho.getCarrinhoCarros().forEach(c -> {
            Apolice apolice = apoliceService.findById(c.getApolice().getId());
            checkAvailabilityForRental(c.getCarro().getId());

            Aluguel aluguel = new Aluguel();
            aluguel.setMotorista(motorista);
            aluguel.setDataPedido(LocalDate.now());
            aluguel.setDataEntrega(c.getDataInicio());
            aluguel.setDataDevolucao(c.getDataTermino());
            aluguel.setCarro(c.getCarro());
            aluguel.setApolice(apolice);
            aluguel.setValorTotal(c.getValorTotal());
            aluguel.setAceitouTermos(createAluguelDTO.aceitouTermos());

            alugueis.add(aluguel);
        });

        carrinhoService.deleteCarrinho(carrinho.getId());

        return aluguelRepository.saveAll(alugueis);

    }

    @Override
    public void payment(Long aluguelId) {
        Aluguel aluguel = this.findById(aluguelId);

        checkAvailabilityForRental(aluguel.getCarro().getId());

        aluguel.setStatus(StatusAluguelEnum.CONCLUIDO);
        aluguel.getCarro().setStatus(StatusCarroEnum.RESERVADO);

        aluguelRepository.save(aluguel);
    }


    private Aluguel findById(Long id) {
        return aluguelRepository.findById(id)
                .orElseThrow(RentNotFoundException::new);
    }

    private void checkAvailabilityForRental(Long carroId) {
        Carro carro = carroService.findById(carroId);

        if(carro.getStatus().equals(StatusCarroEnum.RESERVADO)) {
            throw new CarNotAvailableForRentalException();
        }
    }
}
