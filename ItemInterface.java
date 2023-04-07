
import java.io.Serializable;

public interface ItemInterface extends Serializable {
	
	void move(String destination,Board board);

	public boolean isInRange(String destination);

	public boolean isMoveable(String destination);

	
	public static boolean generalIsInRange(String destination){

		boolean isInRange = false;
		int result = ItemInterface.convertFromStringToIndex(destination);
		int destinationRow_x = result / 10;
		int destinationColumn_y = result % 10;

		if(destinationRow_x >=1 && destinationRow_x<=10 && destinationColumn_y >=1 && destinationColumn_y<=9) isInRange = true;

		return isInRange;
	}

	public static int convertFromStringToIndex(String destination){

		int row_x = 0;
		int column_y =0;

		row_x = (int) destination.charAt(0)-96;

		String last ="";

		last+=destination.charAt(1);

		column_y = Integer.parseInt(last);

		return 10 * row_x + column_y;

	}

	public static String  convertFromIndexToString(int row_x, int column_y){

		String str ="";

		char first = (char) (row_x+96);

		str+=first;

		str+=column_y;

		return str;

	}









}
