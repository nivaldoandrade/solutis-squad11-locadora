package com.squad11.locadora.services.impl.pessoa;

import com.squad11.locadora.dtos.pessoa.request.CreateMotoristaDTO;
import com.squad11.locadora.entities.aluguel.Aluguel;
import com.squad11.locadora.entities.aluguel.Pedido;
import com.squad11.locadora.entities.pessoa.Motorista;
import com.squad11.locadora.entities.enums.SexoEnum;
import com.squad11.locadora.exceptions.*;
import com.squad11.locadora.repositories.aluguel.AluguelRepository;
import com.squad11.locadora.repositories.aluguel.PedidoRepository;
import com.squad11.locadora.repositories.pessoa.MotoristaRepository;
import com.squad11.locadora.services.pessoa.CadastroPendenteService;
import com.squad11.locadora.services.pessoa.MotoristaService;
import com.squad11.locadora.services.pessoa.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoristaServiceImpl implements MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Autowired
    private CadastroPendenteService cadastroPendenteService;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    AluguelRepository aluguelRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Motorista show(Long motoristaId) {
        return this.findById(motoristaId);
    }

    @Override
    public Aluguel showAluguel(Long motoristaId, Long aluguelId) {
        Motorista motorista = this.findById(motoristaId);

        Optional<Aluguel> aluguel = aluguelRepository.findById(aluguelId);

        if(aluguel.isEmpty()) {
            throw new RentNotFoundException();
        }

        if(!aluguel.get().getMotorista().equals(motorista)) {
            throw new RentNotFoundException();
        }

        return aluguel.get();
    }

    @Override
    public Pedido showPedido(Long motoristaId, Long pedidoId) {
        Motorista motorista = this.findById(motoristaId);

        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);

        if(pedido.isEmpty()) {
            throw new OrderNotFoundException();
        }

        if(!pedido.get().getMotorista().equals(motorista)) {
            throw new OrderNotFoundException();
        }

        return pedido.get();
    }

    @Override
    public Page<Aluguel> listAlugueis(Pageable pageable, Long motoristaId) {
        Motorista motorista = this.findById(motoristaId);

        return aluguelRepository.findAllByMotorista(pageable, motorista);
    }

    @Override
    public Motorista findByIdAtivo(Long motoristaId) {
        Motorista motorista = this.findById(motoristaId);

        if(!motorista.isAtivo()) {
            throw new UnconfirmedRegistrationException();
        }

        return motorista;
    }

    @Override
    public String create(CreateMotoristaDTO createMotoristaDTO) {

        pessoaService.validateEmailUniqueness(createMotoristaDTO.email(), null);

        pessoaService.validateCPFUniqueness(createMotoristaDTO.cpf(), null);

        validateNumeroCNHUniqueness(createMotoristaDTO.numeroCNH());

        SexoEnum sexo = SexoEnum.fromString(createMotoristaDTO.sexo());

        Motorista newMotorista = CreateMotoristaDTO.to(createMotoristaDTO, sexo);

        newMotorista = motoristaRepository.save(newMotorista);

        String token = cadastroPendenteService.createToken(newMotorista.getId());

        return token;
    }

    @Override
    public Motorista findById(Long motoristaId) {
        return motoristaRepository.findById(motoristaId)
                .orElseThrow(DriverNotFoundException::new);
    }

    private void validateNumeroCNHUniqueness(String numeroCNH) {
        validateNumeroCNHUniqueness(numeroCNH, null);
    }

    private void validateNumeroCNHUniqueness(String numeroCNH, Long existingMotoristaId) {
        Optional<Motorista> motoristaByNumeroCNHExists = motoristaRepository.findByNumeroCNH(numeroCNH);

        motoristaByNumeroCNHExists.ifPresent((c) -> {
            if(!c.getId().equals(existingMotoristaId)) {
                throw new NumeroCNHAlreadyInUseException();
            }
        });
    }

}
