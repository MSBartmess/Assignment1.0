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
    public boolean isValidLocation(ChessBoard board){
        return false;
    }
}
