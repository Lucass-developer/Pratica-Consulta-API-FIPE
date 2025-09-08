package br.com.alura.consultaFIPE.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class ConverteDadosListas implements IConverteDadosLIstas {
    private final ObjectMapper mapper =  new ObjectMapper();

    @Override
    public <T> List<T> obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, classe));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
