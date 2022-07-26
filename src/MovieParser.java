
import java.util.List;

public class MovieParser {
    // List<String> items = new ArrayList<String>();
    List<Filme> items;
    String errorMessage;

    public List<Filme> getItems() {
        return items;
    }

    public void setItems(List<Filme> items) {
        this.items = items;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
