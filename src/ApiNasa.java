import java.io.IOException;
import java.util.List;

public class ApiNasa {
    public static void main(String[] args) {
        ApiConsuption apiConsupiton = new ApiConsuption();
        try {
            apiConsupiton.apiInvoke();
        } catch (Exception e) {
        }
        List<NasaPicture> pictures = apiConsupiton.ParsePictures();
        int iterator = 0;
        for (NasaPicture picture : pictures) {
            System.out.println("Copyright: " + picture.getCopyright());
            System.out.println("Date: " + picture.getDate());
            System.out.println("Url: " + picture.getUrl());
            System.out.println("Title: " + picture.getTitle());
            System.out.println();
            StickerFactory stickerFactory = new StickerFactory();
            try {
                stickerFactory.create(picture.getUrl(), iterator);
                iterator++;
            } catch (IOException e) {
            }
        }
    }
}
