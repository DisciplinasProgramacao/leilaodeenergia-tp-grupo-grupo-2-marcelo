package codigo;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class LeitorDados {
    public static int capacidadeTotal;
    public static List<Oferta> ofertas = new ArrayList<>();

    public static void lerDados(String caminhoArquivo) throws IOException {
        // Imprime o diretório de trabalho atual
        System.out.println("Diretório de trabalho atual: " + Paths.get("").toAbsolutePath().toString());

        List<String> linhas = Files.readAllLines(Paths.get(caminhoArquivo));
        capacidadeTotal = Integer.parseInt(linhas.get(0).trim());

        for (int i = 1; i < linhas.size(); i++) {
            String[] partes = linhas.get(i).split(";");
            String nome = partes[0];
            int megawatts = Integer.parseInt(partes[1]);
            int valor = Integer.parseInt(partes[2]);
            ofertas.add(new Oferta(nome, megawatts, valor));
        }
    }
}
