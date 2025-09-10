package br.com.alura.consultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos (@JsonAlias("nome") String nome,
                            @JsonAlias("codigo") String codigo) {

    @Override
    public String toString() {
        return "Codigo: " + codigo + " - Modelo: " + nome;
    }
}
