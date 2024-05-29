package com.example.transacoes.mappers;

import com.example.transacoes.dtos.TransacaoDto;
import com.example.transacoes.models.TransacaoModel;
import org.springframework.stereotype.Component;

@Component
public class TransacaoMapper {

    public TransacaoDto toDto(TransacaoModel transacaoModel) {
        return new TransacaoDto(
                transacaoModel.getRecebedor(),
                transacaoModel.getPagador(),
                transacaoModel.getValor()
        );
    }

    public TransacaoModel toEntity(TransacaoDto transacaoDto) {
        return new TransacaoModel(
                null,
                transacaoDto.recebedor(),
                transacaoDto.pagador(),
                transacaoDto.valor()
        );
    }
}
