import javax.swing.JOptionPane;
import javax.swing.JTextArea;
/**
 * aqui se ejecutan los metodos de la clase MatrizFre
 * author (Dilian) 
 * version 1
 */
public class Controlador
{

    public static void main(String[] args) {
        MatrizFre prueba = new MatrizFre();
       
        prueba.cargar();
       
        JOptionPane.showMessageDialog(null,"El rango es:\n"+prueba.traer());
        
        prueba.ordenar();
        JOptionPane.showMessageDialog(null,"El rango es:\n"+prueba.traer());
        //ordena lo datos leidos
        JOptionPane.showMessageDialog(null,"El rango es:\n"+prueba.rango());
        JOptionPane.showMessageDialog(null,"El  numero de categorias por usar:\n"+prueba.numcategorias());
        JOptionPane.showMessageDialog(null,"El  ancho para cada clase es de:\n"+prueba.anchodeclase());
        prueba.matrizfre1();
        JOptionPane.showMessageDialog(null, new JTextArea("de\thas\tfrecAbs fi\t frecRelav hi\tfrecaAcum Fi\tfrecrelAcum Hi\n"+prueba.mostrarmat()));
        
    }
}
