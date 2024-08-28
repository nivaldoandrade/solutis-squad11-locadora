package com.squad11.locadora.utils;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.repositories.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private CarroRepository carroRepository;

    @Override
    public void run(String... args) throws Exception {
        // Popula o banco de dados com carros, se estiver vazio
        if (carroRepository.count() == 0) {
            carroRepository.save(new Carro("1HGCM82633A004352", "ABC-1234", BigDecimal.valueOf(100.0)));
            carroRepository.save(new Carro("1HGCM82633A004353", "DEF-5678", BigDecimal.valueOf(150.0)));
            carroRepository.save(new Carro("1HGCM82633A004354", "GHI-9101", BigDecimal.valueOf(200.0)));
            carroRepository.save(new Carro("1HGCM82633A004355", "JKL-1121", BigDecimal.valueOf(130.0)));
            carroRepository.save(new Carro("1HGCM82633A004356", "MNO-3141", BigDecimal.valueOf(180.0)));
            carroRepository.save(new Carro("1HGCM82633A004357", "PQR-5161", BigDecimal.valueOf(220.0)));
            carroRepository.save(new Carro("1HGCM82633A004358", "STU-7181", BigDecimal.valueOf(250.0)));
            carroRepository.save(new Carro("1HGCM82633A004359", "VWX-9202", BigDecimal.valueOf(90.0)));
            carroRepository.save(new Carro("1HGCM82633A004360", "YZA-1233", BigDecimal.valueOf(170.0)));
            carroRepository.save(new Carro("1HGCM82633A004361", "BCD-4566", BigDecimal.valueOf(210.0)));
            carroRepository.save(new Carro("1HGCM82633A004362", "EFG-7899", BigDecimal.valueOf(140.0)));
            carroRepository.save(new Carro("1HGCM82633A004363", "HIJ-1012", BigDecimal.valueOf(160.0)));
            carroRepository.save(new Carro("1HGCM82633A004364", "KLM-1314", BigDecimal.valueOf(190.0)));
            carroRepository.save(new Carro("1HGCM82633A004365", "NOP-1516", BigDecimal.valueOf(240.0)));
            carroRepository.save(new Carro("1HGCM82633A004366", "QRS-1718", BigDecimal.valueOf(260.0)));
        }
    }
}