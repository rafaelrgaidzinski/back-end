package com.example.transacoes.mappers;

import com.example.transacoes.dtos.clienteDtos.ClienteRequestDto;
import com.example.transacoes.dtos.clienteDtos.ClienteResponseDto;
import com.example.transacoes.models.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteRequestDto toClienteRequestDto(ClienteModel clienteModel) {
        return new ClienteRequestDto(
                clienteModel.getNome(),
                clienteModel.getSaldo(),
                clienteModel.getSenha()
        );
    }

    public ClienteResponseDto toClienteResponseDto (ClienteModel clienteModel) {
        return new ClienteResponseDto(
                clienteModel.getNome(),
                clienteModel.getSaldo()
        );
    }

    public ClienteModel toEntity(ClienteRequestDto clienteRequestDto) {
        return new ClienteModel(
                null,
                clienteRequestDto.nome(),
                clienteRequestDto.saldo(),
                clienteRequestDto.senha()
        );
    }
}
