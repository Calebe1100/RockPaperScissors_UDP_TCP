import java.util.Random;

public class RockPaperScissors {

    private String[] movements = { "pedra", "papel", "tesoura" };

    public String getAnswer(String clientMovement) {
        if (clientMovement.equalsIgnoreCase("cancel")) {
            return clientMovement;
        }
        Random rand = new Random();
        int randNum = rand.nextInt(3);

        String serverMove = this.movements[randNum];
        System.out.println(clientMovement);
        System.out.println(serverMove);
        String serverAnswer = "";

        if (clientMovement.toLowerCase().contains(serverMove.toLowerCase())) {
            serverAnswer = serverMove + ";empate";
            return serverAnswer;
        }

        if (clientMovement.toLowerCase().contains("pedra") && serverMove.equals("tesoura") ||
                clientMovement.toLowerCase().contains("tesoura") && serverMove.equals("papel") ||
                clientMovement.toLowerCase().contains("papel") && serverMove.equals("pedra")) {
            serverAnswer = serverMove + ";vitoria";
        } else {
            serverAnswer = serverMove + ";derrota";
        }

        return serverAnswer;
    }
}
