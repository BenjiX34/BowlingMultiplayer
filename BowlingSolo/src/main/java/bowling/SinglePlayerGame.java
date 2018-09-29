package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class SinglePlayerGame {

	private final Frame firstFrame; // Le premier tour
	private Frame currentFrame; // Le tour en cours

	/**
	 * Constructeur
	 */
	public SinglePlayerGame() {
		// On initialise les 10 tours, en commençant par le dernier
		// Chaque tour (sauf le dernier) est initialisé avec son tour suivant
		// On construit ainsi une "liste chainée" de tours
		currentFrame = new TenthFrame();
		for (int frameNumber = 9; frameNumber > 0; frameNumber--) {
			Frame f = new Frame(frameNumber, currentFrame);
			currentFrame = f;
		}
		firstFrame = currentFrame;
	}

	/**
	 * Cette méthode doit être appelée à chaque lancé de boule
	 *
	 * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de
	 * ce lancé
	 */
	public void lancer(int nombreDeQuillesAbattues) {
		if (currentFrame.isFinished()) {
			currentFrame = currentFrame.next();
		}
		if (currentFrame != null)
			currentFrame.roll(nombreDeQuillesAbattues);
		else
			throw new UnsupportedOperationException("Le jeu est fini");

	}

	/**
	 * Cette méthode donne le score du joueur
	 *
	 * @return Le score du joueur
	 */
	public int score() {
		return firstFrame.score();
	}

	/**
	 * 
	 * @return la frame courante pour ce jeu 
	 */
	public Frame getCurrentFrame() {
		return currentFrame;
	}
	
	
}
