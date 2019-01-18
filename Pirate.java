public class Pirate {
	private int row;
	private int col;
	private int direction;
	private boolean destroyed;

	//konstruktor
  Pirate(int row, int col, int dir) {
    this.row = row;
    this.col = col;
    this.direction = dir; 
    this.destroyed = false;
  }
  
  //geteri i seteri
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}


  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }
  
  public boolean getDestroyed() { return this.destroyed; }

  //smjer kretanja
	static final int UP = 8;
	static final int RIGHT_UP = 9;
	static final int RIGHT = 6;
	static final int RIGHT_DOWN = 3;
	static final int DOWN = 2;
	static final int LEFT_DOWN = 1;
	static final int LEFT = 4;
	static final int LEFT_UP = 7;

	//pomjeri pirate na osnovu zadanog smjera
	void move() {
		if(this.direction == this.LEFT_UP) {
			this.row--;
			this.col--;
		}
		if (this.direction == this.UP) {
			this.row --;
		}
		if(this.direction == this.RIGHT_UP) {
			this.row--;
			this.col++;
		}
		if (this.direction == this.RIGHT) {
			this.col ++;
		}
		if (this.direction == this.LEFT) {
			this.col --;
		}
		if(this.direction == this.LEFT_DOWN) {
			this.row++;
			this.col--;
		}
		if (this.direction == this.DOWN) {
			this.row ++;
		}
		if(this.direction == this.RIGHT_DOWN) {
			this.row++;
			this.col++;
		}
	}

	//promjeni pravac
	void changeDirection(int dir) {
		this.direction = dir;
	}
	
	//manhattanova udaljenost
	private int returnDistance(int xP, int yP, int yD, int xD) {
		return Math.abs(xP - xD) + Math.abs(yP - yD);
	}

//nova pozicija pirata min od 8 mogucih koraka
	void newPosition(int x1, int y1) {
		int[] niz = new int[8]; // up, right, down, left
		niz[0] = returnDistance(x1, y1, this.row - 1, this.col);
		niz[1] = returnDistance(x1, y1, this.row-1, this.col + 1);
		niz[2] = returnDistance(x1, y1, this.row, this.col+1);
		niz[3] = returnDistance(x1, y1, this.row+1, this.col + 1);
		niz[4] = returnDistance(x1, y1, this.row+1, this.col);
		niz[5] = returnDistance(x1, y1, this.row+1, this.col - 1);
		niz[6] = returnDistance(x1, y1, this.row, this.col - 1);
		niz[7] = returnDistance(x1, y1, this.row-1, this.col - 1);
		int min = niz[0];

		for (int i = 0; i < 8; i++) {
			if (niz[i] < min) {
				min = niz[i];
			}
		}
		

    int random = randomIndex(niz, min);
		if (random == 0) {
			this.direction = this.UP;
		} else if (random == 1) {
			this.direction = this.RIGHT_UP;
		} else if (random == 2) {
			this.direction = this.RIGHT;
		} else if(random == 3) {
			this.direction = this.RIGHT_DOWN;
		} else if(random == 4) {
			this.direction = this.DOWN;
		} else if(random == 5) {
			this.direction = this.LEFT_DOWN;
		} else if(random == 6) {
			this.direction = this.LEFT;
		} else {
			this.direction = this.LEFT_UP;
		}
	}
  
	//biramo random index
  private int randomIndex(int[] niz, int min) {
    int ran = (int) (Math.random() * 8);
    while (niz[ran] != min) {
      ran = (int) (Math.random() * 8);
    }
    return (Math.abs(ran - getRandom01())) % 8;
  }

  
  int getRandom01() {
    if (Math.random() < 0.75) {
      return 0;
    }
    return 1;
  }
}