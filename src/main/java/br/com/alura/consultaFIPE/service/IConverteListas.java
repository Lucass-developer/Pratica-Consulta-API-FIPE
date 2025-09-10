package br.com.alura.consultaFIPE.service;

import java.util.List;

public interface IConverteListas {
    <T> List<T> converterListas(String json, Class<T> classe);
}
