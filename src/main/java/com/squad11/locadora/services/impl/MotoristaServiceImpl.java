package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateMotoristaDTO;
import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.entities.SexoEnum;
import com.squad11.locadora.exceptions.DriverNotFoundException;
import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.exceptions.NumeroCNHAlreadyInUseException;
import com.squad11.locadora.repositories.MotoristaRepository;
import com.squad11.locadora.services.CadastroPendenteService;
import com.squad11.locadora.services.MotoristaService;
import com.squad11.locadora.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Motorista findById(Long id) {
        return motoristaRepository.findById(id)
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
