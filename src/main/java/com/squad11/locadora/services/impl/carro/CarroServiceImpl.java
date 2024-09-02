package com.squad11.locadora.services.impl.carro;

import com.squad11.locadora.dtos.carro.request.CreateCarroDTO;
import com.squad11.locadora.entities.carro.Acessorio;
import com.squad11.locadora.entities.carro.Carro;
import com.squad11.locadora.entities.carro.ModeloCarro;
import com.squad11.locadora.entities.enums.CategoriaEnum;
import com.squad11.locadora.entities.enums.StatusCarroEnum;
import com.squad11.locadora.exceptions.CarNotAvailableException;
import com.squad11.locadora.exceptions.CarNotAvailableForRentalException;
import com.squad11.locadora.exceptions.CartNotFoundException;
import com.squad11.locadora.repositories.carro.CarroRepository;
import com.squad11.locadora.services.carro.AcessorioService;
import com.squad11.locadora.services.carro.CarroService;
import com.squad11.locadora.services.carro.ModeloCarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.squad11.locadora.specifications.CarrosSpecifications.*;

@Service
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private ModeloCarroService modeloCarroService;

    @Autowired
    private AcessorioService acessorioService;

    @Override
    public Page<Carro> list(Pageable pageable, List<String> categorias, List<String> acessorios) {

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


        return carroRepository.findAll(specification.and(byDisponiveis()), pageable);
    }

    @Override
    public Carro findById(Long id) {
        return carroRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }

    @Override
    public Carro findByIdDisponivel(Long id) {
        Carro carro = this.findById(id);

        if(!(carro.getStatus() == StatusCarroEnum.DISPONIVEL)) {
            throw new CarNotAvailableException();
        }

        return carro;
    }

    @Override
    public void checkCarroDisponivel(Long carroId) {
        Carro carro = this.findById(carroId);


        if(carro.getStatus().equals(StatusCarroEnum.RESERVADO)) {
            throw new CarNotAvailableForRentalException(carroId);
        }
    }

    @Override
    public Carro create(CreateCarroDTO createCarroDTO) {
        ModeloCarro modeloCarro = modeloCarroService.show(createCarroDTO.modeloId());

        List<Acessorio> acessorios = new ArrayList<>();

        if(createCarroDTO.acessoriosId() != null) {
            acessorios = getAcessorios(createCarroDTO.acessoriosId());
        }

        Carro carro = CreateCarroDTO.to(createCarroDTO, acessorios, modeloCarro);

        return carroRepository.save(carro);
    }

    private List<Acessorio> getAcessorios(List<Long> acessoriosId) {
        List<Acessorio> acessorios = new ArrayList<>();

        acessoriosId.forEach(a -> {
            Acessorio acessorio = acessorioService.show(a);
            acessorios.add(acessorio);
        });

        return acessorios;
    }


}
