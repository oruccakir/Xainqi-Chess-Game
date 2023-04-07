
public class General extends Item{

    private boolean isCheckMated;
    private boolean isInCheck;
    private boolean isOverAgainstOtherGeneral;

    public General(){

    }

    public General(String type,int row_x, int column_y,float pointValue, boolean isCheckMated, boolean isInCheck,boolean isOverAgainstOtherGeneral,int index,int itemIndex) {
        super(type,row_x, column_y,pointValue,index,itemIndex);
        this.isInCheck = isInCheck;
        this.isCheckMated = isCheckMated;
        this.isOverAgainstOtherGeneral = isOverAgainstOtherGeneral;
        if(type.equals("red")) setNotation('ş');
        else setNotation('Ş');
    }

    public General(General general){
        super(general);
        this.isCheckMated = general.isCheckMated;
        this.isInCheck = general.isInCheck;
        this.isOverAgainstOtherGeneral = general.isOverAgainstOtherGeneral;
    }

    public General clone(){
        return new General(this);
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;

        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if(getType().equals("red")){

            if(destinationRow_x >=1 && destinationRow_x <=3 && destinationColumn_y >=4 && destinationColumn_y <=6){

                if(getColumn_y() == destinationColumn_y && getRow_x() + 1 == destinationRow_x) isInRange = true;
                else if(getColumn_y() == destinationColumn_y && getRow_x() - 1 == destinationRow_x) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()+1) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()-1) isInRange = true;


            }
            else isInRange = false;

        }

        else{

            if(destinationRow_x >=8 && destinationRow_x <=10 && destinationColumn_y >=4 && destinationColumn_y <=6){

                if(getColumn_y() == destinationColumn_y && getRow_x() - 1 == destinationRow_x ) isInRange = true;
                else if(getColumn_y() == destinationColumn_y && getRow_x() + 1 == destinationRow_x) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()+1 ) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()-1 ) isInRange = true;


            }
            else isInRange = false;

        }
        return isInRange;
    }

    @Override
    public boolean isMoveable(String destination) {

        boolean isMoveable = false;

        boolean firstControl = super.isMoveable(destination);


        if(firstControl){

            int result = ItemInterface.convertFromStringToIndex(destination);
            int destinationRow_x = result / 10;
            int destinationColumn_y = result % 10;

            boolean isTherePieceOnThePath = false;

            if(getType().equals("red")){

                for(int i = destinationRow_x;  i<=10 && !isTherePieceOnThePath; i++){

                    if(Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][destinationColumn_y].getNotation() !='Ş')
                        isTherePieceOnThePath = true;
                    else if(Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][destinationColumn_y].getNotation() =='Ş')
                        break;

                }

                if(isTherePieceOnThePath) isMoveable = true;

                else isMoveable = false;

            }
            else{

                for(int i = destinationRow_x;  i>=1 && !isTherePieceOnThePath; i--){

                    if(Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][destinationColumn_y].getNotation() !='ş')
                        isTherePieceOnThePath = true;
                    else if(Player.getCurrentBoardAtPlayer()[i][destinationColumn_y] != null && Player.getCurrentBoardAtPlayer()[i][destinationColumn_y].getNotation() =='ş')
                        break;

                }

                if(isTherePieceOnThePath) isMoveable = true;

                else isMoveable = false;

            }

        }

        else isMoveable = false;

        return isMoveable;

    }

    @Override
    public String toString() {
        return super.toString()+isCheckMated+","+isInCheck+","+isOverAgainstOtherGeneral+",";
    }


    public boolean isCheckMated() {
        return isCheckMated;
    }
    public void setCheckMated(boolean checkMated) {
        isCheckMated = checkMated;
    }
    public boolean isOverAgainstOtherGeneral() {
        return isOverAgainstOtherGeneral;
    }
    public void setOverAgainstOtherGeneral(boolean overAgainstOtherGeneral) {isOverAgainstOtherGeneral = overAgainstOtherGeneral;}
    public boolean getInCheck() {
        return isInCheck;
    }
    public void setInCheck(boolean inCheck) {
        isInCheck = inCheck;
    }


}
