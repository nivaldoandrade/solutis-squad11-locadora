package com.squad11.locadora.services.impl;

import com.squad11.locadora.dtos.CreateMotoristaDTO;
import com.squad11.locadora.entities.Motorista;
import com.squad11.locadora.exceptions.EmailAlreadyInUseException;
import com.squad11.locadora.repositories.MotoristaRepository;
import com.squad11.locadora.services.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MotoristaServiceImpl implements MotoristaService {

    @Autowired
    private MotoristaRepository motoristaRepository;

    @Override
    public Motorista create(CreateMotoristaDTO createMotoristaDTO) {

        Optional<Motorista> contactByEmailExists = motoristaRepository.findByEmail(
                createMotoristaDTO.email()
        );

        if(contactByEmailExists.isPresent()) {
            throw new EmailAlreadyInUseException();
        }

        Motorista newMotorista = CreateMotoristaDTO.to(createMotoristaDTO);

        return motoristaRepository.save(newMotorista);


//        Contact newContact = CreateContactRequest.to(contact, photoName, categoryExists);
//
//        return contactRepository.save(newContact);


    }
}
