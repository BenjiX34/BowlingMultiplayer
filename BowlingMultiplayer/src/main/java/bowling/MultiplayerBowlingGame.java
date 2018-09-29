/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowling;

import java.util.HashMap;

/**
 *
 * @author pedago
 */
public class MultiplayerBowlingGame implements MultiPlayerGame{
    private HashMap<String, SinglePlayerGame> partie;
    private String[] joueurs;
    private int n_joueur, joueursAyantFini; 
    
    
    @Override
    public String startNewGame(String[] playerNames) throws Exception {
        if(playerNames.length == 0){
           throw new NullPointerException("Le nombre de joueurs est nul");
        }
        
        partie = new HashMap<>();
        n_joueur = 0;
        joueurs = playerNames;
        joueursAyantFini = 0;
                
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
        String nomJoueurActuel = joueurs[n_joueur];
        SinglePlayerGame joueurActuel = partie.get(nomJoueurActuel);
        Frame tourActuel;
        
        try{
            
            joueurActuel.lancer(nombreDeQuillesAbattues);

            tourActuel = joueurActuel.getCurrentFrame();

            if(tourActuel.isFinished()){
                if(tourActuel instanceof TenthFrame){                   
                    joueursAyantFini++;
                    n_joueur = (n_joueur + 1)%joueurs.length;
                    System.out.println("---------");
                    
                    return "Le joueur \""+nomJoueurActuel+"\" a terminé\n"+toString();
                }
                
                n_joueur = (n_joueur + 1)%joueurs.length;
                System.out.println("-----");
            }
            
            return toString();
            
        }catch(UnsupportedOperationException e){
            
            gameOver();
            
            throw new Exception("La partie est finie");
                        
        }
    }
    
    
    @Override
    public int scoreFor(String playerName) throws Exception {
        return partie.get(playerName).score();
    }
      
    
    private void gameOver() throws Exception{
        
    }
    
    @Override
    public String toString(){
        String infoProchainTir="";
        String joueurActuel = joueurs[n_joueur];
        Frame tourActuel = partie.get(joueurActuel).getCurrentFrame();
        
        if(tourActuel.isFinished() && !(tourActuel instanceof TenthFrame)){
            tourActuel = tourActuel.next();
        }
        
        boolean dernierLancer = tourActuel.isFinished() && tourActuel instanceof TenthFrame;
        
        if(!dernierLancer){
            infoProchainTir = "Prochain tir : joueur "+joueurActuel
                            +", tour n° "+tourActuel.getFrameNumber()
                            +", boule n° "+(tourActuel.getBallsThrown()+1);
        }
        
        return infoProchainTir;
        
        
    }
    
    
}
