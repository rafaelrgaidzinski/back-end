package com.example.transacoes.services;

import com.example.transacoes.dtos.TransacaoDto;
import com.example.transacoes.mappers.TransacaoMapper;
import com.example.transacoes.models.TransacaoModel;
import com.example.transacoes.repositories.TransacaoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    TransacaoMapper transacaoMapper;

    @Transactional
    public TransacaoDto save(TransacaoDto transacaoDto) {
        TransacaoModel transacaoModel = transacaoMapper.toEntity(transacaoDto);
        return transacaoMapper.toDto(transacaoRepository.save(transacaoModel));
    }

    @Transactional
    public void delete(TransacaoDto transacaoDto) {
        TransacaoModel transacaoModel = transacaoMapper.toEntity(transacaoDto);
        transacaoRepository.delete(transacaoModel);
    }

    public List<TransacaoDto> findAllPageable(Integer pagina,
                                                Integer resultados,
                                                List<String> ordenacao
    ) {
        List<Sort.Order> orderList = ordenacao.stream().map((ordem) -> {
            return new Sort.Order(Sort.Direction.DESC, ordem);
        }).toList();

        Pageable pageable = PageRequest.of(pagina,resultados, Sort.by(orderList));
        Page<TransacaoModel> transacaoModelPage = transacaoRepository.findAll(pageable);
        return transacaoModelPage.map(transacaoMapper::toDto).toList();
    }

    public Optional<TransacaoDto> findById(UUID id) {

        Optional<TransacaoModel> transacaoModelOptional = transacaoRepository.findById(id);
        if (transacaoModelOptional.isEmpty()){
            return Optional.empty();
        }
        TransacaoModel transacaoModel = new TransacaoModel();
        BeanUtils.copyProperties(transacaoModelOptional.get(), transacaoModel);
        return Optional.ofNullable(transacaoMapper.toDto(transacaoModel));
    }


}

