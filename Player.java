
import java.io.*;

import java.util.StringTokenizer;

public class Player implements Serializable {

	private static  boolean isMyTurn[]={true,false};
	private boolean isIncheck;
	float puan; // her taş yedikçe oyuncunun puanı taşın puanına göre artar.
	private String playerName;
	private String piecesColour;
	private static Item[][] currentBoardAtPlayer = new Item[11][10];
	private Item[] playerPieces = new Item[16];

	public Player(){

	}

	public Player(float puan, String playerName, String piecesColour) {

		this.puan = puan;
		this.playerName = playerName;
		this.piecesColour = piecesColour;

		if(piecesColour.equals("red")){
			playerPieces[0] = new Chariot(piecesColour,1,1,9,0,0);
			playerPieces[1] = new Horse(piecesColour,1,2,4,1,1);
			playerPieces[2] = new Elephant(piecesColour,1,3,2,2,2);
			playerPieces[3] = new Advisor(piecesColour,1,4,2,"leftAdvisor",3,3);
			playerPieces[4] = new General(piecesColour,1,5,0,false,false,false,4,4);
			playerPieces[5] = new Advisor(piecesColour,1,6,2,"rightAdvisor",5,5);
			playerPieces[6] = new Elephant(piecesColour,1,7,2,6,6);
			playerPieces[7] = new Horse(piecesColour,1,8,4,7,7);
			playerPieces[8] = new Chariot(piecesColour,1,9,9,8,8);
			playerPieces[9] = new Cannon(piecesColour,3,2,(float) 4.5,9,9);
			playerPieces[10] = new Cannon(piecesColour,3,8,(float) 4.5,10,10);
			playerPieces[11] = new Soldier(piecesColour,4,1,1,false,11,11);
			playerPieces[12] = new Soldier(piecesColour,4,3,1,false,12,12);
			playerPieces[13] = new Soldier(piecesColour,4,5,1,false,13,13);
			playerPieces[14] = new Soldier(piecesColour,4,7,1,false,14,14);
			playerPieces[15] = new Soldier(piecesColour,4,9,1,false,15,15);
		}
		else{
			playerPieces[0] = new Chariot(piecesColour,10,1,9,0,16);
			playerPieces[1] = new Horse(piecesColour,10,2,4,1,17);
			playerPieces[2] = new Elephant(piecesColour,10,3,2,2,18);
			playerPieces[3] = new Advisor(piecesColour,10,4,2,"leftAdvisor",3,19);
			playerPieces[4] = new General(piecesColour,10,5,0,false,false,false,4,20);
			playerPieces[5] = new Advisor(piecesColour,10,6,2,"rightAdvisor",5,21);
			playerPieces[6] = new Elephant(piecesColour,10,7,2,6,22);
			playerPieces[7] = new Horse(piecesColour,10,8,4,7,23);
			playerPieces[8] = new Chariot(piecesColour,10,9,9,8,24);
			playerPieces[9] = new Cannon(piecesColour,8,2,(float) 4.5,9,25);
			playerPieces[10] = new Cannon(piecesColour,8,8,(float) 4.5,10,26);
			playerPieces[11] = new Soldier(piecesColour,7,1,1,false,11,27);
			playerPieces[12] = new Soldier(piecesColour,7,3,1,false,12,28);
			playerPieces[13] = new Soldier(piecesColour,7,5,1,false,13,29);
			playerPieces[14] = new Soldier(piecesColour,7,7,1,false,14,30);
			playerPieces[15] = new Soldier(piecesColour,7,9,1,false,15,31);

		}
	}



	public static void makeCurrentBoardAtPlayer(Player white, Player black){

		for(int i=1; i<=9; i++)
			currentBoardAtPlayer[1][i] = white.playerPieces[i-1];

		for(int i=1; i<=9; i++)
			currentBoardAtPlayer[10][i] = black.playerPieces[i-1];

		currentBoardAtPlayer[3][2] = white.playerPieces[9];



		currentBoardAtPlayer[3][8] = white.playerPieces[10];

		currentBoardAtPlayer[8][2] = black.playerPieces[9];

		currentBoardAtPlayer[8][8] = black.playerPieces[10];

		int index = 0;
		for(int i=1; i<=9; i+=2){

			currentBoardAtPlayer[4][i]=white.playerPieces[11+index];
			index++;

		}

		index = 0;

		for(int i=1; i<=9; i+=2){
			currentBoardAtPlayer[7][i]=black.playerPieces[11+index];
			index++;
		}

	}
	public boolean resultOfThisMoveIsCheck(Player opponent){

		for(int i=0; i<opponent.getPlayerPieces().length; i++){
			if(opponent.getPlayerPieces()[i] != null){
				if(opponent.getPlayerPieces()[i].isMoveable(this.getPlayerPieces()[4].getPosition()))
					return true;
			}
		}

		return false;
	}

