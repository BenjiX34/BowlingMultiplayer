/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import bowling.MultiplayerBowlingGame;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedago
 */
public class MultiplayerBowlingGameTest {
    private MultiplayerBowlingGame partie;
    
    @Before
    public void setUp() {
         partie = new MultiplayerBowlingGame();
    }
    
    @After
    public void tearDown() {
        System.out.println("----------------");
    }

    //@Test
    public void initialisationTest() throws Exception{
        System.out.println(partie.startNewGame(new String[] {"a", "b", "c"}));
        System.out.println(partie.lancer(10));
    }
    
    //@Test (expected=Exception.class)
    public void deuxJoueursDeMemeNom() throws Exception{
        System.out.println(partie.startNewGame(new String[] {"a", "a", "c"}));
    }
    
    //@Test
    public void partieSolo() throws Exception{
        System.out.println(partie.startNewGame(new String[] {"a"}));
        rollMany(20,3);
        System.out.println(partie.scoreFor("a"));
    }
    
    @Test
    public void partieMulti() throws Exception{
        System.out.println(partie.startNewGame(new String[] {"a", "b"}));
        rollMany(36,4);
        
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
}
