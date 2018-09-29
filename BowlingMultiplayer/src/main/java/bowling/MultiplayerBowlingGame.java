/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import bowling.Frame;
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.time.Clock;
import java.util.HashMap;

/**
 *
 * @author pedago
 */
public class MultiplayerBowlingGame implements MultiPlayerGame{
    private HashMap<String, SinglePlayerGame> partie;
    private String[] joueurs;
    private int n_joueur; 
    
    
    @Override
    public String startNewGame(String[] playerNames) throws Exception {
        if(playerNames.length == 0){
           throw new NullPointerException("Le nombre de joueurs est nul");
        }
        
        partie = new HashMap<>();
        n_joueur = 0;
        
        joueurs = playerNames;
                
        for(String player: playerNames){
            if(partie.containsKey(player)){
                throw new Exception("Un joueur du même nom est déjà présent dans la partie");
            }else{
                partie.put(player, new SinglePlayerGame());
            }
        }
        
        return toString();
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        SinglePlayerGame joueurActuel = partie.get(joueurs[n_joueur]);
        Frame tourActuel;
        
        try{
            joueurActuel.lancer(nombreDeQuillesAbattues);

            tourActuel = joueurActuel.getCurrentFrame();

            if(tourActuel.isFinished()){
                n_joueur = (n_joueur + 1)%joueurs.length;
                System.out.println("-----");
            }

            return toString();
        }catch(UnsupportedOperationException e){
            return "Le joueur "+joueurs[n_joueur]+" a terminé";
        }
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        return partie.get(playerName).score();
    }
    
  
    @Override
    public String toString(){
        String joueurActuel = joueurs[n_joueur];
        Frame tourActuel = partie.get(joueurActuel).getCurrentFrame();
        return String.format("Prochain tir : joueur %s, tour n° %s, boule n° %s", joueurActuel, tourActuel.getFrameNumber(), tourActuel.getBallsThrown());
    }
    
    
}
