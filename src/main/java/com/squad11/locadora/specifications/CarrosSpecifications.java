package com.squad11.locadora.specifications;

import com.squad11.locadora.entities.*;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.stream.Collectors;

public class CarrosSpecifications {
    public static Specification<Carro> byCategorias(List<CategoriaEnum> categorias) {
        return (root, query, criteriaBuilder) -> {
            Join<Carro, ModeloCarro> join = root.join("modelo");

            return join.get("categoria").in(categorias);
        };
    }

    public static Specification<Carro> byAcessorios(List<String> acessorios) {
        return (root, query, criteriaBuilder) -> {
            Join<Carro, Acessorio> join = root.join("acessorios", JoinType.INNER);

            return criteriaBuilder.lower(join.get("descricao")).in(
                    acessorios.stream()
                            .map(String::toLowerCase)
                            .collect(Collectors.toList())
            );
        };
    }

    public static Specification<Carro> byDisponiveis() {
        return (root, query, criteriaBuilder) ->
                root.get("status").in(StatusCarroEnum.DISPONIVEL);
    }
}
