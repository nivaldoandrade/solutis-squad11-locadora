package com.squad11.locadora.entities.carro;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CarroEntityListener {

//    @PostLoad
    @PostPersist
    void setPhotoUrl(Carro carro) {
        if(carro.getFoto() == null) {
            return;
        }

        String photoUrl =ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/api/carros/imagem/")
                        .path(carro.getFoto())
                        .toUriString();

        carro.setFoto(photoUrl);
    }
}
