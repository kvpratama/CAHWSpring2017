package week1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ZombieBridge {
    private int numOfZombie, lengthOfBridge, zombieOut, firstZombie, move;
    private List<Zombie> zombies;

    public static void main(String[] args) {
        ZombieBridge zb = new ZombieBridge();
        zb.initialize();

        while (zb.zombieOut < zb.numOfZombie){
            for (int j = 0; j < zb.zombies.size(); j++) {
                System.out.println(zb.zombies.get(j).getLocation() + " " + zb.zombies.get(j).getDirection());
            }
            zb.move();
            System.out.println("");
        }
        System.out.println(zb.firstZombie + " " + zb.move);
    }

    private void initialize() {
        Scanner scanner = new Scanner(System.in);
        numOfZombie = scanner.nextInt();
        lengthOfBridge = scanner.nextInt();
        int direction = 0;
        zombies = new ArrayList<>();

        for (int i = 0; i < numOfZombie; i++) {
            if (Math.random() < 0.5) {
                direction = Zombie.LEFT;
            } else {
                direction = Zombie.RIGHT;
            }
            zombies.add(new Zombie(scanner.nextInt(), direction));
        }
    }

    private void move() {
        for (int i = 0; i < zombies.size(); i++) {
            for (int j = 0; j < zombies.size(); j++) {
                if (j != i) {
                    if (zombies.get(i).getLocation() == zombies.get(j).getLocation()) {
                        zombies.get(i).changeDirection();
                    } else if (zombies.get(i).getNextLocation() == zombies.get(j).getLocation() &&
                            zombies.get(i).getDirection() != zombies.get(j).getDirection()) {
                        zombies.get(i).changeDirection();
                        zombies.get(j).changeDirection();
                    }
                }
            }
        }

        for (int i = 0; i < zombies.size(); i++) {
            zombies.get(i).changeLocation();
            if ((zombies.get(i).getLocation() < 1 || zombies.get(i).getLocation() > lengthOfBridge) &&
                    zombieOut == 0) {
                firstZombie = move+1;
                zombieOut++;
                zombies.remove(i);i--;
            } else if (zombies.get(i).getLocation() < 1 || zombies.get(i).getLocation() > lengthOfBridge) {
                zombieOut++;
                zombies.remove(i);i--;
            }
        }
        move++;
    }
}

