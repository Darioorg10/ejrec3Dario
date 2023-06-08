/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                      
                           int[][]matriz = leerFichero1("./matrizP(1).txt");
                           imprimirMatriz(matriz);                      
                      
                }
                  case 2 -> {
                }
                  case 3 -> {
                }
                  case 4 -> {
                }
                  case 5 -> {
                }
                default -> throw new AssertionError();
            }
            
            
            
            
            
            
        } while (respuesta != 5);
        
        
        
        
        
    }
    
    // Métodos
    
    // Método para leer el fichero
    private static int[][] leerFichero1(String ruta) {        
        int[][] matriz = new int[][];
        String linea = "";
        String[] tokens;

        try ( Scanner flujo = new Scanner(new FileReader(ruta))) {
            
            while (flujo.hasNext()) {                
                linea = flujo.nextLine();
                tokens = linea.split(",");                                  
                
                for (int i = 0; i < matriz.length; i++) {                    
                    matriz[i] = linea.length();
                    for (int j = 0; j < matriz[i].length; j++) {
                        
                    }
                }
                                                
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return matriz;
    }
    
    // Método para imprimir una matriz
    private static void imprimirMatriz(int[][] matriz){
    
         for (int i = 0; i < matriz.length; i++) {
             for (int j = 0; j < matriz[i].length; j++) {
                 System.out.println(matriz[i][j] + " ");
             }
             System.out.println("");
        }
    
    }
    
}
