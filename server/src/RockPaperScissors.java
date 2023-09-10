import java.util.Random;

public class RockPaperScissors {
    
    private String[] movements = {"pedra","papel","tesoura"};

    public String getAnswer(String clientMovement){

        Random rand = new Random();
        int randNum = rand.nextInt(3);

        String serverMove = this.movements[randNum];
        String serverAnswer = "";

        if(serverMove.equals(clientMovement)){
            serverAnswer = serverMove+";empate";
        }

        if(clientMovement.equals("pedra")&&serverMove.equals("tesoura") ||
           clientMovement.equals("tesoura")&&serverMove.equals("papel") ||
           clientMovement.equals("papel")&&serverMove.equals("pedra")){
            serverAnswer = serverMove+";vitoria";
        }else{
            serverAnswer = serverMove+";derrota";
        }

        return serverAnswer;
    }
}
