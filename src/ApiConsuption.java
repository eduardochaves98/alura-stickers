
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import java.util.List;
import java.util.Properties;

import com.google.gson.Gson;

public class ApiConsuption {
    String body;
    List<Filme> filmes;
    List<NasaPicture> pictures;
    Gson gson = new Gson();
    List<String> keys = new ArrayList<String>();

    public static Properties getProp() throws IOException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(
                "./config.properties"));
        return prop;
    }

    public Gson apiInvoke() throws Exception {
        Properties prop = getProp();
        String url = prop.getProperty("prop.server.url");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        setBody(response.body());
        Gson gson = new Gson();
        this.gson = gson;
        return gson;
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

    public int ParseFilmes() {
        MovieParser parser = gson.fromJson(body, MovieParser.class);
        filmes = parser.getItems();
        if (!parser.getErrorMessage().equals("")) {
            System.out.println(parser.getErrorMessage());

            return filmes.size();
        } else {
            return filmes.size();
        }
    }

    public List<NasaPicture> ParsePictures() {
        body = "{itens: " + body + "}";
        ParseNasa parser = gson.fromJson(body, ParseNasa.class);
        pictures = parser.getMaster();
        return pictures;
    }

    // @SuppressWarnings("unchecked")
    // public Object ParsePictures() {
    // ArrayList<LinkedTreeMap<String, String>> json = gson.fromJson(body,
    // ArrayList.class);

    // for (LinkedTreeMap<String, String> map : json) {
    // picture = new NasaPicture(map);
    // pictures.add(this.picture);
    // }
    // System.out.println(picture);
    // return pictures;
    // }

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
