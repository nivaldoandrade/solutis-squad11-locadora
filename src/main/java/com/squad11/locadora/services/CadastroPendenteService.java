package com.squad11.locadora.services;

public interface CadastroPendenteService {

    String createToken(Long id);

    void confirmToken(String token);
}
