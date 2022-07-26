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
        int fontSize = 60;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);

        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        setTextCenter(graphics, text, novaImagem);

        ImageIO.write(novaImagem, "png", new File("imgs/sticker.png"));
    }

    public void create(String url, String text, String name) throws IOException {
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
        int fontSize = 60;
        Font font = new Font(Font.SANS_SERIF, Font.BOLD, fontSize);

        graphics.setFont(font);
        graphics.setColor(Color.YELLOW);
        setTextCenter(graphics, text, novaImagem);

        String fileName = "C:/Users/Eduardo/Imersao Java Alura/alura-stickers/imgs/" + name.replace(":", "-") + ".png";

        ImageIO.write(novaImagem, "png", new File(fileName));
    }

    public void create(String url, int name) throws IOException {
        String formatedUrl = url;
        BufferedImage image = ImageIO.read(new URL(formatedUrl));
        int largura = image.getWidth();
        int altura = image.getHeight();

        ImageIO.write(image, "png", new File("imgs/nasa/sticker" + name + ".png"));
    }

    // Metodo para centralizar e escrever o texto no sticker

    private static void setTextCenter(Graphics2D graphics2DImage, String string,
            BufferedImage bgImage) {
        int stringWidthLength = (int) graphics2DImage.getFontMetrics().getStringBounds(string, graphics2DImage)
                .getWidth();
        int stringHeightLength = (int) graphics2DImage.getFontMetrics().getStringBounds(string, graphics2DImage)
                .getHeight();

        int horizontalCenter = bgImage.getWidth() / 2 - stringWidthLength / 2;
        int verticalCenter = bgImage.getHeight() - stringHeightLength;
        graphics2DImage.drawString(string, horizontalCenter, verticalCenter);
    }
}
