package com.squad11.locadora.controllers.impl.carro;

import com.squad11.locadora.controllers.carro.CarroController;
import com.squad11.locadora.dtos.carro.CarroDTO;
import com.squad11.locadora.dtos.carro.request.CreateCarroDTO;
import com.squad11.locadora.dtos.carro.request.ListCarroDTO;
import com.squad11.locadora.entities.carro.Carro;
import com.squad11.locadora.services.StorageService;
import com.squad11.locadora.services.carro.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CarroControllerImpl implements CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    StorageService storageService;


    @Override
    public ResponseEntity<Resource> getImage(@PathVariable String imageNome) {

        StorageService.RecoveredFile recoveredFile = storageService.getImage(imageNome);


        MediaType contentType = imageNome.endsWith(".png") ? MediaType.IMAGE_PNG :  MediaType.IMAGE_JPEG;

        return ResponseEntity.ok()
                .contentType(contentType)
                .body(recoveredFile.getResource());
    }

    @Override
    public ResponseEntity<ListCarroDTO> getCarrosDisponiveis(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") String orderBy,
            @RequestParam(name = "categorias",required = false) List<String> categorias,
            @RequestParam(name = "acessorios", required = false) List<String> acessorios
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "modelo.descricao"));

        Page<Carro> carros = carroService.list(pageable, categorias, acessorios);

        ListCarroDTO listCarroDTO = ListCarroDTO.from(carros);

        return ResponseEntity.ok().body(listCarroDTO);
    }

    @Override
    public ResponseEntity<CarroDTO> show(@PathVariable Long id) {
        Carro carro = carroService.findByIdDisponivel(id);

        CarroDTO carroDTO = CarroDTO.from(carro);

        return ResponseEntity.ok(carroDTO);
    }

    @Override
    public ResponseEntity<CarroDTO> create(
            @ModelAttribute @Validated CreateCarroDTO createCarroDTO
    ) {

        Carro carro = carroService.create(createCarroDTO);

        CarroDTO carroDTO = CarroDTO.from(carro);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(carro.getId()).toUri();

        return ResponseEntity.created(uri).body(carroDTO);
    }

}
