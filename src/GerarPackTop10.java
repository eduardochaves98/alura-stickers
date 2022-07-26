public class GerarPackTop10 {
    public static void main(String[] args) throws Exception {
        ApiConsuption apiConsuption = new ApiConsuption();

        apiConsuption.apiInvoke();
        int qntdFilmes = apiConsuption.ParseFilmes();
        if (qntdFilmes == 0) {
            // apiConsuption.printFilmes();
            System.out.println("Erro ao buscar os filmes");
        } else {
            System.out.println("Filmes buscados com sucesso");
            System.out.println(qntdFilmes + " filmes encontrados");
        }

        for (int i = 1; i <= 10; i++) {
            Filme filme = apiConsuption.getFilmePorRank(i + "");
            try {
                System.out.println(filme.getTitle());
                // stickerFactory.create(filme.getImage(), filme.getTitle(), filme.getTitle());
            } catch (Exception ignored) {

            }

        }

    }
}
