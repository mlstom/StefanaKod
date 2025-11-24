/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;


import baza.Konekcija;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.sql.ResultSet;
import model.Autor;
import model.Zanr;
/**
 *
 * @author Cofiliks
 */
public class DBBroker {

    public List<Knjiga> ucitajListuKnjigaIzBaze() {
        List<Knjiga> lista=new ArrayList<>();
        try{
        String upit="SELECT * FROM KNJIGA K JOIN AUTOR A ON k.autorId=a.id";
            //NAUCI NAPAMET OVAJ DEO KODA
           Statement st;
            st = Konekcija.getInstance().getConnection().createStatement();
           ResultSet rs= st.executeQuery(upit);
           
           
           while(rs.next()){
               int id=rs.getInt("k.id");
               String naslov=rs.getString("k.naslov");
               int godinaIzdanja=rs.getInt("k.godinaIzdanja");
               String isbn=rs.getString("k.ISBN");
               String zanr=rs.getString("k.zanr");
               Zanr z=Zanr.valueOf(rs.getString("zanr"));//pretvaranje u enum
               
               int idA=rs.getInt("a.id");
               String ime=rs.getString("a.ime");
               String prezime=rs.getString("a.prezime");
               String biografija=rs.getString("a.biografija");
               int godinaRodjenja=rs.getInt("a.godinaRodjenja");
               
               Autor a=new Autor(idA, ime, prezime, godinaRodjenja, biografija);
               Knjiga k =new Knjiga(id,naslov, a, isbn, godinaIzdanja, Zanr.ROMAN);
               lista.add(k);
           
           }
        } catch(SQLException ex){
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        return lista;
            
    }
    //ovde kucas sve sql upite 
    //ideja je da forma kaze kontroleru sta mu je potrebno pa on sa dbb pa on uradi pa vreti kontroleru pa t=on formi 
    
    

     public List<Autor> ucitajListuAutoraIzBaze(){
      List<Autor> lista=new ArrayList<>();
        try{
        String upit="SELECT * FROM AUTOR a";
            //NAUCI NAPAMET OVAJ DEO KODA
           Statement st;
            st = Konekcija.getInstance().getConnection().createStatement();
           ResultSet rs= st.executeQuery(upit);
           
           
           while(rs.next()){
               int idA=rs.getInt("a.id");
               String ime=rs.getString("a.ime");
               String prezime=rs.getString("a.prezime");
               String biografija=rs.getString("a.biografija");
               int godinaRodjenja=rs.getInt("a.godinaRodjenja");
               
               Autor a=new Autor(idA, ime, prezime, godinaRodjenja, biografija);
               lista.add(a);
           
           }
        } catch(SQLException ex){
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE,null,ex);
        }
        
        
        return lista;
            
     
     }

    public void obrisiKnjigu(int id) {
        
        
        try {
            String upit="DELETE FROM knjiga WHERE id=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, id);
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

    }

    public void dodajKnjigu(Knjiga novaKnjiga) {
        try {
            String upit="INSERT INTO knjiga (id,naslov,autorId,ISBN,godinaIzdanja,zanr)"+" VALUES(?,?,?,?,?,?)";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1,novaKnjiga.getId());
            ps.setString(2, novaKnjiga.getNaslov());
            ps.setInt(3, novaKnjiga.getAutor().getId());
            ps.setString(4, novaKnjiga.getISBN());
            ps.setInt(5, novaKnjiga.getGodinaIzdanja());
            ps.setString(6, String.valueOf(novaKnjiga.getZanr()));
            
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            System.out.println("Uspesno dodato");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    public void azurirajKnjigu(Knjiga knjigaZaIzmenu) {
        try {
            String upit="UPDATE KNJIGA SET naslov=?,autorId=?,godinaIzdanja=?,"+"zanr=? where id=?";
            PreparedStatement ps=Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setString(1, knjigaZaIzmenu.getNaslov());
            ps.setInt(2, knjigaZaIzmenu.getAutor().getId());
            ps.setInt(3, knjigaZaIzmenu.getGodinaIzdanja());
            ps.setString(4, String.valueOf(knjigaZaIzmenu.getZanr()));
            ps.setInt(5, knjigaZaIzmenu.getId());
            
            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            System.out.println("Uspesno azurirano");
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}