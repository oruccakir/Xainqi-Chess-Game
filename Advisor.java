

public class Advisor extends Item{

    private String whichAdvisor;

    public Advisor(){

    }
    public Advisor(String type, int row_x, int column_y, float pointValue, String whichAdvisor,int index,int itemIndex) {

        super(type, row_x, column_y, pointValue,index,itemIndex);

        this.whichAdvisor = whichAdvisor;

        if(type.equals("red")) setNotation('v');

        else setNotation('V');

    }

    public Advisor(Advisor advisor){
        super(advisor);
        this.whichAdvisor = advisor.whichAdvisor;
    }

    public Advisor clone(){
        return new Advisor(this);
    }

    @Override
    public boolean isInRange(String destination) {

        boolean isInRange = false;
        int result = ItemInterface.convertFromStringToIndex(destination);
        int destinationRow_x = result / 10;
        int destinationColumn_y = result % 10;

        if(getType().equals("red")){

            if(destinationRow_x >=1 && destinationRow_x <=3 && destinationColumn_y >=4 && destinationColumn_y <=6){

                if(getWhichAdvisor().equals("leftAdvisor")){

                    if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() + 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;

                }
                else{

                    if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() + 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;

                }

            }

            else isInRange=false;

        }

        else{

            if(destinationRow_x >=8 && destinationRow_x <=10 && destinationColumn_y >=4 && destinationColumn_y <=6){

                if(getWhichAdvisor().equals("leftAdvisor")){

                    if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() +1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;
                }
                else{

                    if(getRow_x() - 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() +1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() - 1 == destinationRow_x && getColumn_y() - 1 == destinationColumn_y) isInRange = true;
                    else if(getRow_x() + 1 == destinationRow_x && getColumn_y() + 1 == destinationColumn_y) isInRange = true;

                }

            }
            else isInRange = false;

        }

        return isInRange;

    }

    @Override
    public String toString() {
        return super.toString()+whichAdvisor+",";
    }

    public String getWhichAdvisor() {
        return whichAdvisor;
    }
    public void setWhichAdvisor(String whichAdvisor) {
        this.whichAdvisor = whichAdvisor;
    }

}
