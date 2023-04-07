public class Cannon extends Item{

    public Cannon(String type, int row_x, int column_y,float pointValue,int index,int itemIndex) {
        super(type, row_x, column_y, pointValue,index,itemIndex);
        if(type.equals("red")) setNotation('t');
        else setNotation('T');
    }

    public Cannon(){

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

        boolean isInRange = ItemInterface.generalIsInRange(destination) && this.isInRange(destination);

        if(isInRange){

            int result = ItemInterface.convertFromStringToIndex(destination);
            int destinationRow_x = result / 10;
            int destinationColumn_y = result % 10;


                if(getRow_x() < destinationRow_x){

                    if(getColumn_y() == destinationColumn_y){

                        boolean isTherePieceOnThePath = false;

                        int numberOfPieces = 0;

                        for(int i = getRow_x()+1; i<destinationRow_x && !isTherePieceOnThePath; i++){
                            if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) isTherePieceOnThePath = true;
                        }

                        for(int i = getRow_x()+1; i<destinationRow_x; i++){
                            if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) numberOfPieces++;
                        }

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] != null)
                           if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces == 1) return true;
                           else if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces != 1) return false;

                        else if(!isTherePieceOnThePath){

                            if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                            else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                            else if(numberOfPieces ==0 ) return false;

                        }

                        isMoveable = !isTherePieceOnThePath;

                    }


                }

                else if(getRow_x() > destinationRow_x){

                    if(getColumn_y() == destinationColumn_y){

                        boolean isTherePieceOnThePath = false;

                        int numberOfPieces = 0;

                        for(int i = getRow_x()-1; i>destinationRow_x && !isTherePieceOnThePath; i--){
                            if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) isTherePieceOnThePath = true;
                        }

                        for(int i = getRow_x()-1; i>destinationRow_x; i--){
                            if(Player.getCurrentBoardAtPlayer()[i][getColumn_y()] != null) numberOfPieces++;
                        }

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] != null)
                            if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces == 1) return true;
                            else if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces != 1) return false;


                        else if(!isTherePieceOnThePath){

                            if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                            else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                            else if(numberOfPieces ==0 ) return false;
                        }

                        isMoveable = !isTherePieceOnThePath;

                    }

                }

                else if(getColumn_y() < destinationColumn_y){

                    if(getRow_x() == destinationRow_x){

                        boolean isTherePieceOnThePath = false;

                        int numberOfPieces = 0;

                        for(int i = getColumn_y()+1 ; i< destinationColumn_y && !isTherePieceOnThePath; i++){
                            if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) isTherePieceOnThePath = true;
                        }

                        for(int i = getColumn_y()+1 ; i< destinationColumn_y; i++){
                            if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) numberOfPieces++;
                        }

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] != null)
                            if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces == 1) return true;
                            else if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces != 1) return false;

                        else if(!isTherePieceOnThePath){

                            if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                            else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                            else if(numberOfPieces ==0 ) return false;
                        }

                        isMoveable = !isTherePieceOnThePath;
                    }

                }

                else if (getColumn_y() > destinationColumn_y){


                    if(getRow_x() == destinationRow_x){

                        boolean isTherePieceOnThePath = false;

                        int numberOfPieces = 0;

                        for(int i = getColumn_y()-1 ; i> destinationColumn_y && !isTherePieceOnThePath; i--){
                            if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) isTherePieceOnThePath = true;
                        }

                        for(int i = getColumn_y()-1 ; i> destinationColumn_y; i--){
                            if(Player.getCurrentBoardAtPlayer()[getRow_x()][i] != null) numberOfPieces++;
                        }

                        if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] != null)
                            if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces == 1) return true;
                            else if(!Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType()) && numberOfPieces != 1) return false;

                        else if(!isTherePieceOnThePath){

                            if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y] == null) isTherePieceOnThePath = false;
                            else if(Player.getCurrentBoardAtPlayer()[destinationRow_x][destinationColumn_y].getType().equals(this.getType())) isTherePieceOnThePath = true;
                            else if(numberOfPieces ==0 ) return false;
                        }

                        isMoveable = !isTherePieceOnThePath;
                    }

                }
        }
        else isMoveable = false;



        return isMoveable;
    }

}
