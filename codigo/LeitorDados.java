package codigo;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class LeitorDados {
    public static List<ConjuntoTeste> conjuntosTeste = new ArrayList<>();

    public static void lerDados(String caminhoArquivo) throws IOException {
        System.out.println("Diretório de trabalho atual: " + Paths.get("").toAbsolutePath().toString());

        List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
        List<Oferta> ofertas = new ArrayList<>();
        int capacidadeTotal = 0;

        for (String linha : linhas) {
            linha = linha.trim();
            if (linha.isEmpty()) {
                continue;
            }

            if (linha.matches("\\d+")) {
                if (!ofertas.isEmpty()) {
                    conjuntosTeste.add(new ConjuntoTeste(capacidadeTotal, new ArrayList<>(ofertas)));
                    ofertas.clear();
                }
                capacidadeTotal = Integer.parseInt(linha);
            } else {
                String[] partes = linha.split(";");
                String nome = partes[0];
                int megawatts = Integer.parseInt(partes[1]);
                int valor = Integer.parseInt(partes[2]);
                ofertas.add(new Oferta(nome, megawatts, valor));
            }
        }
        if (!ofertas.isEmpty()) {
            conjuntosTeste.add(new ConjuntoTeste(capacidadeTotal, ofertas));
        }
    }

    public static class ConjuntoTeste {
        public int capacidadeTotal;
        public List<Oferta> ofertas;

        public ConjuntoTeste(int capacidadeTotal, List<Oferta> ofertas) {
            this.capacidadeTotal = capacidadeTotal;
            this.ofertas = ofertas;
        }
    }
}
