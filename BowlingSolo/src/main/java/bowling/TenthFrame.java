package bowling;

/**
 * Le 10° tour, différent de tous les autres tours : Il peut avoir trois lancés
 * Il n'a pas de lancé suivant.
 * 
 * La classe n'est pas publique, sa visibilité est limitée au package
 */
class TenthFrame extends Frame {

	private int thirdRoll = 0;

	TenthFrame() {		
		frameNumber = 10;
		nextFrame = null;
	}

	@Override
	void roll(int number) {
		if (number < 0) {
			throw new IllegalArgumentException("number must be positive");
		}
		if (ballsThrown > 2) {
			throw new UnsupportedOperationException("Max 3 rolls in last Frame");
		}		
		ballsThrown++;
		switch (ballsThrown) {
			case 1:
				firstRoll = number;
				break;
			case 2:
				secondRoll = number;
				break;
			case 3:
				thirdRoll = number;
		}
	}

	@Override
	int strikeBonus() {
		return firstRoll + secondRoll;
	}

	@Override
	int score() {
		return firstRoll + secondRoll + thirdRoll;
	}

	@Override
	public boolean isFinished() {
		return (isStrike() || isSpare()) ? (ballsThrown == 3) : (ballsThrown == 2);
	}

	@Override
	public String toString() {
		return super.toString() + ", third roll: " + thirdRoll;
	}
}
