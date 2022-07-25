import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {

    public void cria(InputStream inputStream, String nomeArquivo ) throws Exception {
        // leitura da imagem
        // InputStream inputStream = new FileInputStream(new File("https://imersao-java-apis.s3.amazonaws.com/TopMovies_2.jpg"));
        // InputStream inputStream = new URL("https://imersao-java-apis.s3.amazonaws.com/TopMovies_2.jpg").openStream();

        // O imageIO.read está lendo um InputStream que é uma fonte de dados, no final não importa de onde ela vem.
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em memória com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original pra nova imagem (em memória)
        // graphics é a minha caneta
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // configurar fonte

        Font fontText = new Font(Font.MONOSPACED, Font.PLAIN, 0);

        if(largura <= 300){
            fontText = new Font(Font.MONOSPACED, Font.PLAIN, 24);
        } else if(largura >= 301 && largura <= 600 ) {
            fontText = new Font(Font.MONOSPACED, Font.PLAIN, 40);
        } else {
            fontText = new Font(Font.MONOSPACED, Font.PLAIN, 70);
        }

        graphics.setColor(Color.ORANGE);
        graphics.setFont(fontText);

        // escrever uma frase na imagem
        String frase = "Odin aprovou";
        graphics.drawString(frase, (largura / 2) - (graphics.getFontMetrics().stringWidth(frase)/2) , novaAltura - 100);


        // escrever a nova imagem em um arquivo
        File diretorio = new File("saida");
        if(!diretorio.exists()){
            diretorio.mkdirs();
        }
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }

    
}
