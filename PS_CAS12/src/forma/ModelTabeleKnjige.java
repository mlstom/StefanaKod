/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forma;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

/**
 *
 * @author Cofiliks
 */
public class ModelTabeleKnjige extends AbstractTableModel {

    private List<Knjiga> listaKnjiga;
    private final String[] kolone={"ID","Naslov","Autor","ISBN","Godina izdanja"};

    public List<Knjiga> getListaKnjiga() {
        return listaKnjiga;
    }

    public void setListaKnjiga(List<Knjiga> listaKnjiga) {
        this.listaKnjiga = listaKnjiga;
    }

    public ModelTabeleKnjige(List<Knjiga> listaKnjiga){
        this.listaKnjiga=listaKnjiga;
    }
    
    
    
    //koliko cemo redova imati 
    @Override
    public int getRowCount() {
        return listaKnjiga.size();
    }

    
    //vracamo duzinu niza kolone
    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    
    //vraca u odredjenoj celiji odredjenu vrednost
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga knjiga=listaKnjiga.get(rowIndex);
        switch (columnIndex){
            case 0:
                return knjiga.getId();
            case 1:
                return knjiga.getNaslov();
            case 2:
                return knjiga.getAutor().getIme()+" "+knjiga.getAutor().getPrezime();
            case 3:
                return knjiga.getISBN();
            case 4:
                return knjiga.getGodinaIzdanja();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];

    }

    void osveziPodatke() {
        fireTableDataChanged();
    }
    
    
    
    
    
    
    
}
