package com.squad11.locadora.services.impl;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.CategoriaEnum;
import com.squad11.locadora.exceptions.CartNotFoundException;
import com.squad11.locadora.exceptions.EntityNotFoundException;
import com.squad11.locadora.repositories.CarroRepository;
import com.squad11.locadora.services.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.squad11.locadora.specifications.CarrosSpecifications.*;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public List<Carro> findAll(List<String> categorias, List<String> acessorios) {

        Specification<Carro> specification = Specification.where(null);

        if(categorias != null && !categorias.isEmpty()) {
            List<CategoriaEnum> categoriaEnums= categorias.stream()
                    .map(CategoriaEnum::fromString)
                    .toList();

            specification = specification.and(byCategorias(categoriaEnums));
        }

        if(acessorios != null && !acessorios.isEmpty()) {
            specification = specification.and(byAcessorios(acessorios));
        }


        return carroRepository.findAll(specification);
    }

    @Override
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }
}
