/**
 * @(#) Plongee.java
 */
package FFSSM;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Plongee {

	public Site lieu;

	public Moniteur chefDePalanquee;

	public LocalDate date;

	public int profondeur;

	public int duree;
        
        private Set<Plongeur> plongeurs;
        
       

	public Plongee(Site lieu, Moniteur chefDePalanquee, LocalDate date, int profondeur, int duree) {
        this.plongeurs = new HashSet<>();
		this.lieu = lieu;
		this.chefDePalanquee = chefDePalanquee;
		this.date = date;
		this.profondeur = profondeur;
		this.duree = duree;
                ajouteParticipant(chefDePalanquee);
                
	}

	public void ajouteParticipant(Plongeur participant) {
		// méthode implémentée
                if(!plongeurs.contains(participant)){
                plongeurs.add(participant);
	}
        }

	public Set<Plongeur> getPlongeurs() {
             return plongeurs;
        }
        
        public LocalDate getDate() {
		return date;
	}  
        
         
        

	/**
	 * Détermine si la plongée est conforme. 
	 * Une plongée est conforme si tous les plongeurs de la palanquée ont une
	 * licence valide à la date de la plongée
	 * @return vrai si la plongée est conforme
	 */
	public boolean estConforme(){
		// méthode implémentée
                boolean conforme = true;
		for (Plongeur plongeur : plongeurs)
                {
                    if (!plongeur.maLicence.get(plongeur.maLicence.size()-1).estValide(this.getDate())){
                        conforme = false; 
                }  
        }
                return conforme;
}
}
