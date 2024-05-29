package com.example.oscar.repositories;

import com.example.oscar.models.AtorModel;
import com.example.oscar.models.FilmeModel;
import com.example.oscar.models.IndicacaoModel;
import com.example.oscar.models.Indicavel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.MessageFormat;

@Repository
public class FileStorageService {

    public void store(IndicacaoModel indicacaoModel) {
        try {
            FileOutputStream file = new FileOutputStream("indicacoes.txt", true);
            PrintWriter printWriter = new PrintWriter(file);
            String informacoesIndicado = informacoesIndicavel(indicacaoModel.getIndicavel());
            if (informacoesIndicado != null) {
                printWriter.append(informacoesIndicado);
            }
            printWriter.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Não foi possível adicionar as informações ao arquivo.");
        }
    }

    public String informacoesIndicavel(Indicavel indicavel) {

        if (indicavel instanceof AtorModel) {
            AtorModel atorModel = new AtorModel();
            BeanUtils.copyProperties(indicavel, atorModel);
            return MessageFormat.format("O ator {0} {1} foi indicado {2} vezes ao oscar", atorModel.getNacionalidade(), atorModel.getNomeAtor(), atorModel.getNumeroDeIndicacoes());
        } else if (indicavel instanceof FilmeModel) {
            FilmeModel filmeModel = new FilmeModel();
            BeanUtils.copyProperties(indicavel, filmeModel);
            return MessageFormat.format("O filme {0} do genero {1} foi indicado {2} vezes ao oscar", filmeModel.getNomeFilme(), filmeModel.getGeneroFilme(), filmeModel.getNumeroDeIndicacoes());
        }
        return null;
    }

}
