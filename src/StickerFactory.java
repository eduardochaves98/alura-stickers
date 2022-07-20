import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.awt.*;
import javax.imageio.ImageIO;

public class StickerFactory {
    public void create(String url, String text) throws IOException {
        String formatedUrl = url.substring(0, url.indexOf("@._V1_UX") + 8);
        BufferedImage image = ImageIO.read(new URL(formatedUrl));
        int largura = image.getWidth();
        int altura = image.getHeight();
        int novaAltura = altura;

        if (!text.equals("")) {
            novaAltura = (int) (altura + (double) altura * 0.2);
        }

        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, 60);

        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);

        graphics.drawString(text, 100, novaAltura - 100);

        ImageIO.write(novaImagem, "png", new File("imgs/sticker.png"));
    }
}
