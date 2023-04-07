
public class Soldier extends Item{

    private boolean isOverTheRiver;

    public Soldier(){

    }

    public Soldier(String type,int row_x, int column_y, float pointValue, boolean isOverTheRiver,int index,int itemIndex) {
        super(type, row_x, column_y,pointValue,index,itemIndex);
        this.isOverTheRiver = isOverTheRiver;
        if(type.equals("red")) setNotation('p');
        else setNotation('P');
    }

    public Soldier(Soldier soldier){
        super(soldier);
        this.isOverTheRiver = soldier.isOverTheRiver;
    }

    public Soldier clone(){
        return new Soldier(this);
    }

    public boolean isOverTheRiverControl(){

        if(this.getType().equals("red")){
            if(this.getRow_x() >=6){
                return true;
            }
        }
        else{
            if(this.getRow_x()<=5){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;

        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if(!isOverTheRiver){

            if(getType().equals("red")){

                if(getColumn_y() == destinationColumn_y && getRow_x() + 1 == destinationRow_x) isInRange = true;

            }

            else{

                if(getColumn_y() == destinationColumn_y && getRow_x() - 1 == destinationRow_x) isInRange = true;

            }

        }

        else{

            if(getType().equals("red")){

                if(getColumn_y() == destinationColumn_y && getRow_x() + 1 == destinationRow_x && destinationRow_x<=10) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()+1 && destinationColumn_y >=1 && destinationColumn_y <=9) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()-1 && destinationColumn_y >=1 && destinationColumn_y <=9) isInRange = true;

            }

            else{

                if(getColumn_y() == destinationColumn_y && getRow_x() - 1 == destinationRow_x && destinationRow_x >=1) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()+1 && destinationColumn_y >=1 && destinationColumn_y <=9) isInRange = true;
                else if(getRow_x() == destinationRow_x && destinationColumn_y == getColumn_y()-1 && destinationColumn_y >=1 && destinationColumn_y <=9) isInRange = true;

            }

        }

        return isInRange;
    }

    @Override
    public String toString() {
        return super.toString()+isOverTheRiver+",";
    }


    public boolean getIsOverTheRiver() {
        return isOverTheRiver;
    }

    public void setOverTheRiver(boolean overTheRiver) {
        isOverTheRiver = overTheRiver;
    }
}
