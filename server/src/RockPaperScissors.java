import java.util.Random;

public class RockPaperScissors {

    private String[] movements = { "pedra", "papel", "tesoura" };

    public String getAnswer(String clientMovement) {

        Random rand = new Random();
        int randNum = rand.nextInt(3);

        String serverMove = this.movements[randNum];
        System.out.println(clientMovement);
        System.out.println(serverMove);
        String serverAnswer = "";

        if (serverMove.equalsIgnoreCase(clientMovement)) {
            serverAnswer = serverMove + ";empate";
            return serverAnswer;
        }

        if (clientMovement.equalsIgnoreCase("pedra") && serverMove.equals("tesoura") ||
                clientMovement.equalsIgnoreCase("tesoura") && serverMove.equals("papel") ||
                clientMovement.equalsIgnoreCase("papel") && serverMove.equals("pedra")) {
            serverAnswer = serverMove + ";vitoria";
        } else {
            serverAnswer = serverMove + ";derrota";
        }

        return serverAnswer;
    }
}
