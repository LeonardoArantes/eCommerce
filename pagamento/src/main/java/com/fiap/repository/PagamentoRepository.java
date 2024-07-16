package com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.entity.Pagamento;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    
}