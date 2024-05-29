package com.example.transacoes.services;

import com.example.transacoes.dtos.clienteDtos.ClienteRequestDto;
import com.example.transacoes.dtos.clienteDtos.ClienteResponseDto;
import com.example.transacoes.mappers.ClienteMapper;
import com.example.transacoes.models.ClienteModel;
import com.example.transacoes.repositories.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClienteService {

    @PostConstruct
    public void init() {
        salvarClientes();
    }

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponseDto save(ClienteRequestDto clienteRequestDto) {
        ClienteModel clienteModel = clienteMapper.toEntity(clienteRequestDto);
        return clienteMapper.toClienteResponseDto(clienteRepository.save(clienteModel));
    }

    @Transactional
    public void delete(ClienteRequestDto clienteRequestDto) {
        ClienteModel clienteModel = clienteMapper.toEntity(clienteRequestDto);
        clienteRepository.delete(clienteModel);
    }

    public List<ClienteResponseDto> findAllPageable(Integer pagina,
                                                    Integer resultados,
                                                    List<String> ordenacao
    ) {
        List<Sort.Order> orderList = ordenacao.stream().map((ordem) -> {
            return new Sort.Order(Sort.Direction.DESC, ordem);
        }).toList();

        Pageable pageable = PageRequest.of(pagina,resultados, Sort.by(orderList));
        Page<ClienteModel> clienteModelPage = clienteRepository.findAll(pageable);
        return clienteModelPage.map(clienteMapper::toClienteResponseDto).toList();
    }

    public Optional<ClienteResponseDto> findClienteResponseById(UUID id) {

        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(id);
        if (clienteModelOptional.isEmpty()){
            return Optional.empty();
        }
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteModelOptional.get(), clienteModel);
        return Optional.ofNullable(clienteMapper.toClienteResponseDto(clienteModel));
    }

    public Optional<ClienteRequestDto> findClienteRequestById(UUID id) {

        Optional<ClienteModel> clienteModelOptional = clienteRepository.findById(id);
        if (clienteModelOptional.isEmpty()){
            return Optional.empty();
        }
        ClienteModel clienteModel = new ClienteModel();
        BeanUtils.copyProperties(clienteModelOptional.get(), clienteModel);
        return Optional.ofNullable(clienteMapper.toClienteRequestDto(clienteModel));
    }


    public Optional<List<ClienteResponseDto>> findByNome(String nome) {

        Optional<List<ClienteModel>> optionalClienteModelList = Optional.ofNullable(clienteRepository.findByNome(nome));
        if (optionalClienteModelList.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(optionalClienteModelList.stream().flatMap(Collection::stream).map(clienteMapper::toClienteResponseDto).toList());
    }

    public void salvarClientes() {

        List<ClienteModel> clientes = new ArrayList<>();

        clientes.add(new ClienteModel(null, "John", 1000.00, "password1"));
        clientes.add(new ClienteModel(null,"Alice", 1500.0, "password2"));
        clientes.add(new ClienteModel(null, "Bob", 2000.0, "password3"));
        clientes.add(new ClienteModel(null, "Emma", 1200.0, "password4"));
        clientes.add(new ClienteModel(null, "Michael", 1800.0, "password5"));
        clientes.add(new ClienteModel(null, "Sophia", 2200.0, "password6"));
        clientes.add(new ClienteModel(null, "William", 1300.0, "password7"));
        clientes.add(new ClienteModel(null, "Olivia", 1600.0, "password8"));
        clientes.add(new ClienteModel(null, "James", 1900.0, "password9"));
        clientes.add(new ClienteModel(null, "Charlotte", 1700.0, "password10"));

        // Accessing objects from the list
        for (ClienteModel cliente : clientes) {
           this.save(clienteMapper.toClienteRequestDto(cliente));
        }
    }

}
