public class Chariot extends Item{

    public Chariot(){

    }

    public Chariot(String type, int row_x, int column_y, float pointValue,int index,int itemIndex) {

        super(type, row_x, column_y,pointValue,index,itemIndex);

        if(type.equals("red")) setNotation('k');

        else setNotation('K');
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;
        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if(destinationColumn_y >=1 && destinationColumn_y <=9 && destinationRow_x >=1 && destinationRow_x <=10){

            if((getColumn_y() == destinationColumn_y) || (getRow_x() == destinationRow_x)) isInRange = true;

        }
        else isInRange = false;

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

            if(getRow_x() < destinationRow_x){

                if(getColumn_y() == destinationColumn_y){

                    boolean isTherePieceOnThePath = false;

                    for(int i = getRow_x()+1; i<destinationRow_x && !isTherePieceOnThePath; i++){
                        if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) isTherePieceOnThePath = true;
                    }

                    if(!isTherePieceOnThePath){
                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                        else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                        else isTherePieceOnThePath = false;
                    }

                    isMoveable = !isTherePieceOnThePath;

                }


            }

            else if(getRow_x() > destinationRow_x){

                if(getColumn_y() == destinationColumn_y){

                    boolean isTherePieceOnThePath = false;

                    for(int i = getRow_x()-1; i>destinationRow_x && !isTherePieceOnThePath; i--){
                        if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) isTherePieceOnThePath = true;
                    }

                    if(!isTherePieceOnThePath){
                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                        else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                        else isTherePieceOnThePath = false;
                    }

                    isMoveable = !isTherePieceOnThePath;

                }

            }

            else if(getColumn_y() < destinationColumn_y){

                if(getRow_x() == destinationRow_x){

                    boolean isTherePieceOnThePath = false;

                    for(int i = getColumn_y()+1 ; i< destinationColumn_y && !isTherePieceOnThePath; i++){
                        if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) isTherePieceOnThePath = true;
                    }

                    if(!isTherePieceOnThePath){

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                        else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                        else isTherePieceOnThePath = false;
                    }

                    isMoveable = !isTherePieceOnThePath;
                }

            }

            else if (getColumn_y() > destinationColumn_y){

                if(getRow_x() == destinationRow_x){

                    boolean isTherePieceOnThePath = false;

                    for(int i = getColumn_y()-1 ; i> destinationColumn_y && !isTherePieceOnThePath; i--){
                        if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) isTherePieceOnThePath = true;
                    }

                    if(!isTherePieceOnThePath){

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                        else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                        else isTherePieceOnThePath = false;
                    }

                    isMoveable = !isTherePieceOnThePath;
                }

            }
        }
        else isMoveable = false;

        return isMoveable;
    }
}
