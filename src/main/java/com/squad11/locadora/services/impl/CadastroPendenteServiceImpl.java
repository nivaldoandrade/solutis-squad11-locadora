package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.CadastroPendente;

import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.exceptions.PendingRegistrationNotFoundException;
import com.squad11.locadora.exceptions.TokenExpiredException;
import com.squad11.locadora.repositories.CadastroPendenteRepository;
import com.squad11.locadora.services.CadastroPendenteService;
import com.squad11.locadora.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CadastroPendenteServiceImpl implements CadastroPendenteService {

    @Autowired
    private CadastroPendenteRepository cadastroPendenteRepository;

    @Autowired
    private PessoaService pessoaService;

    @Override
    public String createToken(Long id) {
        String token = generateToken(id);
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        CadastroPendente cadastroPendente = new CadastroPendente(
                id,
                token,
                expiresAt
        );

        cadastroPendenteRepository.save(cadastroPendente);

        return token;
    }

    @Transactional
    @Override
    public void confirmToken(String token) {
        Optional<CadastroPendente> cadastroPendenteOptional = cadastroPendenteRepository.findByToken(token);

        if(cadastroPendenteOptional.isEmpty()) {
            throw new PendingRegistrationNotFoundException();
        }

        CadastroPendente cadastroPendente = cadastroPendenteOptional.get();

        boolean hasExpired = LocalDateTime.now().isAfter(cadastroPendente.getExpiresAt());

        if(hasExpired) {
            throw new TokenExpiredException();
        }

        pessoaService.updateAtivo(cadastroPendente.getId());

        cadastroPendenteRepository.deleteById(cadastroPendente.getId());
    }

    private static String generateToken(Long id) {
        return System.currentTimeMillis() + "_" + id;
    }
}
