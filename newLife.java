public class conwaysLife {
	//set size of game matrix
	static int depth = 10;
	static boolean willBeAlive = false;
	static int numNeighbors = 0;
	static cell[][] cellMatrix = new cell[depth][depth];

	//Main process
 	public static void main (String[] args){
		//call to setup game
		cellMatrix = gameHelper.setupGame(cellMatrix);
		gameHelper.printToScreen(cellMatrix);
		//enter game loop
		for (int i = 0; i < 10; i++) {
			//Check if cells should live into new generation
			gameHelper.checkNeighbors(cellMatrix, depth);
			gameHelper.loadTempState(cellMatrix);
			//Set the next state of the cells
			gameHelper.loadCurrentState(cellMatrix);
			//Print the current state
			gameHelper.printToScreen(cellMatrix);
		}
	}
}

//CELL CLASS
class cell {
	//properties of each cell
	private boolean alive = false;
	private boolean tempAlive = false;
	private int numNeighbors = 0;
  
	//Get requests for properties of cell
	public boolean getAlive() {
		return alive;
	}
	public boolean getTempAlive() {
		return tempAlive;
	}
	public int getNumNeighbors() {
		return numNeighbors;
	}
	
	//Set requests for properties of cell    
	public void setAlive(boolean status) {
		//set alive status
		alive = status;
	}
	public void setTempAlive(boolean status) {
		tempAlive = status;
	}
	public void setNumNeighbors(int totalN) {
		numNeighbors = totalN;
	}
}

//HELPER CLASS
class gameHelper {
	//Static methods for setup
	public static cell[][] setupGame(cell[][] cellMatrix) {
		//Create matrix of cells for play
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
			}
		}
		return cellMatrix;
	}

	//Static methods for gameplay
	public static void checkNeighbors(cell[][] cellMatrix, int depth) {
		int nX = 0;
		int nY = 0;
		/* During the first loop through the matrix we check to see which cells
		will be alive during the next cycle and then set a temporary state 
		variable to the next state for loading later */
		int totalN = 0;
		for (int x = 0; x < cellMatrix.length; x++) {
			for (int y = 0; y < cellMatrix[x].length; y++) {
				//Check each neighboring cell to determine if cell lives or dies
				for (int i = 0; i < 3; i++) {
					nX = x + (i - 1);	
					for (int j = 0; j < 3; j++) {
						nY = y + (j - 1);
						if (((nX >= 0) && (nX < depth)) && ((nY >= 0) && (nY < depth)) && cellMatrix[nX][nY].getAlive() && (nX != x) && (nY != y)) {
							totalN++;	
						}
					}
				}
				cellMatrix[x][y].setNumNeighbors(totalN);
			}
		}
	
	}
	public static void loadTempState(cell[][] cellMatrix) {
		/* Check conditions of game:
		1: Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
		2: Any live cell with two or three live neighbours lives on to the next generation.
		3: Any live cell with more than three live neighbours dies, as if by overpopulation.
		4: Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction. */
		int numNeighbors;
		for (int x = 0; x < cellMatrix.length; x++) {
			for (int y = 0; y < cellMatrix[x].length; y++) {
				numNeighbors = cellMatrix[x][y].getNumNeighbors();
				if (cellMatrix[x][y].getAlive() && (numNeighbors==2 || numNeighbors==3)) {
					cellMatrix[x][y].setTempAlive(true);	
				} else if (!cellMatrix[x][y].getAlive() && numNeighbors==3) {
					cellMatrix[x][y].setTempAlive(true);
				} else {
					cellMatrix[x][y].setTempAlive(false);	
				}
			}
		}
	}
	public static void loadCurrentState(cell[][] cellMatrix) {
		for (int x = 0; x < cellMatrix.length; x++) {
			for (int y = 0; y < cellMatrix[x].length; y++) {
				cellMatrix[x][y].setAlive(cellMatrix[x][y].getTempAlive);
			}
		}
	}
	public static void printToScreen(cell[][] cellMatrix) {
		//Print current state to screen	
		String printString = "";
		for (int x = 0; x < cellMatrix.length; x++) {
			for (int y = 0; y < cellMatrix[x].length; y++) {
				if (cellMatrix[x][y].getAlive) {
					printString = printString.concat("O");
				} else {
					printString = printString.concat(" ");
				}
			}
			System.out.println(printString);
			printString = "";
		}
	}
}
