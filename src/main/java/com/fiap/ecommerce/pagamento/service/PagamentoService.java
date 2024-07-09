package com.fiap.ecommerce.pagamento.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.ecommerce.pagamento.DTO.PagamentoDTO;
import com.fiap.ecommerce.pagamento.entity.Pagamento;
import com.fiap.ecommerce.pagamento.repository.PagamentoRepository;

import lombok.NonNull;

@Service
public class PagamentoService {
    
    @Autowired
    private PagamentoRepository pagamentoRepository;

    //CREATE
    public PagamentoDTO criarPagamento(PagamentoDTO pagamentoDTO) throws Exception{
        Pagamento pagamento = convertToEntity(pagamentoDTO);
        if(pagamento == null)
            throw new Exception("Pagamento inválido");
        
        pagamento = pagamentoRepository.save(pagamento);
        
        return pagamentoDTO;
    }
    
    //READ
    @NonNull
    public Optional<Pagamento> lerPagamentoPorId(Long id){
        Optional<Pagamento> OptionalPagamento = pagamentoRepository.findById(id);
        if(!OptionalPagamento.isPresent())   
            return Optional.empty();    

        return pagamentoRepository.findById(id);            
    }

    //READALL
    public List<Pagamento> lerTodosPagamentos(){
        return pagamentoRepository.findAll();
    }

    //UPDATE
    // a unica parte do pagamento que sera atualizada é o status
    public Pagamento atualizarPagamento(Long id, Pagamento pagamento){
        Optional<Pagamento> OptionalPagamento = pagamentoRepository.findById(id);

        if(OptionalPagamento.isPresent()){
            pagamento.setStatusPagamento(pagamento.validarPagamento());    
        }

        return pagamentoRepository.save(pagamento);
    }

    //DELETE
    //nao há necessidade de exclusão dos pagamentos

    /**
     * Converts a Pagamento object to a PagamentoDTO object.
     *
     * @param  pagamento  the Pagamento object to convert
     * @return            the converted PagamentoDTO object
     */
    public PagamentoDTO convertToDTO(Pagamento pagamento){
        return new PagamentoDTO(pagamento.getIdPagamento(),
        pagamento.getStatusPagamento(),
        pagamento.getDataPagamento(),
        pagamento.getTipoPagamento(),
        pagamento.getDadosPagamento());
    }

    /**
     * Converts a PagamentoDTO object to a Pagamento object.
     *
     * @param  pagamentoDTO  the PagamentoDTO object to convert
     * @return                the converted Pagamento object
     */
    public Pagamento convertToEntity(PagamentoDTO pagamentoDTO){
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(pagamentoDTO.idPagamento());
        pagamento.setStatusPagamento(pagamentoDTO.statusPagamento());
        pagamento.setDataPagamento(pagamentoDTO.dataPagamento());
        pagamento.setTipoPagamento(pagamentoDTO.tipoPagamento());
        pagamento.setDadosPagamento(pagamentoDTO.dadosPagamento());
               
        return pagamento;
    }

}
