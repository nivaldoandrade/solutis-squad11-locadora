package com.squad11.locadora.specifications;

import com.squad11.locadora.entities.Carro;
import com.squad11.locadora.entities.CategoriaEnum;
import com.squad11.locadora.entities.ModeloCarro;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class CarrosSpecifications {
    public static Specification<Carro> byCategoria(List<CategoriaEnum> categorias) {
        return (root, query, criteriaBuilder) -> {
            Join<Carro, ModeloCarro> join = root.join("modelo");

            if(categorias == null || categorias.isEmpty()) {
                return  criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }

            return join.get("categoria").in(categorias);
        };
    }
}
