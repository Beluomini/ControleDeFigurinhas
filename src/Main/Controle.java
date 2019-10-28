package Main;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author radames
 */
public class Controle {

    List<Figurinha> lista = new ArrayList<>();


    public Figurinha buscar(int chave, String qualAlbum) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).toString());
            if (chave==lista.get(i).getFigurinha() && qualAlbum.equals(lista.get(i).getAlbum())) {
                return lista.get(i);//se encontrou, retorna a linha toda (um contato)
            }
        }
        return null; //se não encontrou na lista, retorna um contato nulo
    }

    public void inserir(Figurinha figurinha) {
        lista.add(figurinha);
    }

    public List<String> listar() {
        
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            ls.add(""
                    + lista.get(i).getFigurinha() + ";"
                    + lista.get(i).getAlbum()
            );
        }
        return ls;
    }
    
    public int qtdFigurinhasTotal (){
        int qtd = 0;
        for (int i = 0; i < lista.size(); i++) {
            qtd+=1;
        }
        return qtd;
    }
    
    public void descolar(Figurinha figurinha){
        lista.remove(figurinha);
    }
}
