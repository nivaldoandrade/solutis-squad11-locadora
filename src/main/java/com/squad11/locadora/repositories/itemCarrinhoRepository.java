package com.squad11.locadora.repositories;

import com.squad11.locadora.entities.Apolice;
import com.squad11.locadora.entities.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface itemCarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    @Modifying
    @Query(value = "DELETE FROM carrinho_carro cc " +
            "WHERE cc.carrinho_id = :carrinhoId " +
            "AND cc.carro_id = :carroId ", nativeQuery = true)
    void deleteByCarroId(Long carrinhoId, Long carroId);

    Optional<ItemCarrinho> findByApolice(Apolice apolice);

}
