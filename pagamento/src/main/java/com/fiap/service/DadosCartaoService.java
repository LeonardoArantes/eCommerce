package com.fiap.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiap.dto.DadosCartaoDTO;
import com.fiap.entity.DadosCartao;
import com.fiap.repository.DadosPagamentoRepository;

@Service
public class DadosCartaoService {

    @Autowired
    private DadosPagamentoRepository dadosPagamentoRepository;

    public DadosCartaoDTO criarDadosPagamento(DadosCartaoDTO dadosPagamentoDTO) throws Exception{
        DadosCartao dadosPagamento = convertToEntity(dadosPagamentoDTO);
        if(dadosPagamento == null)
            throw new Exception("Dados do Cartão inválidos");  
    
        return convertToDTO(dadosPagamentoRepository.save(dadosPagamento));
    }

    public Optional<DadosCartao> lerDadosPagamentoPorId(Long id) {
        Optional<DadosCartao> OptionalDadosPagamento = dadosPagamentoRepository
                                                                    .findById(id);
        
        if(!OptionalDadosPagamento.isPresent())
            return Optional.empty();    

        return OptionalDadosPagamento;            
    }

    //UPDATE - não é necessario pois a partir do momento que o cartão é validado, não
    //faz sentido o atualiza-lo, apenas deletar e/ou criar um novo.

    //DELETE
    public void deletarDadosPagamento(Long id) {
        dadosPagamentoRepository.deleteById(id);
    }

    /**
     * Converts a DadosPagamento object to a DadosPagamentoDTO object.
     *
     * @param  dadosPagamento  the DadosPagamento object to convert
     * @return                the converted DadosPagamentoDTO object
     */
    private DadosCartaoDTO convertToDTO(DadosCartao dadosCartao) {
        return new DadosCartaoDTO(dadosCartao.getCartaoId(),
                                    dadosCartao.getNome(),
                                    dadosCartao.getSobrenome(),
                                    dadosCartao.getNumeroCartao(),
                                    dadosCartao.getCodSeguranca(),
                                    dadosCartao.getBandeira(),
                                    dadosCartao.getValidade());
    }

    /**
     * Converts a DadosPagamentoDTO object to a DadosPagamento object.
     *
     * @param  dadosPagamentoDTO  the DadosPagamentoDTO object to convert
     * @return                   the converted DadosPagamento object
     */
    private DadosCartao convertToEntity(DadosCartaoDTO dadosPagamentoDTO) {
        return new DadosCartao(dadosPagamentoDTO.cartaoId(),
                                  dadosPagamentoDTO.nome(),
                                  dadosPagamentoDTO.sobrenome(),
                                  dadosPagamentoDTO.numeroCartao(),
                                  dadosPagamentoDTO.codSeguranca(),
                                  dadosPagamentoDTO.bandeira(),
                                  dadosPagamentoDTO.validade());
    }

}