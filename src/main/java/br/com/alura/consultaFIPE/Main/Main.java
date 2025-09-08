package br.com.alura.consultaFIPE.Main;

import br.com.alura.consultaFIPE.model.DadosMarcas;
import br.com.alura.consultaFIPE.service.ConsumoApi;
import br.com.alura.consultaFIPE.service.ConverteDadosListas;
import br.com.alura.consultaFIPE.service.ExibirMenu;

import java.util.List;

public class Main {

    private final String endereco = "https://parallelum.com.br/fipe/api/v1/";
    private final String marcas = "/marcas";
    private final String modelos = "/modelos";
    private final String anos = "/anos";

    private final ExibirMenu exibirMenu = new ExibirMenu();
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDadosListas conversor = new ConverteDadosListas();

    public void consultaFipe() {

        System.out.println("***** CONSULTA FIPE *****");
        String modelo = exibirMenu.exbirMenu();

        var json = consumo.obterDados(endereco + modelo + marcas);

        List<DadosMarcas> dadosModelos = conversor.obterDados(json, DadosMarcas.class);
        dadosModelos.forEach(System.out::println);
    }
}
