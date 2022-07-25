import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        String url = "https://wderlan-alura-api.herokuapp.com/linguagens";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoIMDB();


        

        var http = new ClienteHttp();
        var json = http.buscaDados(url);


        // Exibir e manipular os dados
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var gerador = new GeradoraDeFigurinhas();


        for (int i = 0; i < conteudos.size(); i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeFigurinha = conteudo.getTitulo() + ".png";

            gerador.cria(inputStream, nomeFigurinha);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }
    }
}

