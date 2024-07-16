package com.fiap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fiap.entity.DadosCartao;

@Repository
public interface DadosPagamentoRepository extends JpaRepository<DadosCartao, Long> {
    
}