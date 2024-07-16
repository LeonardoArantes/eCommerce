package com.fiap.ecommerce.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.ecommerce.pagamento.entity.DadosCartao;

@Repository
public interface DadosPagamentoRepository extends JpaRepository<DadosCartao, Long> {
    
}