	public  boolean resultOfThisMoveTwoGeneralAreAgainst(Player opponent) {

		General generalCurrent = (General) this.getPlayerPieces()[4];
		int generalCurrentRow_x = generalCurrent.getRow_x();
		int generalCurrentColumn_y = generalCurrent.getColumn_y();

		General generalOpponent = (General) opponent.getPlayerPieces()[4];
		int generalOpponentRow_x = generalOpponent.getRow_x();
		int generalOpponentColumn_y = generalOpponent.getColumn_y();

		if(generalOpponentColumn_y == generalCurrentColumn_y) {

			int destinationColumn_y = generalCurrentColumn_y;

			boolean isTherePieceOnThePath = false;

			boolean isAgainst = true;

			if (generalCurrent.getType().equals("red")) {

				for (int i = generalCurrentRow_x + 1; i<generalOpponentRow_x && !isTherePieceOnThePath; i++) {

					if (Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null)
						isTherePieceOnThePath = true;
				}

				if (isTherePieceOnThePath) isAgainst = false;

				else isAgainst = true;


			} else {

				for (int i = generalCurrentRow_x- 1; i > generalOpponentRow_x && !isTherePieceOnThePath; i--) {

					if (Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null)
						isTherePieceOnThePath = true;
				}

				if (isTherePieceOnThePath) isAgainst = false;

				else isAgainst = true;

			}

			return isAgainst;
		}
		return false;
	}

	public boolean isIncheck(Player opponent){

		for(int i=0; i< opponent.getPlayerPieces().length; i++){
			if(opponent.getPlayerPieces()[i] !=null){
				if(opponent.getPlayerPieces()[i].isMoveable(this.getPlayerPieces()[4].getPosition()))
					return true;
			}
		}
		return false;
	}


