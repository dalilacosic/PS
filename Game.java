import java.util.Random;
public class Game {
  private int numOfPirates = 1;
  private int numOfIslands = 2;
  private int level = 1;
  private boolean gameOver = false;
  Ship ship;
  Board board;
  Pirate[] pirates;

  //getters & setters
  int getLevel(){ return this.level; }
  void setLevel(int lvl){ this.level = lvl; }
  void setGameOver(boolean gameOver) { this.gameOver = gameOver; }


  public void init() {
    board = new Board();
    
    //postavimo igraca u dole lijevo
    ship = new Ship(17, 2);
    
    //postavimo random pirate
    int randomRow;
    int randomCol;
    Random randomPiratePosition = new Random();
    
    System.out.println("Broj pirata: "+this.numOfPirates);
    System.out.println("Broj otoka: "+this.numOfIslands);
   
    board.setShip(ship.getRow(), ship.getCol());
    int i = 0;
    pirates = new Pirate[this.numOfPirates];
    while( i < this.numOfPirates){
      //postavljamo radnom pirate, ali da budu bar 5 mjesta udaljeni od igraca
      randomRow = randomPiratePosition.nextInt(12);
      randomCol = randomPiratePosition.nextInt(15)+5;
      
      if(board.checkInitPirates(randomRow, randomCol)) {
        pirates[i] = new Pirate(randomRow, randomCol, Pirate.UP);  
        board.setPirate(randomRow, randomCol);
        i++;
      }
    }
    
    //na slican nacin postavljamo otoke
    i=0;
    while( i < this.numOfIslands){
      //postavljamo random otoke, ali da budu bar 5 mjesta udaljeni od igraca
      randomRow = randomPiratePosition.nextInt(12);
      randomCol = randomPiratePosition.nextInt(15)+5;
      
      if(board.checkInitPirates(randomRow, randomCol)) {
        board.setIsland(randomRow, randomCol);
        i++;
      }
    }
    System.out.println("Game: ");
    board.toConsole();

  }
  
  //pomjeranje brodova
  public void moveShipMRandom() {
    int dir = (int) (Math.random() * 4 + 1);
    this.ship.setDirection(dir);
    moveShip();
  }

  public void moveShip() {
    int direction = ship.getDirection();

    if (checkMove(ship.getRow(), ship.getCol(), direction)) {
      board.removeShip(ship.getRow(), ship.getCol());
      ship.move();
      
      if (board.setShip(ship.getRow(), ship.getCol()) == true) {
        this.gameOver = true;
      }
      System.out.println("Game over: "+this.gameOver);
    }
  }

  //pomjeranje neprijatelja
  public void movePirates() {

    AI();
    for (int i = 0; i < this.numOfPirates; i++) {
      if(pirates[i].getDestroyed() == false){
        board.removePirate(pirates[i].getRow(), pirates[i].getCol());
        pirates[i].move();
        if( board.setPirate(pirates[i].getRow(), pirates[i].getCol()) ){
          pirates[i].setDestroyed(true);
          if(board.board[this.ship.getRow()][this.ship.getCol()] != this.board.SHIP)
            this.gameOver = true;
        }
      }
    }
  }
  private void AI() {
    for (int i = 0; i < this.numOfPirates; i++) {
        pirates[i].newPosition(this.ship.getCol(), this.ship.getRow());
    }
  }

  //ispravnost poteza
  boolean checkMove(int row, int col, int dir) {
    int dimRow = board.getDimRow();
    int dimCol = board.getDimCol();

    if (row == 0 && dir == ship.UP) {
      return false;
    }
    if(row == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if(row == 0 && dir == ship.RIGHT_UP) {
      return false;
    }
    if (col == 0 && dir == ship.LEFT) {
      return false;
    }
    if(col == 0 && dir == ship.LEFT_UP) {
      return false;
    }
    if(col == 0 && dir == ship.LEFT_DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.LEFT_DOWN) {
      return false;
    }
    if (row == dimRow - 1 && dir == ship.RIGHT_DOWN) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT_UP) {
      return false;
    }
    if (col == dimCol - 1 && dir == ship.RIGHT_DOWN) {
      return false;
    }
    return true;
  }

  //da li je kraj igre
  boolean end() {
      if (this.gameOver == true)
        return true;
    return false;
  }
  
  //provjerava da li je igrac uspio stici do cilja, ako jeste ide na sljedeci level
  boolean nextLevel() {
    return this.ship.getRow() == 0 && this.ship.getCol() == this.board.getDimCol() - 1;
  }
  
  
  void restart(){
    this.level = 1;
    this.numOfIslands = 1;
    this.numOfPirates = 1;
  }
  
  
  void moveToNextLevel(){
    this.level++;
    this.numOfIslands++;
    this.numOfPirates = this.numOfIslands+1;
  }
}