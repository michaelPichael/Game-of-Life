import java.util.Scanner;

public class conwaysLife {
  //set size of game matrix
  static int depth = 10;
  static String printString = "";
  static boolean willBeAlive = false;
  static int numNeighbors = 0;
  static int nX = 0;
  static int nY = 0;
  //Main process
  public static void main (String[] args){
    //Create matrix of cells for play
    cell[][] cellMatrix = new cell[depth][depth];
    boolean status;
    for (int i = 0; i < cellMatrix.length; i++) {
	  for (int j = 0; j < cellMatrix[i].length; j++) {
        if (Math.random() > 0.5) {
          status = true;
        } else {
          status = false;
        }
		cellMatrix[i][j] = new cell();
        cellMatrix[i][j].setAlive(status);
		if (cellMatrix[i][j].getAlive()) {
		  printString = printString.concat("O");
		} else {
		  printString = printString.concat(" ");
		}
	  }
	  System.out.println(printString);
	  printString = "";
	}
    //play game
	while(true) {
		/* During the first loop through the matrix we check to see which cells
		will be alive during the next cycle and then set a temporary state 
		variable to the next state for loading later */
	    for (int x = 0; x < cellMatrix.length; x++) {
		  for (int y = 0; y < cellMatrix[x].length; y++) {
			//Check each neighboring cell to determine if cell lives or dies
			for (int i = 0; i < 3; i++) {
			  nX = x + (i - 1);	
			  for (int j = 0; j < 3; j++) {
				nY = y + (j - 1);
			    if (((nX >= 0) && (nX < depth)) && ((nY >= 0) && (nY < depth)) && cellMatrix[nX][nY].getAlive() && (nX != x) && (nY != y)) {
				  numNeighbors++;	
				}
			  }
			}
			/* Check conditions of game:
			1: Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
            2: Any live cell with two or three live neighbours lives on to the next generation.
            3: Any live cell with more than three live neighbours dies, as if by overpopulation.
            4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
			*/
			if (cellMatrix[x][y].getAlive() && (numNeighbors==2 || numNeighbors==3)) {
			  willBeAlive = true;	
			} else if (!cellMatrix[x][y].getAlive() && numNeighbors==3) {
			  willBeAlive = true;
			} else {
			  willBeAlive = false;	
			}
			//Set up to print next state to screen
			if (willBeAlive) {
			  printString = printString.concat("O");
			} else {
			  printString = printString.concat(" ");
		    }
			cellMatrix[x][y].setTempAlive(willBeAlive);
		  }
		  System.out.println(printString);
		  printString = "";
	    }
		/* Now that all cells have been checked and their next state has been loaded
		we can set the next state of each cell */
		for (int x = 0; x < cellMatrix.length; x++) {
		  for (int y = 0; y < cellMatrix[x].length; y++) {
		    cellMatrix[x][y].setAlive(cellMatrix[x][y].getTempAlive());
		  }
		}
	  Scanner reader = new Scanner(System.in);  // Reading from System.in
      System.out.println("Enter a number: ");
      int z = reader.nextInt(); // Scans the next token of the input as an int.
      //once finished
      reader.close();
	  if (z == 0) {
	    break;
	  }
	  
	  }
	  //no return
    }
  }

class cell {
  //properties of each cell
  private boolean alive = false;
  private boolean tempAlive = false;
  
  //Get requests for properties of cell
  public boolean getAlive() {
    return alive;
  }
  public boolean getTempAlive() {
	return tempAlive;
  }
	
  //Set requests for properties of cell    
  public void setAlive(boolean status) {
    //set alive status
    alive = status;
  }
  public void setTempAlive(boolean status) {
	tempAlive = status;
  }
}