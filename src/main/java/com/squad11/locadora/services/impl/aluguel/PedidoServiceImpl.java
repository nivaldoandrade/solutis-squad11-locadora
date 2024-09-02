package com.squad11.locadora.services.impl.aluguel;

import com.squad11.locadora.dtos.aluguel.request.CreateAluguelDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.enums.StatusAluguelEnum;
import com.squad11.locadora.entities.enums.StatusCarroEnum;
import com.squad11.locadora.entities.enums.StatusPedidoEnum;
import com.squad11.locadora.entities.pessoa.Motorista;
import com.squad11.locadora.exceptions.OrderNotFoundException;
import com.squad11.locadora.exceptions.RentalAlreadyPaidException;
import com.squad11.locadora.repositories.aluguel.PedidoRepository;
import com.squad11.locadora.services.aluguel.AluguelService;
import com.squad11.locadora.services.aluguel.PedidoService;
import com.squad11.locadora.services.carro.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    AluguelService aluguelService;

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CarroService carroService;


    @Override
    public Pedido show(Long pedidoId) {
        return this.findById(pedidoId);
    }

    @Override
    public Pedido findById(Long pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(OrderNotFoundException::new);
    }

    @Transactional
    @Override
    public Pedido create(Long carrinhoId, CreateAluguelDTO createAluguelDTO) {
        List<Aluguel> alugueis = aluguelService.create(carrinhoId, createAluguelDTO);

        Motorista motorista = alugueis.getFirst().getMotorista();

        Pedido pedido = new Pedido(alugueis, motorista);

        return pedidoRepository.save(pedido);
    }

    @Transactional
    @Override
    public Pedido payment(Long pedidoId) {
        Pedido pedido = this.findById(pedidoId);

        pedido.getAlugueis().forEach(p -> {
            Aluguel aluguel = aluguelService.show(p.getId());

            if(aluguel.getStatus().equals(StatusAluguelEnum.CONCLUIDO)) {
                throw new RentalAlreadyPaidException();
            }

            carroService.checkCarroDisponivel(aluguel.getCarro().getId());

            aluguel.setStatus(StatusAluguelEnum.CONCLUIDO);
            aluguel.getCarro().setStatus(StatusCarroEnum.RESERVADO);
        });

        pedido.setStatus(StatusPedidoEnum.CONCLUIDO);
        pedidoRepository.save(pedido);

        return pedido;
    }
}
