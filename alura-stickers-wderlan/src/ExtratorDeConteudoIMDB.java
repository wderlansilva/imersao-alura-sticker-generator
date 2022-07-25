import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoIMDB implements ExtratorDeConteudo {

    public List<Conteudo> extraiConteudos(String json){
        // Pegar só os dados que nos interassam (Title, poster, classificação)
        var parser = new JsonParser();
        List<Map<String,String>> listaDeConteudo = parser.parse(json);

        List<Conteudo> conteudos = new ArrayList<>();

        for (Map<String,String> atributos: listaDeConteudo ) {
            String titulo = atributos.get("title");
            String urlImagem = atributos.get("image");
            var conteudo = new Conteudo(titulo, urlImagem);

            conteudos.add(conteudo);
        }

        return conteudos;
    }
}
