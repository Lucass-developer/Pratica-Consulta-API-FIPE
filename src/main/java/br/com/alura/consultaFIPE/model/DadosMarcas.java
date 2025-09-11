package br.com.alura.consultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosMarcas (String codigo, String nome) {

    @Override
    public String toString() {
        return "Codigo: " + codigo + " - Marcas: " + nome;
    }
}
