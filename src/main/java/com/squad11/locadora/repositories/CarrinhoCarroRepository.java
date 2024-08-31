package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Acessorio;
import com.squad11.locadora.entities.Apolice;
import com.squad11.locadora.entities.CarrinhoCarro;
import com.squad11.locadora.entities.PK.CarrinhoCarroPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CarrinhoCarroRepository extends JpaRepository<CarrinhoCarro, Long> {

    @Modifying
    @Query(value = "DELETE FROM carrinho_carro cc " +
            "WHERE cc.carrinho_id = :carrinhoId " +
            "AND cc.carro_id = :carroId ", nativeQuery = true)
    void deleteByCarroId(Long carrinhoId, Long carroId);

    Optional<CarrinhoCarro> findByApolice(Apolice apolice);

}
