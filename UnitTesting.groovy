import gameLogic.GameInstance
import gameLogic.*;

import org.junit.Test
import org.junit.Assert

class UnitTesting {

    @Test
    public void testCreateGame(){
        GameInstance game = new GameInstance();
        Assert.assertTrue(game.turnCount == 12);
    }

    @Test
    public void testWin(){
        //Testing for ten times to make sure it works with randomized results
        for(int i = 0; i < 10; i++){
            GameInstance game = new GameInstance();
            String[] temp = new String[4];
            temp = game.getAnswer();
            game.guess(temp[0], temp[1], temp[2], temp[3]);
            Assert.assertTrue(game.blackpegs == 4);
        }
    }
    @Test
    public void testThreePegs(){
        for(int i = 0; i < 10; i++){
            GameInstance game = new GameInstance();
            String[] temp = new String[4];
            temp = game.getAnswer();
            game.guess(temp[0], temp[1], temp[2], "yeehaw");
            Assert.assertTrue(game.blackpegs == 3);
        }
    }
    @Test
    public void testTwoPegs(){
        for(int i = 0; i < 10; i++){
            GameInstance game = new GameInstance();
            String[] temp = new String[4];
            temp = game.getAnswer();
            game.guess(temp[0], temp[1], "bust", "yeehaw");
            Assert.assertTrue(game.blackpegs == 2);
        }
    }
    @Test
    public void testOnePeg(){
        for(int i = 0; i < 10; i++){
            GameInstance game = new GameInstance();
            String[] temp = new String[4];
            temp = game.getAnswer();
            game.guess(temp[0], "yeet", "bust", "yeehaw");
            Assert.assertTrue(game.blackpegs == 1);
        }
    }
    @Test
    public void testNoPegs(){
        for(int i = 0; i < 10; i++){
            GameInstance game = new GameInstance();
            String[] temp = new String[4];
            temp = game.getAnswer();
            game.guess("literally anything", "yeet", "bust", "yeehaw");
            Assert.assertTrue(game.blackpegs == 0);
        }
    }

    @Test
    public void testSetAnswer(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "red", "red", "red");
        String[] temp = new String[4];
        temp[0] = game.getAnswer()[0];
        temp[1] = game.getAnswer()[1];
        temp[2] = game.getAnswer()[2];
        temp[3] = game.getAnswer()[3];
        String answerString = (""+temp[0]+""+temp[1]+""+temp[2]+""+temp[3]);
        Assert.assertTrue(answerString.equals("redredredred"));
        game.setAnswer("yellow", "green", "blue", "blue");
        temp[0] = game.getAnswer()[0];
        temp[1] = game.getAnswer()[1];
        temp[2] = game.getAnswer()[2];
        temp[3] = game.getAnswer()[3];
        answerString = (""+temp[0]+""+temp[1]+""+temp[2]+""+temp[3]);
    }

    @Test
    public void testDuplicatePeg1(){
        GameInstance game = new GameInstance();
            game.setAnswer("red","red","green","blue");
            game.guess("orange","purple","purple", "red");
            Assert.assertTrue(game.whitepegs == 1);
    }

    @Test
    public void testDuplicatePeg2(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "red", "green", "blue");
        game.guess("red", "green", "purple", "purple");
        Assert.assertTrue(game.whitepegs == 1 && game.blackpegs == 1);
    }

    @Test
    public void testDuplicatePeg3(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "red", "red", "red");
        game.guess("red", "blellow", "pasta", "literallydoesnotmatter");
        Assert.assertTrue(game.whitepegs == 0 && game.blackpegs == 1);
    }

    @Test
    public void testTurns1(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "yellow", "green", "blue");
        game.guess("red", "orange", "orange", "orange");
        Turn turn = game.turns[0];
        Assert.assertTrue(turn.getMove().orderArray[0].equals("red"));
    }
    @Test
    public void testTurns2() {
        GameInstance game = new GameInstance();
        game.setAnswer("red", "yellow", "green", "blue");
        game.guess("red", "orange", "orange", "orange");
        Turn turn = game.turns[0];
        Assert.assertTrue(turn.getMove().orderArray[3].equals("orange"));
    }
    @Test
    public void testTurns3(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "yellow", "green", "blue");
        game.guess("red", "orange", "orange", "orange");
        Turn turn = game.turns[0];
        Assert.assertTrue(turn.getBlackpegs() == 1);
    }

    @Test
    public void testTurns4(){
        GameInstance game = new GameInstance();
        game.setAnswer("red", "yellow", "green", "blue");
        game.guess("red", "orange", "orange", "orange");
        game.guess("purple","pink","blue","pink");
        Turn turn = game.turns[1];
        Assert.assertTrue(turn.getMove().orderArray[3].equals("pink"));
    }
}
