public class Board {
  static final int EMPTY = 0;
  static final int ISLAND = 1;
  static final int WRECK = 2;
  static final int SHIP = 3;
  static final int PIRATES = 4;
  static final int WIN = 5;

  static final int dimRow = 20;
  static final int dimCol = 20;

  int[][] board;

  Board() {
    this.board = new int[dimRow][dimCol];
    //portal hardcoded to always be on top right corner
    this.board[0][dimCol-1] = this.WIN;
  }

  public int getDimRow() {
    return dimRow;
  }

  public int getDimCol() {
    return dimCol;
  }

  boolean setShip(int row, int col) {
    if(this.board[row][col] == this.WRECK ||
      this.board[row][col] == this.ISLAND ||
      this.board[row][col] == this.PIRATES){
      this.board[row][col] = this.EMPTY;
      return true;
    }
    this.board[row][col] = this.SHIP;
    return false;
  }

  boolean setPirate(int row, int col) {
    if(this.board[row][col] == this.ISLAND){
      this.board[row][col] = this.EMPTY;
      return true;
    } else if (this.board[row][col] == this.PIRATES ||
            this.board[row][col] == this.WRECK){
      this.board[row][col] = this.WRECK;
      return true;
    } else if (this.board[row][col] == this.SHIP){
      this.board[row][col] = this.WRECK;
      return true;
    }
    this.board[row][col] = this.PIRATES;
    return false;
  }
  void setWreck(int row, int col) { this.board[row][col] = this.WRECK; }
  void setIsland(int row, int col) {
    this.board[row][col] = this.ISLAND;
  }

  void removeShip(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }

  void removePirate(int row, int col) {
    this.board[row][col] = this.EMPTY;
  }
  //to check if position of pirate during initialisation is valid
  //explanation: don't put another pirate over island or another pirate
  boolean checkInitPirates(int row, int col){
    if(this.board[row][col] == 0)
      return true;
    return false;
  }
  void toConsole() {
    for (int i = 0; i < this.dimRow; i++) {
      for (int j = 0; j < this.dimCol; j++) {
        System.out.print(this.board[i][j] + " ");
      }
      System.out.println();
    }
    System.out.println();
  }

}