/**
 * @(#) Moniteur.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Moniteur extends Plongeur {

    public int numeroDiplome;
    private List<Embauche> monEmploie = new ArrayList<>();

    /**
     *
     * @param numeroINSEE
     * @param nom
     * @param prenom
     * @param adresse
     * @param telephone
     * @param naissance
     * @param niveau
     * @param numeroDiplome
     */
    public Moniteur(String numeroINSEE, String nom, String prenom, String adresse, String telephone, LocalDate naissance,GroupeSanguin groupeSanguin, int niveau, int numeroDiplome) {
        super(numeroINSEE, nom, prenom, adresse, telephone, naissance, groupeSanguin, niveau);
        this.numeroDiplome = numeroDiplome;
    }

    /**
     * Si ce moniteur n'a pas d'embauche, ou si sa dernière embauche est terminée,
     * ce moniteur n'a pas d'employeur.
     * @return l'employeur actuel de ce moniteur sous la forme d'un Optional
     */
    public Optional<Club> employeurActuel() {
         // méthode implémenté
         Optional<Club> opt;
       if (!monEmploie.isEmpty() && !monEmploie.get(monEmploie.size() - 1).estTerminee()) {
            Club c = monEmploie.get(monEmploie.size() - 1).getEmployeur();
            opt = Optional.ofNullable(c);
        }
        else {
            Club c = null;
            opt = Optional.ofNullable(c);
       }
        return opt;
    }
    
    /**
     * Enregistrer une nouvelle embauche pour cet employeur
     * @param employeur le club employeur
     * @param debutNouvelle la date de début de l'embauche
     */
    public void nouvelleEmbauche(Club employeur, LocalDate debutNouvelle) {   
         // méthode implémentée
          Embauche e = new Embauche(debutNouvelle, this, employeur);
        terminerEmbauche(debutNouvelle);
        monEmploie.add(e);	    
    }
    
       public void terminerEmbauche(LocalDate dateFin) {
           // en cas d'emploie en cours
        if (!monEmploie.isEmpty() && !monEmploie.get(monEmploie.size() - 1).estTerminee())
            monEmploie.get(monEmploie.size() - 1).terminer(dateFin);
       }

    public List<Embauche> emplois() {
         // méthode implémentée
        return monEmploie;
    }

}
