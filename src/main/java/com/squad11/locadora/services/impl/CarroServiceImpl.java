package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.CategoriaEnum;
import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.repositories.CarroRepository;
import com.squad11.locadora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.squad11.locadora.specifications.CarrosSpecifications.byCategoria;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public List<Carro> findAll(List<String> categorias) {

        List<CategoriaEnum> categoriaEnums = new ArrayList<>();

        if(categorias != null)
            categoriaEnums= categorias.stream()
                    .map(CategoriaEnum::fromString)
                    .toList();


        return carroRepository.findAll(byCategoria(categoriaEnums));
    }

    @Override
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Carro.class));
    }
}
