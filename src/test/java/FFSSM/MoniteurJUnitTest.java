/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FFSSM;


import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Tina
 */
public class MoniteurJUnitTest {
    
    LocalDate dateNaissTina, dateNaissRemi, datePlongee1, datePlongee2, delivrance;
    Site castres, albi;
    Plongee plongee1, plongee2;
    Plongeur plongeur, participant;
    Moniteur moniteur1,moniteur2, presidentCastres, presidentAlbi;
    Club castresClub, albiClub;
    Licence licence1, licence2;
    Site site1;
   
    
    @BeforeEach
    public void setUp() {
     plongeur = new Plongeur("FRA000", "Nantenaina", "Tina", "6 Rue Eugène Léris", "0609401100", dateNaissTina, GroupeSanguin.APLUS, 1);
        moniteur1 = new Moniteur("FRA011", "Bastide", "Rémi", "Place Soult", "0607123456", dateNaissRemi, GroupeSanguin.BMOINS, 1, 22);
        participant = new Plongeur("FRA000", "Nante", "Tiana", "6 Rue Eugène Léris", "0607000000", dateNaissTina, GroupeSanguin.APLUS, 1);
        moniteur2 = new Moniteur("FRA012", "Mimi", "Tata", "Avenue Pompidou", "0610254936", dateNaissTina, GroupeSanguin.BPLUS, 2, 24);
        
        datePlongee1 = LocalDate.of(2020, 11, 25);
        datePlongee2 = LocalDate.of(2020,10, 8);
        castresClub = new Club(presidentCastres, "Castres Dive Club", "0578940382");
        albiClub = new Club(presidentAlbi, "Albi Dive Club", "0578940365");
        
        plongee1 = new Plongee(castres, moniteur1, datePlongee1, 3, 1);
        plongee2 = new Plongee (albi,moniteur2, datePlongee2, 4, 2 );
        
      
        delivrance = LocalDate.of(2019, 12, 27);
        licence1 = new Licence(plongeur,"1", delivrance, 5, castresClub);
        licence2 = new Licence(participant, "2", delivrance, 2, albiClub);
        
        site1 = new Site("eau douce","peu profonde");
                 
    }
    
    @Test
    public void testAjouteParticipant() {
        // une plongee a forcément un chef de palanquee
        assertEquals(1,plongee1.getPlongeurs().size());
        
        plongee1.ajouteParticipant(plongeur);
        // On a ajouté un participant
        assertEquals(1 + 1,plongee1.getPlongeurs().size());
        
        // On ajoute le même participant
        plongee1.ajouteParticipant(plongeur);
        // On vérifie que le participant n'est pas en double
        assertEquals(1 + 1,plongee1.getPlongeurs().size());
    }
    
    @Test
    // On teste que cette méthode nous renvoie bien les plongées non conformes
    public void testPlongeesNonConformes() {      
       plongee1.ajouteParticipant(participant);
       castresClub.organisePlongee(plongee1);
       moniteur1.ajouteLicence("001", delivrance, albiClub);
     
    }
    
    @Test
     public void testAjoutPlongee(){
         plongee1.ajouteParticipant(participant);
         castresClub.organisePlongee(plongee1);
         assertTrue(castresClub.getPlongees().contains(plongee1));
     }
     
     
     @Test
     public void testAjoutEmploi(){
         assertTrue(moniteur1.emplois().isEmpty());
         moniteur1.nouvelleEmbauche(albiClub, LocalDate.now());
         assertTrue(!moniteur1.emplois().isEmpty());
     }
     
     @Test
     public void testEmployeurActuel(){
         moniteur1.nouvelleEmbauche(albiClub, datePlongee1);
         assertEquals(albiClub, moniteur1.employeurActuel().get(), "Pas le même employeur");
     }

     @Test
     public void testEmployeur(){
         
     moniteur1.nouvelleEmbauche(castresClub, datePlongee1);
     Optional<Club> employeur = Optional.of(moniteur1.emplois().get(moniteur1.emplois().size()-1).getEmployeur());
     assertEquals(Optional.ofNullable(moniteur1.emplois().get(moniteur1.emplois().size()-1).getEmployeur()), employeur);
     }
   
     
     @Test
     public void testFin(){
         moniteur1.nouvelleEmbauche(castresClub, datePlongee1);
         moniteur1.emplois().get(moniteur1.emplois().size()-1).terminer(LocalDate.now());
         // l'emploie est finie
         assertTrue(moniteur1.emplois().get(moniteur1.emplois().size()-1).estTerminee());
     }
     
     @Test
    public void testEstValide() {
        
        licence1 = new Licence(moniteur1, "0003", delivrance, 2, castresClub);
        // licence est valide au moment de la délivrance
        assertTrue(licence1.estValide(delivrance.plusMonths(0)));
    }
}
    
