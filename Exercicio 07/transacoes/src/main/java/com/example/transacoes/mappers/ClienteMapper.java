package com.example.transacoes.mappers;

import com.example.transacoes.dtos.ClienteDto;
import com.example.transacoes.models.ClienteModel;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public  ClienteDto toDto(ClienteModel clienteModel) {
        return new ClienteDto(
          clienteModel.getNome(),
          clienteModel.getSaldo(),
          clienteModel.getSenha()
        );
    }

    public ClienteModel toEntity(ClienteDto clienteDto) {
        return new ClienteModel(
                null,
                clienteDto.nome(),
                clienteDto.saldo(),
                clienteDto.senha()
        );
    }
}
