package br.com.alura.consultaFIPE.Main;

import br.com.alura.consultaFIPE.model.DadosAnos;
import br.com.alura.consultaFIPE.model.DadosMarcas;
import br.com.alura.consultaFIPE.model.DadosModelosResponse;
import br.com.alura.consultaFIPE.model.Modelo;
import br.com.alura.consultaFIPE.service.ConsumoApi;
import br.com.alura.consultaFIPE.service.ConverteDados;
import br.com.alura.consultaFIPE.service.ConverteListas;
import br.com.alura.consultaFIPE.service.ExibirMenu;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private final ExibirMenu exibirMenu = new ExibirMenu();
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteListas conversorListas = new ConverteListas();
    private final ConverteDados conversorDados = new ConverteDados();

    Scanner scanner = new Scanner(System.in);

    public void consultaFipe() {

        System.out.println("***** CONSULTA FIPE *****");
        String URI_BASE = exibirMenu.exbirMenu();

        var json = consumo.obterDados(URI_BASE);

        List<DadosMarcas> dadosMarcas = conversorListas.converterListas(json, DadosMarcas.class);
        dadosMarcas.stream()
                .sorted(Comparator.comparing(DadosMarcas::codigo))
                .forEach(System.out::println);

        while (true) {
            System.out.println("Digite o Codigo do Modelo Dejesado:");
            var codigoModelo = scanner.nextLine();

            if (dadosMarcas.stream().noneMatch(d -> d.codigo().equals(codigoModelo))){
                System.out.println("Modelo Nao encontrado!");
            } else {
                URI_BASE += codigoModelo + "/modelos/";
                break;
            }
        }

        var jsonModelos = consumo.obterDados(URI_BASE);

        DadosModelosResponse response = conversorDados.converterDados(jsonModelos, DadosModelosResponse.class);
        response.modelos().forEach(System.out::println);

        while (true) {
            System.out.println("Digite o nome do modelo para buscar:");
            var nomeEscolhido = scanner.nextLine();

            if (response.modelos().stream().anyMatch(m -> m.nome().toUpperCase().contains(nomeEscolhido.toUpperCase()))) {
                response.modelos().stream()
                        .filter(m -> m.nome().toUpperCase().contains(nomeEscolhido.toUpperCase()))
                        .forEach(System.out::println);
                break;
            } else {
                System.out.println("Modelo não encontrado");
            }
        }

        while (true) {
            System.out.println("Digite o codigo do modelo que dejesa ver a tabela FIPE:");
            var codModelo = scanner.nextLine();

            if (response.modelos().stream().anyMatch(m -> m.codigo().contains(codModelo))) {

                URI_BASE += codModelo + "/anos/";

                var jsonDadosfinal = consumo.obterDados(URI_BASE);

                List<DadosAnos> dadosAnos = conversorListas.converterListas(jsonDadosfinal, DadosAnos.class);

                for (DadosAnos ano : dadosAnos) {
                    var jsonModelo = consumo.obterDados(URI_BASE + ano);

                    Modelo novoModelo = conversorDados.converterDados(jsonModelo, Modelo.class);

                    System.out.println(novoModelo);
                }
                break;
            } else {
                System.out.println("Modelo não encontrado!");
            }
        }
        System.out.println("****** VOLTE SEMPRE ******");
    }
}
