package com.example.oscar.services;

import com.example.oscar.models.AtorModel;
import com.example.oscar.models.FilmeModel;
import com.example.oscar.models.IndicacaoModel;
import com.example.oscar.models.Indicavel;
import com.example.oscar.repositories.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OscarService {

    @Autowired
    FileStorageService fileStorageService;

    private List<IndicacaoModel> indicacoes;
    private List<AtorModel> atores;
    private List<FilmeModel> filmes;

    public AtorModel adicionarAtor(AtorModel atorModel) {
        atores.add(atorModel);
        return atorModel;
    }

    public void removerAtor(AtorModel atorModel) {
        atores.remove(atorModel);
    }

    public FilmeModel adicionarFilme(FilmeModel filmeModel) {
        filmes.add(filmeModel);
        return filmeModel;
    }

    public void removerFilme(FilmeModel filmeModel) {
        filmes.remove(filmeModel);
    }

    public boolean adicionarIndicacao(IndicacaoModel indicacaoModel) {
        Indicavel indicavel = indicacaoModel.getIndicavel();

        if (indicavel.isElegivel()) {
            indicavel.setNumeroDeIndicacoes((short) (indicavel.getNumeroDeIndicacoes() + 1));
            indicacoes.add(indicacaoModel);
            fileStorageService.store(indicacaoModel);
            return true;
        }
        return false;
    }

    public void cancelarIndicacao(IndicacaoModel indicacaoModel) {
        Indicavel indicavel = indicacaoModel.getIndicavel();
        indicavel.setNumeroDeIndicacoes((short) (indicavel.getNumeroDeIndicacoes() - 1));
        indicacoes.remove(indicacaoModel);
    }

    public List<IndicacaoModel> mostrarListaIndicacoes() {
        return this.indicacoes;
    }

    public List<IndicacaoModel> mostrarAtoresIndicados() {
        List<IndicacaoModel> listaAtores = null;
        for (IndicacaoModel indicacao : this.indicacoes) {
            if (indicacao.getIndicavel() instanceof AtorModel) {
                listaAtores.add(indicacao);
            }
        }
        return listaAtores;
    }

    public Map<String, List<IndicacaoModel>> mostrarAtoresIndicadosPorCategoria() {
        Map<String, List<IndicacaoModel>> atoresIndicadosPorCategoria = null;
        List<IndicacaoModel> listaAtores = null;
        for (IndicacaoModel indicacao : this.indicacoes) {
            if (indicacao.getIndicavel() instanceof AtorModel) {
                listaAtores.add(indicacao);
            }
        }
        if (listaAtores != null) {
            atoresIndicadosPorCategoria = listaAtores.stream().collect(Collectors.groupingBy(IndicacaoModel::getCategoria));
        }

        return atoresIndicadosPorCategoria;
    }

    public List<IndicacaoModel> mostrarFilmesIndicados() {
        List<IndicacaoModel> listaFilmes = null;
        for (IndicacaoModel indicacao : this.indicacoes) {
            if (indicacao.getIndicavel() instanceof FilmeModel) {
                listaFilmes.add(indicacao);
            }
        }
        return listaFilmes;
    }

    public Map<String, List<IndicacaoModel>> mostrarFilmesIndicadosPorCategoria() {
        Map<String, List<IndicacaoModel>> filmesIndicadosPorCategoria = null;
        List<IndicacaoModel> listaFilmes = null;
        for (IndicacaoModel indicacao : this.indicacoes) {
            if (indicacao.getIndicavel() instanceof FilmeModel) {
                listaFilmes.add(indicacao);
            }
        }
        if (listaFilmes != null) {
            filmesIndicadosPorCategoria = listaFilmes.stream().collect(Collectors.groupingBy(IndicacaoModel::getCategoria));
        }

        return filmesIndicadosPorCategoria;
    }

}
