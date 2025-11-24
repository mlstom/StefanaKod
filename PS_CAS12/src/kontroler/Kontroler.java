/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import baza.DBBroker;
import java.util.ArrayList;
import java.util.List;
import model.Autor;
import model.Knjiga;
import model.Zanr;



/**
 *
 * @author Cofiliks
 */
public class Kontroler {
    
    private DBBroker dbb;
    
    
    
    
    private List<Knjiga> listaKnjiga;
    private List<Autor> listaAutora;
    private static Kontroler instance;
    public static Kontroler getInstance(){
        if(instance==null){
            instance=new Kontroler();
        }
        return instance;
    }
    
    //voli da pirta da li je moguce da se napravi privatni kontorler 
    //kad zelimo da pristupimo kontorler idemo preko getInstance funkcije
    private Kontroler() {
        dbb=new DBBroker();
        
        
        
        
       /* Autor a1=new Autor("Ivo", "Andric", 1992,"Biografija autora Ive Andriaca bla bla");
        Autor a2=new Autor("Danilo", "Kis", 1935,"Biografija Danila Kisa bla bla");
        Autor a3=new Autor("Mesa","Selimovic",1910,"Mesa Selimovic je rodjen...");
        
        Knjiga k1=new Knjiga("Na Drini Cuprija",a1,"1234567890",1945,Zanr.DETEKTIVSKI);
        Knjiga k2=new Knjiga("Basta,pepeo",a2,"0987654321",1982,Zanr.ISTORIJSKI);
        Knjiga k3=new Knjiga("Tvrdjava",a3,"1122334455",1970,Zanr.NAUCNA_FANTASTIKA);
        
        listaKnjiga=new ArrayList<>();
        listaAutora=new ArrayList<>();
        
        listaKnjiga.add(k1);
        listaKnjiga.add(k2);
        listaKnjiga.add(k3);
        
        
        listaAutora.add(a1);
        listaAutora.add(a2);
        listaAutora.add(a3);*/
        
        
        
    }

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public List<Autor> getListaAutora() {
        return listaAutora;
    }

    public void setListaAutora(List<Autor> listaAutora) {
        this.listaAutora = listaAutora;
    }

    public void obrisiKnjigu(int id,int selektovaniRed) {
      dbb.obrisiKnjigu(id);
       // listaKnjiga.remove(selektovaniRed);
    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
        dbb.dodajKnjigu(novaKnjiga);
        //listaKnjiga.add(novaKnjiga);
        //System.out.println("Knjiga je dodata");
       // System.out.println(listaKnjiga);
    }

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
       return dbb.ucitajListuKnjigaIzBaze();
    
   }

    public List<Autor> ucitajListuAutoraIzBaze() {

    return dbb.ucitajListuAutoraIzBaze();
    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        dbb.azurirajKnjigu(knjigaZaIzmenu);
    }
    
    
}
