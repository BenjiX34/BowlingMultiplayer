package bowling;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Benjamin Bardy
 */
public class MultiplayerBowlingGameTest {
    private MultiplayerBowlingGame partie;
    
    @Before
    public void setUp() {
         partie = new MultiplayerBowlingGame();
    }
    
    @After
    public void tearDown() {
        System.out.println("--------------------");
    }

    @Test
    public void initialisationTest() throws Exception{
        String[] joueurs = {"a", "b", "c"};
        System.out.println(partie.startNewGame(joueurs));
    }
    
    @Test (expected=Exception.class)
    public void deuxJoueursDeMemeNom() throws Exception{
        String[] joueurs = {"a", "a", "c"};
        System.out.println(partie.startNewGame(joueurs));
    }
    
    @Test
    public void partieSolo() throws Exception{
        String[] joueurs = {"a"};
        System.out.println(partie.startNewGame(joueurs));
        rollMany(20,3);
        printScores(joueurs);
    }
    
    @Test (expected=Exception.class)
    public void partieTropLongue() throws Exception{
        String[] joueurs = {"a"};
        System.out.println(partie.startNewGame(joueurs));
        rollMany(21,3);
        printScores(joueurs);
    }  
       
    @Test
    public void partieDuo() throws Exception{
        String[] joueurs = {"a", "b"};
        System.out.println(partie.startNewGame(joueurs));
        rollMany(40,4);
        printScores(joueurs);
    }
    
    @Test
    public void partieMulti() throws Exception{
        String[] joueurs = {"a", "b", "c"};
        System.out.println(partie.startNewGame(joueurs));
        rollMany(60,4);
        printScores(joueurs);
    }
    
   
    
    // Quelques methodes utilitaires pour faciliter l'écriture des tests
	private void rollMany(int n, int pins) throws Exception {
		for (int i = 0; i < n; i++) {
                    System.out.println(partie.lancer(pins));
		}
	}

	private void rollSpare() throws Exception {
		System.out.println(partie.lancer(7));
		System.out.println(partie.lancer(3));
	}

	private void rollStrike() throws Exception {
		System.out.println(partie.lancer(10));
	}
        
        private void printScores(String[] joueurs) throws Exception{
            for(String joueur: joueurs){
                System.out.println(joueur+": "+partie.scoreFor(joueur));
            }
        }
}
