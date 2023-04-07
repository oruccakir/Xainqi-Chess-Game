public class Elephant extends Item{

    public Elephant(){

    }

    public Elephant(String type,int row_x, int column_y,float pointValue,int index,int itemIndex) {
        super(type, row_x, column_y, pointValue,index,itemIndex);
        if(type.equals("red")) setNotation('f');
        else setNotation('F');
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;
        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if (getType().equals("red")){

            if(destinationRow_x >=1 && destinationRow_x <=5 && destinationColumn_y >=1 && destinationColumn_y <=9 ){

                if(getRow_x() + 2 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() + 2 == destinationRow_x && getColumn_y() -2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 2 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 2 == destinationRow_x && getColumn_y() -2 == destinationColumn_y) isInRange = true;

            }
            else isInRange = false;

        }

        else{

            if(destinationRow_x >=6 && destinationRow_x <=10 && destinationColumn_y >=1 && destinationColumn_y <=9 ){

                if(getRow_x() + 2 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() + 2 == destinationRow_x && getColumn_y() -2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 2 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 2 == destinationRow_x && getColumn_y() -2 == destinationColumn_y) isInRange = true;

            }
            else isInRange = false;

        }
        return isInRange;
    }



    @Override
    public boolean isMoveable(String destination) {

        boolean isMoveable = false;

        boolean isInrange = ItemInterface.generalIsInRange(destination) && this.isInRange(destination);

        if(isInrange){

            int result = ItemInterface.convertFromStringToIndex(destination);
            int destinationRow_x = result / 10;
            int destinationColumn_y = result % 10;

            if(destinationRow_x > getRow_x() && destinationColumn_y > getColumn_y()){

                if(Player.getCurrentBoardAtPlayer()[destinationRow_x-1][destinationColumn_y-1] == null) isMoveable = true;

            }
            else if(destinationRow_x > getRow_x() && destinationColumn_y < getColumn_y()){

                if(Player.getCurrentBoardAtPlayer()[destinationRow_x-1][destinationColumn_y+1] == null) isMoveable = true;

            }
            else if(destinationRow_x < getRow_x() && destinationColumn_y > getColumn_y()){

                if(Player.getCurrentBoardAtPlayer()[destinationRow_x+1][destinationColumn_y-1] == null) isMoveable = true;

            }
            else if(destinationRow_x < getRow_x() && destinationColumn_y < getColumn_y()){

                if(Player.getCurrentBoardAtPlayer()[destinationRow_x+1][destinationColumn_y+1] == null) isMoveable = true;

            }

            if(isMoveable){
                if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isMoveable = true;
                else {
                    if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isMoveable = false;
                    else isMoveable = true;
                }
            }

        }

        else isMoveable = false;
        
        return isMoveable;
    }
}
