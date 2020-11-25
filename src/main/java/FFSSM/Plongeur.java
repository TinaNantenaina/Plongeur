package FFSSM;
import java.time.LocalDate;
import java.util.ArrayList;

public class Plongeur extends Personne {
    
    public int niveau;
    public ArrayList<Licence> licence;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, int niveau) {
       super(numeroINSEE,nom,prenom,adresse,telephone,naissance);
       this.niveau = niveau;
        
    }   
    public void ajouteLicence(String numero, LocalDate delivrance, Club club){
        this.licence.add(new Licence(this,numero,delivrance, niveau, club));
    }
    
    public Licence getLastLicence(){
        return this.licence.get(this.licence.size()-1);
    }
}
