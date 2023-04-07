
import java.util.Scanner;
import java.util.StringTokenizer;


import java.io.*;

public class Game extends AbstractGame{

    static Scanner scan;

    public Game(){

    }

    public Game(String playerRed, String playerBlack){

        super(playerRed,playerBlack);

    }


    @Override
    void play(String from, String to) {

        Player currentPlayer = null, waitedPlayer = null;

        if(isThisLegal(from,to)) {

            int result = ItemInterface.convertFromStringToIndex(from) ,fromFRow_x = result / 10, fromColumn_y = result % 10;
            int resultTo = ItemInterface.convertFromStringToIndex(to),to_x = resultTo / 10,to_y = resultTo % 10;

            if (Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y].getType().equals("red")) {

                currentPlayer = red;
                waitedPlayer = black;

                Player.getIsMyTurn()[0] = false;
                Player.getIsMyTurn()[1] = true;

            } else {

                currentPlayer = black;
                waitedPlayer = red;

                Player.getIsMyTurn()[0] = true;
                Player.getIsMyTurn()[1] = false;

            }

            Item currentItem = Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y];
            Item opponentItem = Player.getCurrentBoardAtPlayer()[to_x][to_y];

            if(opponentItem == null){
                moveTheItemNullPlace(from,to,currentItem,board);
            }
            else{
                moveTheItemNotNullPlace(from,to,currentPlayer,currentItem,waitedPlayer,opponentItem,board);
            }

            waitedPlayer.setIncheck(waitedPlayer.isIncheck(currentPlayer));

                if(waitedPlayer.getIncheck()){

                    Item opponentItem1 = waitedPlayer.findTheMakeCheckItem(currentPlayer);

                    if(waitedPlayer.isCheckMated(opponentItem1,this)){

                        System.out.println("ŞAH MAT! "+currentPlayer.getPlayerName()+" oyunu kazandı. "+currentPlayer.getPlayerName()+"'in puanı: "+currentPlayer.getPuan()+
                                ", "+waitedPlayer.getPlayerName()+"'nin puanı: "+waitedPlayer.getPuan());

                        AbstractGame.setIsGameFinish(true);

                        return;
                    }

                }

