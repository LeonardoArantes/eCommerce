package com.fiap.ecommerce.pagamento.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "pagamento")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pagamento {
    
    private long idPagamento;

    private StatusPagamento statusPagamento;

    private LocalDate dataPagamento;

    //private List<item> itens;

    private tipoPagamento tipoPagamento;

    private DadosPagamento dadosPagamento;    
    
}