	public boolean isGeneralSaveAble(Item opponentItem,Game game){

		int itemRow_x = opponentItem.getRow_x();
		int itemColumn_y = opponentItem.getColumn_y();

		int generalRow_x = this.playerPieces[4].getRow_x();
		int generalColumn_y = this.playerPieces[4].getColumn_y();

		if(opponentItem instanceof Chariot || opponentItem instanceof Cannon){

			if(generalRow_x == itemRow_x){

				if(itemColumn_y < generalColumn_y){

					for(int i=itemColumn_y+1; i<generalColumn_y; i++){
						String destination = ItemInterface.convertFromIndexToString(itemRow_x,i);
						for(int k=0; k<this.getPlayerPieces().length; k++){
							if(this.getPlayerPieces()[k] !=null) {
								if (game.isThisLegal(this.getPlayerPieces()[k].getPosition(),destination)){
									return true;
								}
							}
						}
					}
					if(opponentItem instanceof Cannon){
						for(int i=itemColumn_y +1; i<generalColumn_y; i++){
							if(Player.getCurrentBoardAtPlayer()[itemRow_x][i] != null && Player.getCurrentBoardAtPlayer()[itemRow_x][i].getType().equals(this.getPlayerPieces()[4].getType())){
								Item canSaveItem = Player.getCurrentBoardAtPlayer()[itemRow_x][i];
								return isCannonDefendable(canSaveItem,opponentItem,game);
							}
						}
					}
					return false;
				}
				else{

					for(int i=generalColumn_y+1; i<itemColumn_y; i++){
						String destination = ItemInterface.convertFromIndexToString(itemRow_x,i);
						for(int k=0; k<this.getPlayerPieces().length; k++){
							if(this.getPlayerPieces()[k] !=null) {
								if (game.isThisLegal(this.getPlayerPieces()[k].getPosition(),destination)) {
									return true;
								}
							}
						}
					}
					if(opponentItem instanceof Cannon){
						for(int i=generalColumn_y+1; i<itemColumn_y; i++){
							if(Player.getCurrentBoardAtPlayer()[itemRow_x][i] != null && Player.getCurrentBoardAtPlayer()[itemRow_x][i].getType().equals(this.getPlayerPieces()[4].getType())){
								Item canSaveItem = Player.getCurrentBoardAtPlayer()[itemRow_x][i];
								return isCannonDefendable(canSaveItem,opponentItem,game);
							}
						}
					}

					return false;
				}

			}

			else if(generalColumn_y == itemColumn_y){

				if(itemRow_x < generalRow_x){

					for(int i=itemRow_x+1; i<generalRow_x; i++){
						String destination = ItemInterface.convertFromIndexToString(i,itemColumn_y);
						for(int k=0; k<this.getPlayerPieces().length; k++){
							if(this.getPlayerPieces()[k] !=null) {
								if (game.isThisLegal(this.getPlayerPieces()[k].getPosition(),destination)) {
									return true;
								}

							}
						}
					}
					if(opponentItem instanceof Cannon){
						for(int i=itemRow_x +1; i<generalRow_x; i++){
							if(Player.getCurrentBoardAtPlayer()[i][itemColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][itemColumn_y].getType().equals(this.getPlayerPieces()[4].getType())){
								Item canSaveItem = Player.getCurrentBoardAtPlayer()[i][itemColumn_y];
								return isCannonDefendable(canSaveItem,opponentItem,game);
							}
						}
					}

					return false;
				}
				else{
					for(int i=generalRow_x+1; i<itemRow_x; i++){
						String destination = ItemInterface.convertFromIndexToString(i,itemColumn_y);
						for(int k=0; k<this.getPlayerPieces().length; k++){
							if(this.getPlayerPieces()[k] !=null) {
								if (game.isThisLegal(this.getPlayerPieces()[k].getPosition(),destination)) {
									return true;
								}
							}
						}
					}
					if(opponentItem instanceof Cannon){
						for(int i=generalRow_x+1; i<itemRow_x; i++){
							if(Player.getCurrentBoardAtPlayer()[i][itemColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][itemColumn_y].getType().equals(this.getPlayerPieces()[4].getType())){
								Item canSaveItem = Player.getCurrentBoardAtPlayer()[i][itemColumn_y];
								return isCannonDefendable(canSaveItem,opponentItem,game);
							}
						}
					}
					return false;
				}
			}

		}
		else if(opponentItem instanceof Horse){
			if(opponentItem.getRow_x() + 1 == generalRow_x && opponentItem.getColumn_y() - 2 == generalColumn_y){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(opponentItem.getRow_x(),generalColumn_y+1);
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getRow_x() + 1 == generalRow_x && opponentItem.getColumn_y() + 2 == generalColumn_y){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(opponentItem.getRow_x(),generalColumn_y-1);
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getRow_x() - 1 == generalRow_x && opponentItem.getColumn_y() - 2 == generalColumn_y){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(opponentItem.getRow_x(),generalColumn_y+1);
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getRow_x() - 1 == generalRow_x && opponentItem.getColumn_y() + 2 == generalColumn_y){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(opponentItem.getRow_x(),generalColumn_y-1);
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getColumn_y()-1 == generalColumn_y && opponentItem.getRow_x()+2 == generalRow_x){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(generalRow_x-1,opponentItem.getColumn_y());
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getColumn_y()-1 == generalColumn_y && opponentItem.getRow_x()-2 == generalRow_x){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(generalRow_x+1,opponentItem.getColumn_y());
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getColumn_y()+1 == generalColumn_y && opponentItem.getRow_x()+2 == generalRow_x){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(generalRow_x-1,opponentItem.getColumn_y());
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
			else if(opponentItem.getColumn_y()+1 == generalColumn_y && opponentItem.getRow_x()-2 == generalRow_x){
				for(int i=0; i<this.getPlayerPieces().length; i++){
					String from = this.playerPieces[i].getPosition();
					String destination = ItemInterface.convertFromIndexToString(generalRow_x+1,opponentItem.getColumn_y());
					if(game.isThisLegal(from,destination))
						return true;
				}
				return false;
			}
		}
		return false;
	}

	public boolean isCheckMated(Item opponentItem,Game game){

		boolean isCheckMated = false;
		String position1 = null, position2 = null, position3 = null, position4 = null;

		General general = (General) this.getPlayerPieces()[4];

		position1 = ItemInterface.convertFromIndexToString(general.getRow_x(),general.getColumn_y()+1);
		position2 = ItemInterface.convertFromIndexToString(general.getRow_x(),general.getColumn_y()-1);
		position3 = ItemInterface.convertFromIndexToString(general.getRow_x()+1,general.getColumn_y());
		position4 = ItemInterface.convertFromIndexToString(general.getRow_x()-1,general.getColumn_y());

		boolean generalMove =this.isGeneralMoveable(position1,game) || this.isGeneralMoveable(position2,game) || this.isGeneralMoveable(position3,game) ||this.isGeneralMoveable(position4,game);

		boolean isCatch = this.isCanCatch(opponentItem.getPosition(),game);

		boolean isSave = this.isGeneralSaveAble(opponentItem,game);

		isCheckMated = !generalMove && !isCatch && !isSave;

		return isCheckMated;
	}


	public boolean isGeneralMoveable(String position,Game game){

		String from =this.playerPieces[4].getPosition();
		if(game.isThisLegal(from,position))
			 return true;

		return false;

	}


	public boolean isCanCatch(String position,Game game){

		for(int i=0; i<this.getPlayerPieces().length; i++){
			if(this.getPlayerPieces()[i] != null){
				String from = ItemInterface.convertFromIndexToString(this.playerPieces[i].getRow_x(),this.playerPieces[i].getColumn_y());
				if(game.isThisLegal(from,position)){
						return true;
					}
				}
			}
		return false;
	}


	public Item findTheMakeCheckItem(Player opponent){

		for(int i=0; i<opponent.getPlayerPieces().length; i++){
			if(opponent.getPlayerPieces()[i] != null){
				if(opponent.getPlayerPieces()[i].isMoveable(this.getPlayerPieces()[4].getPosition())){
					return opponent.getPlayerPieces()[i];
				}
			}
		}
		return null;
	}

	public boolean isCannonDefendable(Item canSaveItem, Item opponentCannon ,Game game){

		int general_Row_x = this.playerPieces[4].getRow_x(), general_Column_y = this.playerPieces[4].getColumn_y();
		int cannonRowx = opponentCannon.getRow_x(), cannonColumny = opponentCannon.getColumn_y();

		if(canSaveItem instanceof Chariot){

			if(cannonRowx == general_Row_x){

				String from = canSaveItem.getPosition(), to1 = ItemInterface.convertFromIndexToString(canSaveItem.getRow_x()+1,canSaveItem.getColumn_y());
				String to2 =  ItemInterface.convertFromIndexToString(canSaveItem.getRow_x()-1,canSaveItem.getColumn_y());

				if(game.isThisLegal(from,to1) || game.isThisLegal(from,to2)) return true;
				else return false;

			}
			else if(cannonColumny == general_Column_y){

				String from = canSaveItem.getPosition(), to1 = ItemInterface.convertFromIndexToString(canSaveItem.getRow_x(),canSaveItem.getColumn_y()+1);
				String to2 =  ItemInterface.convertFromIndexToString(canSaveItem.getRow_x(),canSaveItem.getColumn_y()-1);

				if(game.isThisLegal(from,to1) || game.isThisLegal(from,to2)) return true;
				else return false;

			}

		}
		else if(canSaveItem instanceof Cannon) {

			String from = canSaveItem.getPosition();

			if (cannonRowx == general_Row_x) {

				for (int i = 1; i <= 10; i++) {

					String to = ItemInterface.convertFromIndexToString(i, canSaveItem.getColumn_y());

					if (game.isThisLegal(from, to)) return true;

				}

			} else if (cannonColumny == general_Column_y){

				for (int i = 1; i <= 9; i++) {

					String to = ItemInterface.convertFromIndexToString(canSaveItem.getRow_x(), i);

					if (game.isThisLegal(from, to)) return true;

				}

		}

			return false;

		}
		else if(canSaveItem instanceof Horse){

			String from = canSaveItem.getPosition();
			int x = canSaveItem.getRow_x(), y = canSaveItem.getColumn_y();
			String to1 = ItemInterface.convertFromIndexToString(x+1,y+2), to2 = ItemInterface.convertFromIndexToString(x+1,y-2);
			String to3 = ItemInterface.convertFromIndexToString(x-1,y+2), to4 = ItemInterface.convertFromIndexToString(x-1,y-2);
			String to5 = ItemInterface.convertFromIndexToString(x+2,y+1), to6 = ItemInterface.convertFromIndexToString(x+2,y-1);
			String to7 = ItemInterface.convertFromIndexToString(x-2,y+1), to8 = ItemInterface.convertFromIndexToString(x-2,y-1);

			if(game.isThisLegal(from,to1) || game.isThisLegal(from,to2) || game.isThisLegal(from,to3) || game.isThisLegal(from,to4) || game.isThisLegal(from,to5) || game.isThisLegal(from,to6)||
					game.isThisLegal(from,to7) || game.isThisLegal(from,to8)) return true;
			else return false;

		}
		else if(canSaveItem instanceof Elephant){

			String from = canSaveItem.getPosition();
			int x = canSaveItem.getRow_x(), y = canSaveItem.getColumn_y();

			String to1 = ItemInterface.convertFromIndexToString(x+2,y+2), to2 = ItemInterface.convertFromIndexToString(x+2,y-2);
			String to3 = ItemInterface.convertFromIndexToString(x-2,y+2), to4 = ItemInterface.convertFromIndexToString(x-2,y-2);

			if(game.isThisLegal(from,to1) || game.isThisLegal(from,to2) || game.isThisLegal(from,to3) || game.isThisLegal(from,to4)) return true;
			else return false;


		}
		else if(canSaveItem instanceof Advisor){

					String from = canSaveItem.getPosition();
					int x = canSaveItem.getRow_x(), y = canSaveItem.getColumn_y();

					String to1 = ItemInterface.convertFromIndexToString(x+1,y+1), to2 = ItemInterface.convertFromIndexToString(x-1,y-1);
					String to3 = ItemInterface.convertFromIndexToString(x+1,y-1), to4 = ItemInterface.convertFromIndexToString(x-1,y+1);

					if(game.isThisLegal(from,to1) || game.isThisLegal(from,to2) || game.isThisLegal(from,to3) || game.isThisLegal(from,to4)) return true;
					else return false;
		 }

		return false;
	}


	public void saveInfo(PrintWriter output){

		output.print(this);

	}


	public Player loadInfo(){

		String wholeInformation = Game.scan.next();
		StringTokenizer tokenizer = new StringTokenizer(wholeInformation,",");

		setIncheck(Boolean.parseBoolean(tokenizer.nextToken()));
		setPlayerName(tokenizer.nextToken());
		setPiecesColour(tokenizer.nextToken());
		setPuan(Float.parseFloat(tokenizer.nextToken()));

		for(int i=0; i<getPlayerPieces().length; i++){
			String whole = Game.scan.next();
			if(getPlayerPieces()[i] != null && !whole.equals("null,")){
				getPlayerPieces()[i] = getPlayerPieces()[i].loadInfo(whole);
			}
			else getPlayerPieces()[i] = null;
		}

		return this;
	}


	public String toString(){

		String representation = "";
		representation = representation+isIncheck+","+playerName+","+piecesColour+","+puan+","+"\n";

		for(int i=0; i<getPlayerPieces().length; i++){
			if(getPlayerPieces()[i] != null){
				representation = representation+getPlayerPieces()[i].toString()+"\n";
			}
			else representation+="null,\n";

		}
		return representation;
	}



	public static boolean[] getIsMyTurn() {
		return isMyTurn;
	}

	public static void setIsMyTurn(boolean[] isMyTurn) {
		Player.isMyTurn = isMyTurn;
	}

	public static Item[][] getCurrentBoardAtPlayer() {
		return currentBoardAtPlayer;
	}

	public float getPuan() {
		return puan;
	}

	public void setPuan(float puan) {
		this.puan +=puan;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPiecesColour() {
		return piecesColour;
	}

	public void setPiecesColour(String piecesColour) {
		this.piecesColour = piecesColour;
	}

	public Item[] getPlayerPieces() {
		return playerPieces;
	}

	public void setPlayerPieces(Item[] playerPieces) {
		this.playerPieces = playerPieces;
	}

	public boolean getIncheck() {
		return isIncheck;
	}
	public void setIncheck(boolean incheck) {
		isIncheck = incheck;
	}

	public static void setCurrentBoardAtPlayer(Item[][] currentBoardAtPlayer) {Player.currentBoardAtPlayer = currentBoardAtPlayer;}
}
