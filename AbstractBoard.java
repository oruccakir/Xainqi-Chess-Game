
public abstract class AbstractBoard implements BoardInterface{

	private  static Item [][] currentBoard;
	Item [] items;

	public AbstractBoard(){
		items = new Item[32];
	}

	public static Item[][] getCurrentBoard() {
		return currentBoard;
	}
	public static void setCurrentBoard(Item[][] currentBoard) {
		AbstractBoard.currentBoard = currentBoard;
	}
	public Item[] getItems() {
		return items;
	}
	public void setItems(Item[] items) {
		this.items = items;
	}


}
