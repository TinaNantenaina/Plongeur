package FFSSM;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plongeur extends Personne {
    
    public int niveau;
    public List<Licence> licence = new ArrayList<>();
    public GroupeSanguin groupeSanguin;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin, int niveau) {
       super(numeroINSEE,nom,prenom,adresse,telephone,naissance);
       this.niveau = niveau;
       this.groupeSanguin = groupeSanguin;
        
    }   
    public void ajouteLicence(String numero, LocalDate delivrance, Club club){
        this.licence.add(new Licence(this,numero,delivrance, niveau, club));
    }
    
    public Licence getLastLicence(LocalDate date){
        return this.licence.get(this.licence.size()-1);
    }
}
