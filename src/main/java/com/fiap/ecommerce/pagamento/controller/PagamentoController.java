package com.fiap.ecommerce.pagamento.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.fiap.ecommerce.pagamento.service.PagamentoService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fiap.ecommerce.pagamento.DTO.PagamentoDTO;
import com.fiap.ecommerce.pagamento.entity.Pagamento;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {
    
    @Autowired
    private PagamentoService pagamentoService;

    //criarPagamento
    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody PagamentoDTO pagamentoDTO) throws Exception{
        PagamentoDTO pagamentoCriado = pagamentoService.criarPagamento(pagamentoDTO);

        return new ResponseEntity<>(pagamentoCriado, HttpStatus.CREATED);
    }
    //lerPagamentoPorId
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Pagamento>> lerPagamentoPorId(@RequestParam Long id){
        Optional<Pagamento> pagamento = pagamentoService.lerPagamentoPorId(id);
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
    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@RequestBody Pagamento pagamento){
        Pagamento pagamentoAtualizado = pagamentoService.atualizarPagamento(
                                                pagamento.getIdPagamento(), pagamento);
        return pagamentoAtualizado != null ? new ResponseEntity<>(pagamentoAtualizado, HttpStatus.OK) :
                                                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
