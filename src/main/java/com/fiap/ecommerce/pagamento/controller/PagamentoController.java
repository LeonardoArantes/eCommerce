package com.fiap.ecommerce.pagamento.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ecommerce.pagamento.service.PagamentoService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fiap.ecommerce.pagamento.DTO.PagamentoDTO;
import com.fiap.ecommerce.pagamento.entity.Pagamento;
import com.fiap.ecommerce.pagamento.entity.StatusPagamento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    public static Logger LOGGER = LoggerFactory.getLogger(PagamentoController.class);
    
    @Autowired
    private PagamentoService pagamentoService;

    //criarPagamento
    @PostMapping("/criarPagamento")
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody PagamentoDTO pagamentoDTO) throws Exception{
        PagamentoDTO pagamentoCriado = pagamentoService.criarPagamento(pagamentoDTO);

        return new ResponseEntity<>(pagamentoCriado, HttpStatus.CREATED);
    }
    //lerPagamentoPorId
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> lerPagamentoPorId(@PathVariable Long id){
        LOGGER.info("Getting payment by ID: " + id);
        Optional<Pagamento> pagamento = pagamentoService.lerPagamentoPorId(id);
        LOGGER.info("Payment found: " + pagamento);
        return pagamento != null ? new ResponseEntity<>(pagamento, HttpStatus.OK) : 
                                   new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    //lerTodosPagamentos
    @GetMapping
    public ResponseEntity<List<Pagamento>> lerTodosPagamentos(){
        List<Pagamento> pagamentos = pagamentoService.lerTodosPagamentos();
        return new ResponseEntity<>(pagamentos, HttpStatus.OK);
    }    

    //atualizarPagamento
    @PutMapping("/atualizarPagamento/{id}")
    public ResponseEntity<StatusPagamento> atualizarPagamento(@PathVariable Long id, @RequestBody Pagamento pagamento){
        Pagamento pagamentoAtualizado = pagamentoService.atualizarPagamento(id, pagamento); 

        return pagamento != null ? new ResponseEntity<>(pagamentoAtualizado.getStatusPagamento(), HttpStatus.OK) :
                                                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
