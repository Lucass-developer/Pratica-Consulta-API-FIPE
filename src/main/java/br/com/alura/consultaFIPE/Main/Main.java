package br.com.alura.consultaFIPE.Main;

import br.com.alura.consultaFIPE.model.DadosAnos;
import br.com.alura.consultaFIPE.model.DadosMarcas;
import br.com.alura.consultaFIPE.model.DadosModelosResponse;
import br.com.alura.consultaFIPE.service.ConsumoApi;
import br.com.alura.consultaFIPE.service.ConverteDados;
import br.com.alura.consultaFIPE.service.ConverteListas;
import br.com.alura.consultaFIPE.service.ExibirMenu;

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
        String modelo = exibirMenu.exbirMenu();

        String endereco = "https://parallelum.com.br/fipe/api/v1/";
        var json = consumo.obterDados(endereco + modelo + "/marcas");

        List<DadosMarcas> dadosMarcas = conversorListas.converterListas(json, DadosMarcas.class);
        dadosMarcas.forEach(System.out::println);

        String modeloEscolhido;

        while (true) {
            System.out.println("Digite o Codigo do Modelo Dejesado:");
            var codigoModelo = scanner.nextLine();

            if (dadosMarcas.stream().noneMatch(d -> d.codigo().equals(codigoModelo))){
                System.out.println("Modelo Nao encontrado!");
            } else {
                modeloEscolhido = codigoModelo;
                break;
            }
        }

        var jsonModelos = consumo.obterDados(endereco + modelo + "/marcas/" + modeloEscolhido + "/modelos");

        DadosModelosResponse response = conversorDados.converterDados(jsonModelos, DadosModelosResponse.class);
        response.modelos().forEach(System.out::println);

        while (true) {
            System.out.println("Digite o nome do modelo para melhor vizualização:");
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

                var jsonDadosfinal = consumo.obterDados(endereco + modelo + "/marcas/" + modeloEscolhido + "/modelos/" + codModelo + "/anos");

                List<DadosAnos> dadosAnos = conversorListas.converterListas(jsonDadosfinal, DadosAnos.class);

                for (int i = 0 ; i < dadosAnos.size();) {

                    i++;
                }
                break;
            } else {
                System.out.println("Modelo não encontrado!");
            }
        }
        System.out.println("****** VOLTE SEMPRE ******");
    }
}
