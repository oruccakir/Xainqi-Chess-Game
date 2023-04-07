
import java.util.StringTokenizer;

public class Item extends AbstractItem{

	private String type;
	private char notation;
	private int row_x;
	private int column_y;
	private float pointValue;
	private int index;
	private int itemIndex;
	private String position;

	public Item (){

	}

	public Item(String type,int row_x, int column_y, float pointValue,int index,int itemIndex) {
		this.type = type;
		this.row_x = row_x;
		this.column_y = column_y;
		this.pointValue = pointValue;
		this.setPosition(ItemInterface.convertFromIndexToString(row_x,column_y));
		this.index = index;
		this.itemIndex = itemIndex;
	}

	public Item(Item item){
		this.type = item.type;
		this.notation = item.notation;
		this.row_x = item.getRow_x();
		this.column_y = item.getColumn_y();
		this.pointValue = item.pointValue;
		this.index = item.index;
		this.itemIndex = item.itemIndex;
		this.setPosition(item.getPosition());
	}

	public Item clone(){
		return new Item(this);
	}

	@Override
	public void move(String destination,Board board) {

		int result = ItemInterface.convertFromStringToIndex(destination);
		int to_x = result / 10, to_y = result % 10;
		int from_x = this.getRow_x(), from_y = this.getColumn_y();

			this.setRow_x(to_x);
			this.setColumn_y(to_y);
			this.setPosition(destination);

			board.items[this.itemIndex].setRow_x(to_x);
			board.items[this.itemIndex].setColumn_y(to_y);
			board.items[this.itemIndex].setPosition(destination);

	}


	@Override
	public boolean isMoveable(String destination) {

		boolean isMoveable = false;

		boolean isInrange = ItemInterface.generalIsInRange(destination) && this.isInRange(destination);

		if (isInrange){

			int result = ItemInterface.convertFromStringToIndex(destination);
			int destinationRow_x = result / 10;
			int destinationColumn_y = result % 10;

			if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isMoveable = true;

			else{

				if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isMoveable = false;

				else isMoveable = true;

			}

		}

		else isMoveable = false;

		return isMoveable;

	}

	public  boolean isInRange(String destination){

		return false;
	}

	public String toString(){

		return getPosition()+","+type+","+notation+","+row_x+","+column_y+","+index+","+itemIndex+","+pointValue+",";

	}


	public Item loadInfo(String wholeInformation){

		StringTokenizer tokenizer = new StringTokenizer(wholeInformation,",");

		setPosition(tokenizer.nextToken());
		setType(tokenizer.nextToken());
		setNotation(tokenizer.nextToken().charAt(0));
		setRow_x(Integer.parseInt(tokenizer.nextToken()));
		setColumn_y(Integer.parseInt(tokenizer.nextToken()));
		setIndex(Integer.parseInt(tokenizer.nextToken()));
		setItemIndex(Integer.parseInt(tokenizer.nextToken()));
		setPointValue(Float.parseFloat(tokenizer.nextToken()));

		if(this instanceof Soldier)
			((Soldier) this).setOverTheRiver(Boolean.parseBoolean(tokenizer.nextToken()));

		else if(this instanceof Advisor)
			((Advisor) this).setWhichAdvisor(tokenizer.nextToken());

		else if(this instanceof General){
			((General) this).setCheckMated(Boolean.parseBoolean(tokenizer.nextToken()));
			((General) this).setInCheck(Boolean.parseBoolean(tokenizer.nextToken()));
			((General) this).setOverAgainstOtherGeneral(Boolean.parseBoolean(tokenizer.nextToken()));
		}

		return  this;

	}

	@Override
	public String getPosition() {
		return this.position;
	}
	public void setPosition(String position){
		this.position = position;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getRow_x() {
		return row_x;
	}
	public void setRow_x(int row_x) {
		this.row_x = row_x;
	}
	public float getPointValue() {
		return pointValue;
	}
	public void setPointValue(float pointValue) {
		this.pointValue = pointValue;
	}
	public int getColumn_y() {
		return column_y;
	}
	public void setColumn_y(int column_y) {
		this.column_y = column_y;
	}
	public char getNotation() {
		return notation;
	}
	public void setNotation(char notation) {
		this.notation = notation;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getItemIndex() {
		return itemIndex;
	}
	public void setItemIndex(int itemIndex) {
		this.itemIndex = itemIndex;
	}

}
