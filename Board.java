
import java.io.PrintWriter;


public class Board extends AbstractBoard{


	public Board(){
		super();
	}
	@Override
	public void print() {

		int charNotation=106;

		for(int i=10; i>=1; i--){

			System.out.print((char)charNotation+"\t");

			for(int k=1; k<=9; k++){
				if(getCurrentBoard()[i][k] !=null){

					if(k !=9)
						System.out.print(getCurrentBoard()[i][k].getNotation()+"--");
					else
						System.out.print(getCurrentBoard()[i][k].getNotation());
				}

				else{

					if(k != 9)
						System.out.print("---");
					else
						System.out.print("-");

				}

			}

			charNotation--;
			System.out.println();

			if(i == 10)
				System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			else if(i == 9)
				System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			else if(i == 8 || i==7 || i==5 || i==4)
				System.out.println(" \t|  |  |  |  |  |  |  |  |");
			else if(i == 6)
				System.out.println(" \t|                       |");
			else if(i == 3)
				System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			else if(i==2)
				System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			else{
				System.out.println();
				System.out.println(" \t1--2--3--4--5--6--7--8--9");
			}

		}

	}


	public void saveInfo(PrintWriter output) {

		output.print(this);

	}

	public Board loadInfo(){

		for(int i=0; i<items.length; i++){
			String whole = Game.scan.next();
			items[i] =items[i].loadInfo(whole);
		}

		return this;
	}

	public String toString(){

		String represantation = "";

		for(int i=0; i<items.length; i++)
			represantation+=items[i].toString()+"\n";

		return represantation;
	}

}
