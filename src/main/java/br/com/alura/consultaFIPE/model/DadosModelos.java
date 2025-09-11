package br.com.alura.consultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos (String nome, String codigo) {

    @Override
    public String toString() {
        return "Codigo: " + codigo + " - Modelo: " + nome;
    }
}
