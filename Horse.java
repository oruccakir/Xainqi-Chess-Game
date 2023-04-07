public class Horse extends Item{

    public Horse(){

    }
    public Horse(String type, int row_x, int column_y,float pointValue,int index,int itemIndex) {
        super(type, row_x, column_y,pointValue,index,itemIndex);
        if(type.equals("red")) setNotation('a');
        else setNotation('A');
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;
        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if(destinationColumn_y >=1 && destinationColumn_y <=9 && destinationRow_x >=1 && destinationRow_x <=10){

                if(getRow_x() + 1 == destinationRow_x && getColumn_y() - 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 2 == destinationColumn_y) isInRange = true;
                else if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y) isInRange = true;
                else if(getColumn_y()-1 == destinationColumn_y && getRow_x()+2 == destinationRow_x) isInRange=true;
                else if(getColumn_y()-1 == destinationColumn_y && getRow_x()-2 == destinationRow_x) isInRange=true;
                else if(getColumn_y()+1 == destinationColumn_y && getRow_x()+2 == destinationRow_x) isInRange=true;
                else if(getColumn_y()+1 == destinationColumn_y && getRow_x()-2 == destinationRow_x) isInRange=true;

        }
        else isInRange = false;

        return isInRange;
    }

    @Override
    public boolean isMoveable(String destination) {

        boolean isMoveable = false;

        boolean isInRange = ItemInterface.generalIsInRange(destination) && this.isInRange(destination);

        if(isInRange){

            int result = ItemInterface.convertFromStringToIndex(destination);
            int destinationRow_x = result / 10;
            int destinationColumn_y = result % 10;

            if(getRow_x() + 1 == destinationRow_x && getColumn_y() - 2 == destinationColumn_y){
                if(Player.getCurrentBoardAtPlayer()[getRow_x()][destinationColumn_y+1] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y){
                if(Player.getCurrentBoardAtPlayer()[getRow_x()][destinationColumn_y-1] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 2 == destinationColumn_y){
                if(Player.getCurrentBoardAtPlayer()[getRow_x()][destinationColumn_y+1] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 2 == destinationColumn_y){
                if(Player.getCurrentBoardAtPlayer()[getRow_x()][destinationColumn_y-1] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getColumn_y()-1 == destinationColumn_y && getRow_x()+2 == destinationRow_x){
                if(Player.getCurrentBoardAtPlayer()[destinationRow_x-1][getColumn_y()] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getColumn_y()-1 == destinationColumn_y && getRow_x()-2 == destinationRow_x){
                if(Player.getCurrentBoardAtPlayer()[destinationRow_x+1][getColumn_y()] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getColumn_y()+1 == destinationColumn_y && getRow_x()+2 == destinationRow_x){
                if(Player.getCurrentBoardAtPlayer()[destinationRow_x-1][getColumn_y()] != null) isMoveable = false;
                else isMoveable = true;
            }
            else if(getColumn_y()+1 == destinationColumn_y && getRow_x()-2 == destinationRow_x){
                if(Player.getCurrentBoardAtPlayer()[destinationRow_x+1][getColumn_y()] != null) isMoveable = false;
                else isMoveable = true;
            }

            if(isMoveable){

                if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isMoveable = true;

                else{

                    if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isMoveable = false;
                    else isMoveable = true;

                }
            }

        }
        else isMoveable = false;

        return isMoveable;
    }
}
