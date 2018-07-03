/**
 * aqui se realizan los metodos para construir la tabla de frecuencias o matriz de frecuencias
 * author (Dilian) 
 * version 1
 */
import java.awt.*;
import java.lang.Math;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
public class MatrizFre
{
    String str;
    String tablafre[][];
    double datos[];
    double n[]=new double[4];// se le asinga un tamaño temporal cambia cuando se lee el Archivo
    String[]campos=new String[7];// se le asinga un tamaño temporal cambia cuando se lee el Archivo

    MatrizFre(){

    }

    /*
     * este metodo no recibe nada ,ni retorna nada, se encarga de leer el archivo  CSV los mete en un vector
     */
    public void cargar (){ //carga datos desde archivo a memoria (si son correctos)
        JFileChooser fileChooser = new JFileChooser(); //Ventana para el manejo de directorios
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(null); //intenta abrir diálogo para manejo de dir
        if (result == JFileChooser.CANCEL_OPTION) //usuario da click sobre la X de la ventana
            return; //se obliga a terminar acá
        File fileName = fileChooser.getSelectedFile(); //tome archivo seleccionado
        if (fileName == null || fileName.getName().equals(""))
            JOptionPane.showMessageDialog(null, "ERROR", "Nombre de archivo es inválido",
                JOptionPane.ERROR_MESSAGE);
        else {

            try {
                BufferedReader in = new BufferedReader(
                        new FileReader(fileName));
                String str ;
               
                while((str=in.readLine())!=null){
                   
                    
                    campos =str.split(";");//guarda los datos  que estaban separados por coma

                    n= new double[campos.length];
                    for(int i=0; i<campos.length;i++){
                        n[i]=Double.parseDouble(campos[i]);// mete los valores del vector de String a uno de enteros

                    }

                }
                in.close();

            }
            catch (IOException e) {
                System.out.println("Excepcion");

            }

        }

    }

    /*
     * este metodo no recibe nada y retorna en una String el vector de datos leidos
     */
    public String traer(){
        String resul ="";
        for (int i=0; i<n.length; i++){

            resul=resul+n[i]+" ";

        }

        return resul;
    }

    /*
     * no recibe ni retorna nada solo ordena el vector de datos para mayor facilidad de sacar el rango
     */
    public void ordenar(){
        double temp=0;

        for(int c=0;c<n.length-1;c++){
            for(int h=c+1;h<n.length;h++){
                if(n[c]>n[h]){
                    temp=n[c];
                    n[c]=n[h];
                    n[h]=temp;
                } 
            }
        }

    }

    /*
     * calcula el rango del vector de enteros que contiene los datos leidos es decir al ultimo elemento le resta el primero
     */
    public double rango(){
        double rango=0;
        rango=n[n.length-1]-n[0];
        return rango;

    }

    /*
     * calcula el numero de categorias de la matriz dado formula del enunciado, no recibe nada y retorna el numero de clases
     */
    public double numcategorias(){
        double x=Math.round(1+3.322*(Math.log(n.length)/Math.log(10)));
        double clases=x;
        return clases;
    }

    /*
     * no recibe nada y retorna el ancho de clase que debe tener cada categoria cada cual debe de ser del mismo tamaño
     */
    public double anchodeclase(){
        double ran=rango();
        double numca=numcategorias();
        double anchoclase=ran/numca;
        return anchoclase;
    }

    /*
     * metodo usado para redondear 3 decimales los limites de cada clase 
     * se redondea para no tener tantos decimales
     */
    public double redondeo(double i){//maneja que no aparezcan tantos decimales ya que trabajamos con doubles
        double lim = i * Math.pow(10, 3);
        lim = Math.round(lim);
        lim = lim/Math.pow(10, 3);
        return lim;
    }

    /*
     * metodo usado para redondear cuantro decimales la frecuencia relativa acumulada y la frecuencia acumulada
     * se redondea para no tener tantos decimales
     */
    public double redondeo1(double i){//maneja que no sean demasiados decimales
        double lim = i * Math.pow(10, 4);
        lim = Math.round(lim);
        lim = lim/Math.pow(10, 4);
        return lim;
    }

    /*
     * no recibe nada y con los datos obtenidos calcula la matriz de frecuencias 
     */
    public void matrizfre1(){
        double c=0;
        double limder;
        double anchoclase=anchodeclase();
        double j=anchoclase;
        int cont=2;
        double limizq;
        double numcla=numcategorias();
        int filas=(int)numcla;
        int fr=0;
        double i=n[0];
        int freRel=0;
        double freRelAcum=0;
        int frecAbso=0;
        double num=n.length;
        double temp;
        double freAcum=0;
        tablafre=new String[filas][6];
        while(i<n[n.length-1] && fr<numcla){// aqui se va llenando la matriz con los datos antes calculados

            limder=j+n[0];     
            limizq = i;
            limizq=redondeo(limizq);// redondea los limites
            limder=redondeo(limder);
            tablafre[fr][0]=""+limizq+"\t";// mete en la matriz el limiteizquierdo del intervalo
            tablafre[fr][1]=""+limder+"\t";// mete en la matriz el limitederecho del intervalo
            j= anchoclase*cont;
            cont++;
            i=i+anchoclase;
            if(limder==n[n.length-1]){//caso para que incluya el ultimo valor
                limder=j+n[0]+0.01;
            }
            for(int f=0;f<n.length;f++){
                if(n[f]>=limizq && n[f]<limder){//si se encuentra entre los limites que lo contabilice 
                    frecAbso++;
                    freRel++;
                    temp=freRel;

                }
                freAcum=frecAbso/num;
                freRelAcum= freAcum/num;
                tablafre[fr][3]="\t"+freRel+"\t";// mete en la columna 3 la frecuencia relativa
                tablafre[fr][2]=""+frecAbso;//  mete en la columna 2 la frecuencia absoluta
                freAcum=redondeo1(freAcum);
                tablafre[fr][4]=""+freAcum+"\t";//mete en la columna 4 la frecuencia  acumulada
            }
            frecAbso=0;
            freRelAcum=redondeo1(freRelAcum);
            tablafre[fr][5]=""+freRelAcum+"\t";//mete en la columna 5 la frecuencia relativa acumulada

            fr++;//contador para cambiar de fila
        }
    } 

    /*
     * no recibe nada y retorna una String con la matriz de frecuencias
     */
    public String mostrarmat(){
        String result="";
        for(int filas=0; filas<numcategorias(); filas++){
            for(int column=0; column<6;column++){
                result=result+tablafre[filas][column];//mete los valores de la matriz en una string
            }
            result=result+"\n";
        }  
        return result;
    }

}