/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Camila
 */
public class Classify {

    String propriedade;
    ArrayList<String> palavrasDiagrama = new ArrayList<String>();

    ArrayList<String> palavras = new ArrayList<String>();
    ArrayList<String> verbos = new ArrayList<String>();
    ArrayList<String> propiedadeVerbos = new ArrayList<String>();
    ArrayList<String> propiedadePreposicoes = new ArrayList<String>();
    ArrayList<String> propiedadeArtigos = new ArrayList<String>();
    ArrayList<String> propriedadeSubstantivos = new ArrayList<String>();

    public Classify(String propriedade, String[] palavrasDiagrama) {
        this.propriedade = propriedade;
        this.propriedade = this.propriedade.replaceAll(Pattern.quote("."), "");
        this.propriedade = this.propriedade.replaceAll(Pattern.quote(","), "");
        String[] array = this.propriedade.split(" ");

        List listaP = Arrays.asList(array);
        this.palavras = new ArrayList(listaP);

        List lista = Arrays.asList(palavrasDiagrama);
        this.palavrasDiagrama = new ArrayList(lista);

        this.verbos = loadVerbs();
    }

    private ArrayList<String> loadVerbs() {
        ArrayList<String> conteudo = new ArrayList<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("verbos.txt"));
            String str;
            while ((str = in.readLine()) != null) {
                conteudo.add(str);
            }
            in.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return conteudo;
    }

    public int countVerbos() {
        ArrayList<String> copPalavras = (ArrayList<String>) palavras.clone();

        int count = 0;
        for (int i = 0; i < palavras.size(); i++) {
            if (this.verbos.contains(palavras.get(i).toLowerCase())) {
                count++;
                this.propiedadeVerbos.add(palavras.get(i).toLowerCase());
                copPalavras.remove(palavras.get(i));
            }
        }
        return count;
    }

//C:\Users\Camila\Desktop\xmits\src\data
    public int countArtigos() {
        String artigos = "o a os as um uma uns umas";
        ArrayList<String> copPalavras = (ArrayList<String>) palavras.clone();
        int count = 0;
        for (int i = 0; i < palavras.size(); i++) {
            System.out.println(palavras);

            if (artigos.toLowerCase().contains(palavras.get(i).toLowerCase())) {
                count++;
                this.propiedadeArtigos.add(palavras.get(i).toLowerCase());
                copPalavras.remove(palavras.get(i));
            }
        }
        this.palavras = copPalavras;
        return count;
    }

    public int countPreposicoes() {
        String preposicoes = "a ante após até com contra de desde em entre para perante por sem sob sobre trás conforme como durante exceto fora ediante segundo senão salvo";
        ArrayList<String> copPalavras = (ArrayList<String>) palavras.clone();
        int count = 0;
        for (int i = 0; i < palavras.size(); i++) {

            if (preposicoes.toLowerCase().contains(palavras.get(i).toLowerCase())) {
                count++;
                this.propiedadePreposicoes.add(palavras.get(i).toLowerCase());
                copPalavras.remove(palavras.get(i));
            }
        }
        this.palavras = copPalavras;
        return count;
    }

    //-------------------------------Diagrama
    public int DiagramCountVerbos() {
        ArrayList<String> copPalavras = (ArrayList<String>) palavrasDiagrama.clone();

        int count = 0;
        for (int i = 0; i < palavrasDiagrama.size(); i++) {
            if (this.verbos.contains(palavrasDiagrama.get(i).toLowerCase())) {
                System.out.println(palavrasDiagrama.get(i));
                count++;
                this.propiedadeVerbos.add(palavrasDiagrama.get(i).toLowerCase());
                copPalavras.remove(palavrasDiagrama.get(i));
            }
        }
        return count;
    }

//C:\Users\Camila\Desktop\xmits\src\data
    public int DiagramCountArtigos() {
        String artigos = "o a os as um uma uns umas";
        ArrayList<String> copPalavras = (ArrayList<String>) palavrasDiagrama.clone();
        int count = 0;
        for (int i = 0; i < palavrasDiagrama.size(); i++) {

            if (artigos.toLowerCase().contains(palavrasDiagrama.get(i).toLowerCase())) {
                count++;
                this.propiedadeArtigos.add(palavrasDiagrama.get(i).toLowerCase());
                copPalavras.remove(palavrasDiagrama.get(i));
            }
        }
        this.palavrasDiagrama = copPalavras;
        return count;
    }

    public int DiagramCountPreposicoes() {
        String preposicoes = "a ante após até com contra de desde em entre para perante por sem sob sobre trás conforme como durante exceto fora ediante segundo senão salvo";
        ArrayList<String> copPalavras = (ArrayList<String>) palavrasDiagrama.clone();
        int count = 0;
        for (int i = 0; i < palavrasDiagrama.size(); i++) {

            if (preposicoes.toLowerCase().contains(palavrasDiagrama.get(i).toLowerCase())) {
                count++;
                this.propiedadePreposicoes.add(palavrasDiagrama.get(i).toLowerCase());
                copPalavras.remove(palavrasDiagrama.get(i));
            }
        }
        this.palavrasDiagrama = copPalavras;
        return count;
    }
    //--------------------Em Comum----------

    public void teste() {
        int prepoComum = countPreposicoesDiagrama();

        int verboComum = countVerbosDiagrama();
        System.out.println(prepoComum);
        System.out.println(prepoComum);

    }

    public int countPreposicoesDiagrama() {
        ArrayList<String> copPalavras = (ArrayList<String>) propiedadePreposicoes.clone();
        int count = 0;
        for (int i = 0; i < palavrasDiagrama.size(); i++) {
            if (propiedadePreposicoes.contains(palavrasDiagrama.get(i).toLowerCase())) {
                count++;
                this.propiedadePreposicoes.add(palavrasDiagrama.get(i).toLowerCase());
                copPalavras.remove(palavrasDiagrama.get(i));
            }
        }
        this.palavrasDiagrama = copPalavras;
        return count;
    }

    public int countVerbosDiagrama() {
        ArrayList<String> copPalavras = (ArrayList<String>) propiedadeVerbos.clone();
        int count = 0;
        for (int i = 0; i < palavrasDiagrama.size(); i++) {
            if (propiedadeVerbos.contains(palavrasDiagrama.get(i).toLowerCase())) {
                count++;
                this.propiedadePreposicoes.add(palavrasDiagrama.get(i).toLowerCase());
                copPalavras.remove(palavrasDiagrama.get(i));
            }
        }
        this.palavrasDiagrama = copPalavras;
        return count;
    }

    public void gravar() {
        try {
            FileWriter arq = new FileWriter("classify.csv", true);
            BufferedWriter escArq = new BufferedWriter(arq);

            escArq.append(this.countArtigos() + ";");
            escArq.append(this.countPreposicoes() + ";");
            escArq.append(this.countVerbos() + ";");
            escArq.append(";");

            escArq.append(this.DiagramCountArtigos() + ";");
            escArq.append(this.DiagramCountPreposicoes() + ";");
            escArq.append(this.DiagramCountVerbos() + ";");
            escArq.append(";");

            escArq.newLine();

            escArq.close();
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
}
