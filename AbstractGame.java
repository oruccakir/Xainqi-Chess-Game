
public abstract class AbstractGame {
	
	Board board;
	Player red;
	Player black;

	private static boolean isGameFinish = false;

	public AbstractGame(){


	}

	public AbstractGame(String playerRed, String playerBlack){

		for(int i=1; i<=10; i++)
			for(int k=1; k<=9; k++)
				Player.getCurrentBoardAtPlayer()[i][k] = null;

		red = new Player((float)0,playerRed,"red");

		black = new Player((float)0,playerBlack,"black");

		Player.makeCurrentBoardAtPlayer(red,black);

		board = new Board();

		loadTheItems(red,black,board);

		board.setCurrentBoard(Player.getCurrentBoardAtPlayer());

	}
	
	/*
	 * from pozisyonundaki taşı to pozisyonuna taşır.
	 * Eğer hareket kural dışı ise, ekrana "hatali hareket" mesajı ekrana yazılır ve oyuncunun tekrar oynaması için sırayı değiştirmez.
	 * Eğer hareket sonucu biri oyunu kazandı ise, "ŞAH MAT! X oyunu kazandı. X'in puanı: A, Y'nin puanı: B" yazar. X ve Y oyuncuların ismidir. A ve B aldıkları puanlardır.
	 * Eğer hareket sonucu pat oldu ise (şahın hiç bir yere hareket edememesi ve başka yapacak hareketinin olmaması durumu), "PAT" mesajı ekrana yazılır ve oyun sonlanır. 
	 * */
	abstract void play(String from, String to);  
	
	/*
	 * Oyunun o anki hali belirtilen dosyaya binary olarak kaydedilir.
	 * */
	abstract void save_binary(String address);  
	
	/*
	 * Oyunun o anki hali belirtilen dosyaya metin dosyası olarak kaydedilir.
	 * */
	abstract void save_text(String address);  
	
	/*
	 * Belirtilen adreste kaydedilen metin dosyasına göre oyunu yükler ve oyun kaldığı yerden devam eder. 
	 * Dosyanın doğru dosya olduğunu varsayabilirsiniz.
	 * */
	abstract void load_text(String address);  
	
	
	/*
	 * Belirtilen adreste kaydedilen binary dosyaya göre oyunu yükler ve oyun kaldığı yerden devam eder.
	 * Dosyanın doğru dosya olduğunu varsayabilirsiniz.
	 * 
	 * */
	abstract void load_binary(String address);

	public boolean isThisLegal(String from, String to){

		if(isGameFinish){
			return false;
		}

		if(from == null || to == null) return false;

		if(from.length() != 2 || to.length() !=2) {
			return false;
		}
		else{
			if(!(from.charAt(0) >= 'a' || from.charAt(0) <= 'j')){
				return false;
			}
			if(!(from.charAt(1) >= (char) 31 || from.charAt(1) <= (char) 39)) {
				return false;
			}
			if(!(to.charAt(0) >= 'a' || to.charAt(0) <= 'j')){
				return false;
			}
			if(!(to.charAt(1) >= (char) 31 || to.charAt(1) <= (char) 39)) {
				return false;
			}
		}

		int result = ItemInterface.convertFromStringToIndex(from);
		int fromFRow_x = result / 10;
		int fromColumn_y = result % 10;
		int resultTo = ItemInterface.convertFromStringToIndex(to);
		int to_x = resultTo / 10;
		int to_y = resultTo % 10;

		if(!ItemInterface.generalIsInRange(from) || !ItemInterface.generalIsInRange(to)) return false;

		if(Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y] == null) {
			return false;
		}

		if(Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y].getType().equals("red")){
			if(Player.getIsMyTurn()[0] == false) {
				return false;
			}
		}
		else{
			if(Player.getIsMyTurn()[1] == false) {
				return false;
			}

		}

		 if(Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y].isMoveable(to)){

			 Player currentPlayer = null, waitedPlayer = null;

			 if (Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y].getType().equals("red")) {

				 currentPlayer = red;
				 waitedPlayer = black;

			 } else {

				 currentPlayer = black;
				 waitedPlayer = red;

			 }

			 Item currentItem = Player.getCurrentBoardAtPlayer()[fromFRow_x][fromColumn_y];
			 Item opponentItem = Player.getCurrentBoardAtPlayer()[to_x][to_y];

			 if(to.equals(waitedPlayer.getPlayerPieces()[4].getPosition())){
				 return false;
			 }

			 boolean whichTurn = false;

			 if(currentPlayer.getPiecesColour().equals("red"))
				 whichTurn = Player.getIsMyTurn()[0];
			 else
				 whichTurn = Player.getIsMyTurn()[1];

			 return whichTurn && Game.makeTheControl(from,to,currentPlayer,currentItem,waitedPlayer,opponentItem,board);
		 }

		 return false;

	}

	public static void loadTheItems(Player red, Player black, Board board){

		int index = 0;
		for(int i=0; i<red.getPlayerPieces().length; i++){
			board.items[index] = red.getPlayerPieces()[i].clone();
			index++;
		}

		for(int i=0; i<black.getPlayerPieces().length; i++){
			board.items[index] = black.getPlayerPieces()[i].clone();
			index++;
		}

	}

	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getRed() {
		return red;
	}
	public void setRed(Player red) {
		this.red = red;
	}
	public Player getBlack() {
		return black;
	}
	public void setBlack(Player black) {
		this.black = black;
	}
	public static void setIsGameFinish(boolean isGameFinish) {
		AbstractGame.isGameFinish = isGameFinish;
	}
	public static boolean getIsGameFinish() {return isGameFinish;}

}
