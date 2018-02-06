public class Location {
    private int x;
    private int y;
    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setLoc(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setLoc(Location loc){
        this.x = loc.getX();
        this.y = loc.getY();
    }

    public boolean isValidLocation(ChessBoard board){
        if(x <= board.X_RANGE_UPPER && x >= board.X_RANGE_LOWER){
            if(y <= board.Y_RANGE_UPPER && y >= board.Y_RANGE_LOWER){
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object o){
        if(o==this) {
            return true;
        }
        if(!(o instanceof Location)){
            return false;
        }
        Location loc = (Location) o;
        return this.x == loc.getX() && this.y == loc.getY();
    }
}
