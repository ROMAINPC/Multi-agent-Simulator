package games.ballgames;

/**
 * 
 * Tests du package du jeu de balles.
 *
 */
public class TestBalls {

	public static void main(String[] args) {

		Balls balls1 = new Balls();
		System.out.println("Ensemble vide : " + balls1);
		balls1.addBall(4, 3);
		balls1.addBall(-2, 6);
		System.out.println("Avec " + balls1.getNbBalls() + " balles : " + balls1);
		balls1.addBall(1, 1);
		System.out.println("Et une balle en (1;1) : " + balls1);
		balls1.removeBall(1);
		System.out.println("En enlevant la 2ème : " + balls1);
		try {
			balls1.removeBall(42);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Test d'exception sur mauvais indexe effectué.");
		}

		System.out.println("Il reste " + balls1.getNbBalls() + " balles.");

		int[][] balls = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 }, { 5, 6 } };
		Balls balls2 = new Balls(balls);
		System.out.println("Ensemble de " + balls2.getNbBalls() + " balles :");
		System.out.println(balls2);
		balls2.translate(2, -1);
		System.out.println("Après translation de (2;-1) :");
		System.out.println(balls2);
		balls2.translate(4, 5);
		System.out.println("Après translation de (4;5) :");
		System.out.println(balls2);
		balls2.reInit();
		System.out.println("Après réinitialisation des positions :");
		System.out.println(balls2);

		System.out.println("La 3ème balle a une vitesse de (2;3) et la 4ème est retirée :");
		balls2.setHSpeed(2, 2);
		balls2.setVSpeed(2, 3);
		balls2.removeBall(3);
		balls2.translate();
		System.out.println(balls2);
		balls2.translate();
		System.out.println(balls2);

		System.out.println("Merci, de rien, au revoir messieurs dames.");

	}

}
