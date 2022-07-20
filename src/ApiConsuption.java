import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.google.gson.Gson;

public class ApiConsuption {
    String body;
    List<Filme> filmes;

    public int apiInvoke() throws Exception {
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        setBody(response.body());
        Gson gson = new Gson();
        Parser parser = gson.fromJson(body, Parser.class);
        filmes = parser.getItems();
        if (!parser.getErrorMessage().equals("")) {
            System.out.println(parser.getErrorMessage());
            return filmes.size();
        } else {
            return filmes.size();
        }
    }

    public void printFilmes() {
        for (Filme filme : filmes) {
            System.out.println("Rank: " + filme.getRank());
            System.out.println("ID: " + filme.getId());
            System.out.println("Titulo: " + filme.getTitle());
            System.out.println("Poster: " + filme.getImage());
            System.out.println("Nota: " + filme.getImDbRating());
        }
    }

    public boolean printFilmePorID(String id) {
        for (Filme filme : filmes) {
            if (filme.getId().equals(id)) {
                System.out.println("Rank: " + filme.getRank());
                System.out.println("ID: " + filme.getId());
                System.out.println("Titulo: " + filme.getTitle());
                System.out.println("Poster: " + filme.getImage());
                System.out.println("Nota: " + filme.getImDbRating());
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public boolean printFilmePorRank(String rank) {
        for (Filme filme : filmes) {
            if (filme.getRank().equals(rank)) {
                System.out.println("Rank: " + filme.getRank());
                System.out.println("ID: " + filme.getId());
                System.out.println("Titulo: " + filme.getTitle());
                System.out.println("Poster: " + filme.getImage());
                System.out.println("Nota: " + filme.getImDbRating());
                System.out.println();
                return true;
            }
        }
        return false;
    }

    public Filme getFilmePorID(String id) {
        for (Filme filme : filmes) {
            if (filme.getRank().equals(id)) {
                return filme;
            }
        }
        return null;
    }

    public Filme getFilmePorRank(String rank) {
        for (Filme filme : filmes) {
            if (filme.getRank().equals(rank)) {
                return filme;
            }
        }
        return null;
    }

    public void setNotaFilme(Filme filme, String nota) {
        filme.setImDbRating(nota);
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}
