import java.io.File;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        Filme filme = new Filme();

        // fazer a conexao HTTP e buscar os filmes
        ApiConsuption apiConsuption = new ApiConsuption();
        if (!apiConsuption.apiInvoke()) {
            // apiConsuption.printFilmes();
            System.out.println("Erro ao buscar os filmes");
        }

        do {
            System.out.println("Qual o tipo de consulta? (id, rank)");
            String type = scanner.nextLine();
            if (type.equals("id")) {
                System.out.println("Qual o id do filme?");
                String id = scanner.nextLine();
                if (apiConsuption.printFilmePorID(id)) {
                    continuar = true;
                    filme = apiConsuption.getFilmePorID(id);

                } else {
                    System.out.println("Erro ao buscar o filme");
                }

            } else if (type.equals("rank")) {
                System.out.println("Qual o rank do filme?");
                String rank = scanner.nextLine();
                if (apiConsuption.printFilmePorRank(rank)) {
                    continuar = true;
                    filme = apiConsuption.getFilmePorRank(rank);

                } else {
                    System.out.println("Erro ao buscar o filme");
                }
            } else {
                System.out.println("Tipo de consulta inválida");
            }
        } while (!continuar);
        continuar = false;
        do {
            System.out.println("Qual a sua nota? (1 a 10)");
            try {
                Double nota = Double.parseDouble(scanner.nextLine());
                if (nota >= 0 && nota <= 10) {
                    filme.setImDbRating(nota.toString());
                    apiConsuption.setNotaFilme(filme, nota.toString());
                    continuar = true;
                    // apiConsuption.printFilmePorRank(filme.getRank());
                } else {
                    System.out.println("Nota inválida");
                }
            } catch (Exception e) {
                System.out.println("Nota inválida");
            }

        } while (!continuar);
        System.out.println("Nota atualizada");
        System.out.println();
        apiConsuption.printFilmes();
        scanner.close();
    }
}
