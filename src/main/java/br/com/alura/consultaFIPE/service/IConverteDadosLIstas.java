package br.com.alura.consultaFIPE.service;

import java.util.List;

public interface IConverteDadosLIstas {
    <T> List<T> obterDados(String json, Class<T> classe);
}
