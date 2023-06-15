/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dario
 */
public class Aplicacion {
    
    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        String menu = """
                      
                                  ¿Qué quieres hacer?
                                   
                                   1. Leer el archivo, cargar el contenido en una matriz e imprimirla
                                   2. Leer el archivo, cargar el contenido en una matriz, realizar la invertida e imprimirla
                                   3. Leer el archivo, cargar el contenido en una matriz, realizar la invertida y volcarlo en un fichero txt
                                   4. Leer el archivo, cargar el contenido en una matriz y ordenar cada fila de mayor a menor e imprimirla
                                   5. Salir 
                                   """;

        int respuesta;

        do {
            System.out.println(menu);
            respuesta = teclado.nextInt();

            switch (respuesta) {
                case 1 -> {

                    int[][] matriz = leerFichero1("./matrizP(1).txt");
                    imprimirMatriz(matriz);

                }
                case 2 -> {
                    
                    int[][] matriz = leerFichero1("./matrizP(1).txt");
                    int[][] inversa = matrizInversa(matriz);
                    imprimirMatriz(inversa);
                      
                }
                case 3 -> {
                    
                    int[][] matriz = leerFichero1("./matrizP(1).txt");
                    int[][] inversa = matrizInversa(matriz);                    
                    String ruta = JOptionPane.showInputDialog("¿Cómo quieres llamar al fichero?");
                    escribirInversa(inversa, ruta + ".txt");
                    
                }
                case 4 -> {
                    
                    int[][] matriz = leerFichero1("./matrizP(1).txt");                    
                    int[][] ordenada = mayorAMenor(matriz);
                    imprimirMatriz(ordenada);
                    
                }
                case 5 -> {
                    break;
                }
                default ->
                    throw new AssertionError();
            }

        } while (respuesta != 5);

    }
    
    // Métodos        
    
    // Método para contar las líneas de la matriz
    private static List<String> lineasMatriz(String ruta){
            
        List<String> lineas = null;
        
        try {
            lineas = Files.readAllLines(Path.of(ruta));            
        } catch (IOException ex) {
            Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lineas;
        
    }
    
    // Método para imprimir una matriz
    private static void imprimirMatriz(int[][] matriz){
    
         for (int i = 0; i < matriz.length; i++) {
             for (int j = 0; j < matriz[i].length; j++) {
                 System.out.print(matriz[i][j] + " ");                 
             }
             System.out.println("");
        }
    
    }
    
    // Método para leer el fichero (caso 1)
    private static int[][] leerFichero1(String ruta) {
        List<String> lineas = lineasMatriz(ruta);
        int[][] matriz = new int[lineas.size()][];

        try ( Scanner flujo = new Scanner(new FileReader(ruta))) {

            while (flujo.hasNext()) {

                for (int i = 0; i < matriz.length; i++) {
                    String linea = "";
                    String[] tokens;
                    linea = flujo.nextLine();
                    tokens = linea.split(",");
                    matriz[i] = new int[tokens.length];

                    for (int j = 0; j < matriz[i].length; j++) {
                        matriz[i][j] = Integer.parseInt(tokens[j]);
                    }

                }

            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return matriz;
    }
    
    // Método para hacer la inversa (caso 2)
    private static int[][] matrizInversa(int [][] matriz){
        
        int[][] inversa = new int[matriz.length][];
        int contador = 0;
        
        // Recorrer la i descendentemente y la j ascendentemente
        for (int i = matriz.length-1; i >= 0; i--) { // Con esto estamos empezando a leer desde la última fila
            inversa[contador] = new int[matriz[i].length]; // Con el contador es como si empezaras con i=0, es decir la recorrieras normal
            
            for (int j = 0; j < matriz[i].length; j++) {
                inversa[contador][j] = matriz[i][j];
            }
            contador++;
        }
        
        /* Otra forma ( no estoy seguro si funciona) 
        int[][] inversa = new int[matriz.length][];

        for (int i = 0; i < matriz.length; i++) {
            inversa[i] = matriz[i].clone(); // Copiamos la fila de la matriz original
        }

        Collections.reverse(Arrays.asList(inversa)); // Invertimos el orden de las filas

        return inversa;
        */
        
        /*
        Forma con constante
        int[][] inversa = new int[matriz.length][];
        
        
        // Recorrer la i descendentemente y la j ascendentemente
        for (int i = matriz.length-1; i >= 0; i--) { 
        
            final int CONS_INVERSA = matriz[i].length;
            inversa[CONS_INVERSA - i] = new int[matriz[i].length]; // Así al restar la inversa que es igual a la i, queda en 0
            
            for (int j = 0; j < matriz[i].length; j++) {
                inversa[CONS_INVERSA][j] = matriz[i][j];
            }
            contador++;
        }
        */
        
        return inversa;
    
    }    
    
    // Método para escribir en un fichero de texto la matriz invertida (caso 3)
    private static void escribirInversa(int[][] matriz, String ruta){
    
        String texto = "";

        try ( BufferedWriter flujo = new BufferedWriter(new FileWriter(ruta))) {

            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    texto = String.valueOf(matriz[i][j]) + ","; // String textoModificado = texto.substring(0, texto.length()-1); (si no fuera en bucles, así se podría quitar la coma)                    
                    flujo.write(texto);                  
                }
                flujo.newLine(); // Cada vez que termina una fila hacemos una nueva línea
            }           

            System.out.println("El archivo " + ruta + " ha sido creado satisfactoriamente");
        } catch (IOException ioe) {
            System.out.println("No se ha podido crear el fichero correctamente");
        }
        
    }
    
    
    // Método para ordenar cada fila de la matriz de mayor a menor (caso 4)
    private static int[][] mayorAMenor(int[][] matriz) {

        int[][] ordenada = new int[matriz.length][];

        for (int i = 0; i < matriz.length; i++) {
            ordenada[i] = new int[matriz[i].length];

            int[] filaTemporal = Arrays.copyOf(matriz[i], matriz[i].length); // Para no fastidiar la información hacemos la copia

            Arrays.sort(filaTemporal);
            for (int j = 0; j < filaTemporal.length; j++) {
                ordenada[i][j] = filaTemporal[filaTemporal.length - 1 - j];
            }
        }

        return ordenada;

    }
    
}
