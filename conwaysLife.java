public class conwaysLife {
  //set size of game matrix
  static int depth = 10;
  //Main process
  public static void main (String[] args){
    //Create matrix of cells for play
    cell[][] cellMatrix = new cell[depth][depth];
    boolean status;
    int[] coord = new int[2];
    for (int i = 0; i < cellMatrix.length; i++) {
	  for (int j = 0; j < cellMatrix[i].length; j++) {
        if (Math.random() > 0.5) {
          status = true;
        } else {
          status = false;
        }
        coord[0] = i;
        coord[1] = j;
		cellMatrix[i][j] = new cell();
        cellMatrix[i][j].setAlive(status);
        cellMatrix[i][j].setXYCo(coord);
	  }
	}
    //play game
    //no return
  }
}

class cell {
  //properties of each cell
  private boolean alive = false;
  private int[] xyCo = new int[2];

  //Get requests for properties of cell
  public boolean getAlive() {
    return alive;
  }
  public int[] getXYCo() {
    return xyCo;
  }
  //Set requests for properties of cell    
  public void setAlive(boolean status) {
    //set alive status
    alive = status;
  }
  public void setXYCo(int[] coord) {
    //set xy coordinates of cell
    xyCo = coord;
  }
}