package week1;

public class Zombie {

    private int direction;
    private int location;

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    public Zombie(int location, int direction){
        this.location = location;
        this.direction = direction;
    }

    public void changeDirection(){
        if(getDirection() == LEFT){
            this.direction = RIGHT;
        }else{ // if direction is RIGHT
            this.direction = LEFT;
        }
    }

    public int getDirection(){
        return this.direction;
    }

    public void changeLocation(){
        if(getDirection() == LEFT){
            this.location--;
        }else{ // if direction is RIGHT
            this.location++;
        }
    }

    public int getLocation(){
        return this.location;
    }

    public int getNextLocation(){
        if(this.getDirection() == LEFT){
            return this.getLocation() - 1;

        }else{ // if direction is RIGHT
            return this.getLocation() + 1;
        }
    }
}
