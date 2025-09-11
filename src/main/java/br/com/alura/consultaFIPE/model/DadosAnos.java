package br.com.alura.consultaFIPE.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosAnos(String codigo) {
    @Override
    public String toString() {
        return codigo;
    }
}
