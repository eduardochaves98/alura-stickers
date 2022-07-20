import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        boolean continuar = false;
        Filme filme = new Filme();

        // fazer a conexao HTTP e buscar os filmes
        ApiConsuption apiConsuption = new ApiConsuption();
        StickerFactory stickerFactory = new StickerFactory();
        int qntdFilmes = apiConsuption.apiInvoke();
        if (qntdFilmes == 0) {
            // apiConsuption.printFilmes();
            System.out.println("Erro ao buscar os filmes");
        } else {
            System.out.println("Filmes buscados com sucesso");
            System.out.println(qntdFilmes + " filmes encontrados");
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
                    System.out.println("Qual o texto do Sticker? (Vazio para padrao)");
                    String text = scanner.nextLine();
                    stickerFactory.create(filme.getImage(), text);

                } else {
                    System.out.println("Erro ao buscar o filme");
                }
            } else {
                System.out.println("Tipo de consulta inválida");
            }
        } while (!continuar);
        // continuar = false;
        // do {
        // System.out.println("Qual a sua nota? (1 a 10)");
        // try {
        // Double nota = Double.parseDouble(scanner.nextLine());
        // if (nota >= 0 && nota <= 10) {
        // filme.setImDbRating(nota.toString());
        // apiConsuption.setNotaFilme(filme, nota.toString());
        // continuar = true;
        // // apiConsuption.printFilmePorRank(filme.getRank());
        // } else {
        // System.out.println("Nota inválida");
        // }
        // } catch (Exception e) {
        // System.out.println("Nota inválida");
        // }

        // } while (!continuar);
        // System.out.println("Nota atualizada");
        // System.out.println();
        // apiConsuption.printFilmes();
        scanner.close();
    }
}
