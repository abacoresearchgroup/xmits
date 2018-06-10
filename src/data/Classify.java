/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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

    public Classify(){
    }

    public void gravar(String teste, File file) {
        try {
            FileWriter arq = new FileWriter("words"+file.getName()+".txt", true);
            BufferedWriter escArq = new BufferedWriter(arq);

            escArq.append(teste);
            escArq.newLine();
            escArq.close();
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
                    e.getMessage());
        }
    }
}
