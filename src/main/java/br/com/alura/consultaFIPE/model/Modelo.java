package br.com.alura.consultaFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelo (@JsonAlias("Valor") String valor,
                      @JsonAlias("Marca") String marca,
                      @JsonAlias("Modelo") String modelo,
                      @JsonAlias("AnoModelo") String ano,
                      @JsonAlias("Combustivel") String tipoCombustivel,
                      @JsonAlias("MesReferencia") String mesReferencia){
    @Override
    public String toString() {
        return "TABELA FIPE POR ANO:" +
                "\nMODELO: " + modelo +
                "\nANO: " + ano +
                "\nVALOR: " + valor +
                "\nMARCA: " + marca +
                "\nCOMBUSTIVEL: " + tipoCombustivel +
                "\nMES DE REFERENCIA: " + mesReferencia +
                "\n";
    }
}
