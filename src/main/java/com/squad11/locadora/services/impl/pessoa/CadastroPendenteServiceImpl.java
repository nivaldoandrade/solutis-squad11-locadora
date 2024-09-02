package com.squad11.locadora.services.impl.pessoa;

import com.squad11.locadora.entities.pessoa.CadastroPendente;

import com.squad11.locadora.entities.pessoa.Pessoa;
import com.squad11.locadora.exceptions.PendingRegistrationNotFoundException;
import com.squad11.locadora.exceptions.PersonAlreadyActiveException;
import com.squad11.locadora.exceptions.TokenExpiredException;
import com.squad11.locadora.repositories.pessoa.CadastroPendenteRepository;
import com.squad11.locadora.services.pessoa.CadastroPendenteService;
import com.squad11.locadora.services.pessoa.PessoaService;
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

    @Transactional(noRollbackFor = PersonAlreadyActiveException.class)
    @Override
    public String createToken(Long id) {
        Pessoa pessoa = pessoaService.findById(id);

        if(pessoa.isAtivo()) {
            cadastroPendenteRepository.deleteById(id);
            throw new PersonAlreadyActiveException();
        }

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
