package com.example.transacoes.services;

import com.example.transacoes.dtos.ClienteDto;
import com.example.transacoes.mappers.ClienteMapper;
import com.example.transacoes.models.ClienteModel;
import com.example.transacoes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    public List<ClienteDto> getClientesPaginados(Integer pagina,
                                                 Integer resultados,
                                                 List<String> ordenacao
    ) {
        List<Sort.Order> orderList = ordenacao.stream().map((ordem) -> {
            return new Sort.Order(Sort.Direction.DESC, ordem);
        }).toList();

        Pageable pageable = PageRequest.of(pagina,resultados, Sort.by(orderList));
        Page<ClienteModel> clienteModelPage = clienteRepository.findAll(pageable);
        return clienteModelPage.map(clienteMapper::toDto).toList();
    }

    public List<ClienteDto> getAllClientes(){
      List<ClienteModel> listaClientes = clienteRepository.findAll();
      return listaClientes.stream().map(clienteMapper::toDto).toList();
    }

    public ClienteDto getClienteByNome(String nome) {

        ClienteModel clienteModel = clienteRepository.findOneByNome(nome);
        return clienteMapper.toDto(clienteModel);
    }

    public ClienteDto getClienteById(UUID id) {
        ClienteModel clienteModel = clienteRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("Cliente não encontrado");
        });
        return clienteMapper.toDto(clienteModel);
    }

    public  ClienteDto saveCliente(ClienteDto clienteDto) {
        ClienteModel clienteModel = clienteMapper.toEntity(clienteDto);
        return clienteMapper.toDto(clienteRepository.save(clienteModel));
    }

}
