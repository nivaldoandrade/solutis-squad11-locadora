package com.squad11.locadora.utils;

import com.squad11.locadora.entities.*;
import com.squad11.locadora.repositories.AcessorioRepository;
import com.squad11.locadora.repositories.CarroRepository;
import com.squad11.locadora.repositories.FabricanteRepository;
import com.squad11.locadora.repositories.ModeloCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private ModeloCarroRepository modeloCarroRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;


    @Override
    public void run(String... args) throws Exception {

        //Criação dos fabricantes

        ArrayList<Fabricante> listaFabricantes = new ArrayList<>();
        ArrayList<ModeloCarro> listaModeloCarros = new ArrayList<>();

        List<String> FabricantesString = List.of(
                "Ford", "Audi", "BMW", "Honda",
                "Buggati", "Aston Martin", "McLaren",
                "Samsung Galaxy", "Tesla", "Hennessey", "Koenigsegg"
                );


        Acessorio acessorio = acessorioRepository.save(new Acessorio("Aerofólio"));
        List<Acessorio> listaAcessorios = new ArrayList<>();
        listaAcessorios.add(acessorio);
        for(String s : FabricantesString) {

            Fabricante f = fabricanteRepository.save(new Fabricante(s));
            listaFabricantes.add(f);

        }

        for(Fabricante f : listaFabricantes) {

            ModeloCarro m  = modeloCarroRepository.save(new ModeloCarro("Descricao", CategoriaEnum.ESPORTIVO, f));
            listaModeloCarros.add(m);

        }


        // Popula o banco de dados com carros, se estiver vazio

        listaModeloCarros.forEach(modelo -> carroRepository.save(new Carro("OSDFKSDF9-IKJSDF", "LV5P-UON", BigDecimal.valueOf(100.0), modelo, listaAcessorios)));
    }
}