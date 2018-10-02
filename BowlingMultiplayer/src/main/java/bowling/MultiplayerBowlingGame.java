package bowling;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Classe qui affiche une partie de bowling à plusieurs tour par tour,
 * et renvoie le score de chaque joueur
 * @author Benjamin Bardy
 */
public class MultiplayerBowlingGame implements MultiPlayerGame{
    private final String SEPARATEUR="-------";
    private HashMap<String, SinglePlayerGame> partie;
    private HashMap<Integer, String> scoresFinaux;
    private String[] joueurs;
    private int n_joueur; 
    
    /**
     * Méthode à exécuter au début d'une nouvelle partie
     * 
     * @param playerNames La liste de noms des joueurs
     * @return L'état du prochain tour
     * @throws Exception 
     */
    @Override
    public String startNewGame(String[] playerNames) throws Exception {
        if(playerNames.length == 0){
           throw new NullPointerException("Le nombre de joueurs est nul");
        }
        
        partie = new HashMap<>();
        n_joueur = 0;
        joueurs = playerNames;
        scoresFinaux = new HashMap<>();
                
        for(String player: playerNames){
            if(partie.containsKey(player)){
                throw new Exception("Un joueur du même nom est déjà présent dans la partie");
            }else{
                partie.put(player, new SinglePlayerGame());
            }
        }
        
        return toString();
    }

    /**
     * Méthode à être appelée à chaque lancer de boule
     * 
     * @param nombreDeQuillesAbattues le nombre de quilles abattues lors du lancer
     * @return L'état du prochain lancer
     * @throws Exception 
     */
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
                    n_joueur = (n_joueur + 1)%joueurs.length;
                    
                    scoresFinaux.put(joueurActuel.score(), nomJoueurActuel);
                                        
                    return SEPARATEUR+"\n"+
                            "Le joueur \""+nomJoueurActuel+"\" a terminé"+"\n"+
                           SEPARATEUR+"\n"+
                            toString();
                }
                
                n_joueur = (n_joueur + 1)%joueurs.length;
                System.out.println(SEPARATEUR);
            }
            
            return toString();
            
        }catch(UnsupportedOperationException e){
            
            scoresFinaux();
            
            throw new Exception("La partie est finie");
                        
        }
    }
    
    /**
     * @param playerName le nom du joueur dont on veut connaître le score
     * @return le score du joueur
     * @throws Exception 
     */
    @Override
    public int scoreFor(String playerName) throws Exception {
        return partie.get(playerName).score();
    }
      
    /**
     * Affiche le score final et le classement à la fin de la partie
     */
    private void scoresFinaux(){
        System.out.println("-----SCORE FINAUX-----");
        Object[] scores = scoresFinaux.keySet().toArray();
        Arrays.sort(scores, Collections.reverseOrder());
        for(int place=0; place<scores.length; place++){
            System.out.println(String.format("%d%s : %s (%s pts)",
                    place+1, (place+1 == 1)? "er": (place+1 == 2)? "nd" : "eme",  
                    scoresFinaux.get(scores[place]), scores[place]));
        }
        
        
        
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
