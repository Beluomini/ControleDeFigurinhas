/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas
 */
public class ListaPerfil {
    
    List<Perfilo> lista = new ArrayList<>();

    public void inserir(Perfilo perfilo) {
        lista.add(perfilo);
    }

    public List<String> listar() {
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            ls.add(""
                    + lista.get(i).getNome() + ";"
                    + lista.get(i).getIdade() + ";"
                    + lista.get(i).getTime() + ";"
                    + lista.get(i).getDatan()
            );
        }
        return ls;
    }
    public int Limpar(String caminho, String texto) {
       try {
           // Create file 
           FileWriter arquivo = new FileWriter(caminho);
           BufferedWriter conteudoDoArquivo = new BufferedWriter(arquivo);
           conteudoDoArquivo.write(texto); // 
           
           conteudoDoArquivo.close();
       } catch (Exception e) {//Catch exception if any
           System.err.println("Error: " + e.getMessage());
           return 1; //houve erro
       }
       return 0;
   }
    
    public List<String> criar(String nome, String idade, String time, String datan){
        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            ls.add( nome + ";"
                    + idade + ";"
                    + time + ";"
                    + datan
            );
        }
        return ls;
    }
}
