package FFSSM;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class Plongeur extends Personne {
    
    public int niveau;
    public List<Licence> maLicence = new ArrayList<>();
    public GroupeSanguin groupeSanguin;

    public Plongeur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance, GroupeSanguin groupeSanguin, int niveau) {
       super(numeroINSEE,nom,prenom,adresse,telephone,naissance);
       this.niveau = niveau;
       this.groupeSanguin = groupeSanguin;
        
    }   
    public void ajouteLicence(String numero, LocalDate delivrance, Club club){
        for (Licence licence : maLicence){
            if(licence.estValide(LocalDate.now())){
                licence.setValide(false);
            }
        }
        maLicence.add(new Licence(this,numero,delivrance, niveau, club));
    }
    
    public Licence getLastLicence(LocalDate date){
        return this.maLicence.get(this.maLicence.size()-1);
    }
    
}
