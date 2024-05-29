package com.example.oscar.services;

import com.example.oscar.models.AtorModel;
import com.example.oscar.models.FilmeModel;
import com.example.oscar.models.IndicacaoModel;
import com.example.oscar.models.Indicavel;
import com.example.oscar.repositories.AtorRepository;
import com.example.oscar.repositories.FilmeRepository;
import com.example.oscar.repositories.IndicacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class IndicacaoService {

    @Autowired
    IndicacaoRepository indicacaoRepository;

    @Autowired
    AtorRepository atorRepository;

    @Autowired
    FilmeRepository filmeRepository;

    @Transactional
    public IndicacaoModel save(IndicacaoModel indicacaoModel) { return indicacaoRepository.save(indicacaoModel); }

    @Transactional
    public void delete(IndicacaoModel indicacaoModel) { indicacaoRepository.delete(indicacaoModel); }

    public Optional<IndicacaoModel> findById(UUID id) {
        return indicacaoRepository.findById(id);
    }

    public List<IndicacaoModel> findAll() {
        return indicacaoRepository.findAll();
    }

    public boolean adicionarIndicacao(IndicacaoModel indicacaoModel) {
        Indicavel indicavel = indicacaoModel.getIndicavel();

        if (indicavel.isElegivel()) {
            indicavel.setNumeroDeIndicacoes((short) (indicavel.getNumeroDeIndicacoes() + 1));
            atualizarIndicavel(indicavel);
            return true;
        }
        return false;
    }

    public void cancelarIndicacao(IndicacaoModel indicacaoModel) {
        Indicavel indicavel = indicacaoModel.getIndicavel();
        indicavel.setNumeroDeIndicacoes((short) (indicavel.getNumeroDeIndicacoes() - 1));
        atualizarIndicavel(indicavel);
    }

    public void atualizarIndicavel(Indicavel indicavel) {

        if (indicavel instanceof AtorModel) {
            AtorModel atorModel = new AtorModel();
            BeanUtils.copyProperties(indicavel, atorModel);
            atorRepository.save(atorModel);
        } else if (indicavel instanceof FilmeModel) {
            FilmeModel filmeModel = new FilmeModel();
            BeanUtils.copyProperties(indicavel, filmeModel);
            filmeRepository.save(filmeModel);
        }
    }

}
