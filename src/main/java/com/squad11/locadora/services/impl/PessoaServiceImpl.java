package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.entities.Pessoa;
import com.squad11.locadora.exceptions.CPFAlreadyInUseException;
import com.squad11.locadora.exceptions.EmailAlreadyInUseException;
import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.exceptions.PersonNotFoundException;
import com.squad11.locadora.repositories.PessoaRepository;
import com.squad11.locadora.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public void updateAtivo(Long id) {
        Pessoa pessoa = this.findById(id);

        pessoa.ativar();

        pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() ->  new PersonNotFoundException());

        return pessoa;
    }

    @Override
    public void validateEmailUniqueness(String email, Long existingPessoaId) {
        Optional<Pessoa> pessoaByEmailExists = pessoaRepository.findByEmail(email);

        pessoaByEmailExists.ifPresent((c) -> {
            if(!c.getId().equals(existingPessoaId)) {
                throw new EmailAlreadyInUseException();
            }
        });
    }

    @Override
    public void validateCPFUniqueness(String cpf, Long existingPessoaId) {
        Optional<Pessoa> pessoaByCPFExists = pessoaRepository.findByCpf(cpf);

        pessoaByCPFExists.ifPresent((c) -> {
            if(!c.getId().equals(existingPessoaId)) {
                throw new CPFAlreadyInUseException();
            }
        });
    }
}