            if(currentItem instanceof Soldier){
                ((Soldier)currentItem).setOverTheRiver(((Soldier) currentItem).isOverTheRiverControl());
            }


        }

        else{
            System.out.println("hatali hareket");
        }



    }

    public static void moveTheItemNullPlace(String from,String to,Item item,Board board){

        int result = ItemInterface.convertFromStringToIndex(from) ,fromFRow_x = result / 10, fromColumn_y = result % 10, resultTo = ItemInterface.convertFromStringToIndex(to),to_x = resultTo / 10,to_y = resultTo % 10;

        item.move(to,board);

        Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = null;
        Player.getCurrentBoardAtPlayer()[to_x][to_y] = item;



    }

    public static void moveTheItemNotNullPlace(String from,String to,Player currentPlayer,Item item,Player waitedPlayer,Item opponentItem,Board board){

        int result = ItemInterface.convertFromStringToIndex(from) ,fromFRow_x = result / 10, fromColumn_y = result % 10, resultTo = ItemInterface.convertFromStringToIndex(to),to_x = resultTo / 10,to_y = resultTo % 10;

        item.move(to,board);

        currentPlayer.setPuan(opponentItem.getPointValue());

        board.items[opponentItem.getItemIndex()].setPosition("xx");

        waitedPlayer.getPlayerPieces()[opponentItem.getIndex()] = null;

        Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = null;

        Player.getCurrentBoardAtPlayer()[to_x][to_y] = item;

    }

    public static boolean makeTheControl(String from,String to,Player currentPlayer,Item currentItem,Player waitedPlayer,Item opponentItem,Board board){

        int result = ItemInterface.convertFromStringToIndex(from) ,fromFRow_x = result / 10, fromColumn_y = result % 10, resultTo = ItemInterface.convertFromStringToIndex(to),to_x = resultTo / 10,to_y = resultTo % 10;

        if(Player.getCurrentBoardAtPlayer()[to_x][to_y] == null){

            moveTheItemNullPlace(from,to,currentItem,board);

            if(currentPlayer.resultOfThisMoveIsCheck(waitedPlayer) || currentPlayer.resultOfThisMoveTwoGeneralAreAgainst(waitedPlayer)){

                currentItem.move(from,board);
                Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = currentItem;
                Player.getCurrentBoardAtPlayer()[to_x][to_y] = null;

                return false;
            }

            currentItem.move(from,board);
            Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = currentItem;
            Player.getCurrentBoardAtPlayer()[to_x][to_y] = null;

            return true;

        }
        else{

            moveTheItemNotNullPlace(from,to,currentPlayer,currentItem,waitedPlayer,opponentItem,board);

            if(currentPlayer.resultOfThisMoveIsCheck(waitedPlayer) || currentPlayer.resultOfThisMoveTwoGeneralAreAgainst(waitedPlayer)){

                currentItem.move(from,board);

                currentPlayer.setPuan(-opponentItem.getPointValue());

                board.items[opponentItem.getItemIndex()].setPosition(opponentItem.getPosition());

                waitedPlayer.getPlayerPieces()[opponentItem.getIndex()] = opponentItem;

                Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = currentItem;

                Player.getCurrentBoardAtPlayer()[to_x][to_y] = opponentItem;


                return false;
            }

            currentItem.move(from,board);

            currentPlayer.setPuan(-opponentItem.getPointValue());

            board.items[opponentItem.getItemIndex()].setPosition(opponentItem.getPosition());

            waitedPlayer.getPlayerPieces()[opponentItem.getIndex()] = opponentItem;

            Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] = currentItem;

            Player.getCurrentBoardAtPlayer()[to_x][to_y] = opponentItem;

            return true;
        }

    }

    @Override
    void save_binary(String address) {

        ObjectOutputStream output = null;

        try{

            output = new ObjectOutputStream(new FileOutputStream(address));

            output.writeObject(red);
            output.writeObject(black);
            output.writeObject(board);
            output.writeObject(Board.getCurrentBoard());
            output.writeBoolean(getIsGameFinish());
            output.writeObject(Player.getCurrentBoardAtPlayer());
            output.writeObject(Player.getIsMyTurn());
            output.close();

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    void load_binary(String address) {

        ObjectInputStream input = null;

        try{

            input = new ObjectInputStream(new FileInputStream(address));

            red = (Player) input.readObject();
            black = (Player)  input.readObject();
            board = (Board) input.readObject();
            Board.setCurrentBoard((Item[][]) input.readObject());
            setIsGameFinish(input.readBoolean());
            Player.setCurrentBoardAtPlayer((Item[][]) input.readObject());
            Player.setIsMyTurn((boolean[]) input.readObject());

            input.close();

        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    void save_text(String address) {

        PrintWriter output = null;

        try{
            output = new PrintWriter(new FileOutputStream(address));

            this.saveInfo(output);

            output.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    void load_text(String address) {

        try{

           scan = new Scanner(new FileInputStream(address));

           this.loadInfo();

           updateAllBoards(red,black);

           scan.close();

        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

    }



    public void saveInfo(PrintWriter output){

        red.saveInfo(output);
        black.saveInfo(output);
        board.saveInfo(output);
        saveStaticInfo(output);

    }

    public void loadInfo(){

        red = red.loadInfo();
        black = black.loadInfo();
        board = board.loadInfo();
        loadStaticInfo();

    }

    public static void saveStaticInfo(PrintWriter output){

        output.print(getIsGameFinish()+",");
        output.print(Player.getIsMyTurn()[0]+",");
        output.print(Player.getIsMyTurn()[1]+",");

    }

    public static void loadStaticInfo(){

        String wholeInformation2 = scan.next();

        StringTokenizer tokenizer2 = new StringTokenizer(wholeInformation2, ",");

        setIsGameFinish(Boolean.parseBoolean(tokenizer2.nextToken()));
        Player.getIsMyTurn()[0] = Boolean.parseBoolean(tokenizer2.nextToken());
        Player.getIsMyTurn()[1] = Boolean.parseBoolean(tokenizer2.nextToken());

    }


    public void updateAllBoards(Player red, Player black){

        for(int i=1; i<=10; i++)
            for(int k=1; k<=9; k++)
                Player.getCurrentBoardAtPlayer()[i][k] = null;

        for (int i=0; i<red.getPlayerPieces().length; i++){

            if(red.getPlayerPieces()[i] != null){
                int row_x = red.getPlayerPieces()[i].getRow_x();
                int column_y = red.getPlayerPieces()[i].getColumn_y();
                Player.getCurrentBoardAtPlayer()[row_x][column_y] = red.getPlayerPieces()[i];
            }

        }

        for (int i=0; i<black.getPlayerPieces().length; i++){

            if(black.getPlayerPieces()[i] != null){
                int row_x = black.getPlayerPieces()[i].getRow_x();
                int column_y = black.getPlayerPieces()[i].getColumn_y();
                Player.getCurrentBoardAtPlayer()[row_x][column_y] = black.getPlayerPieces()[i];
            }

        }

        Board.setCurrentBoard(Player.getCurrentBoardAtPlayer());

    }

}

