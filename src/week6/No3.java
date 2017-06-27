package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class No3 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();

        int testCase = Integer.parseInt(input);

        for (int i = 0; i < testCase; i++) {

            input = s.nextLine();
            int playersAndGames[] = splitInputString(input);
            int noPlayers = playersAndGames[0];
            int noGames = playersAndGames[1];

            input = s.nextLine();
            int playerWin[] = splitInputString(input);

            int games[][] = new int[noGames][];

            for (int j = 0; j < noGames; j++) {
                input = s.nextLine();
                games[j] = splitInputString(input);
            }

            System.out.println(minWin(playerWin, games));
        }

    }

    public static int minWin(int playerWin[], int games[][]) {

        List matchInvolveX = new ArrayList<>();

        for (int i = 0; i < games.length; i++) {

            if (games[i][0] == 0) {
                matchInvolveX.add(games[i][1]);
                playerWin[games[i][1]]++;
            } else if (games[i][1] == 0) {
                matchInvolveX.add(games[i][0]);
                playerWin[games[i][0]]++;
            } else {
                if (playerWin[games[i][0]] <= playerWin[games[i][1]]) {
                    playerWin[games[i][0]]++;
                } else {
                    playerWin[games[i][1]]++;
                }
            }
        }

        for (int i = 0; i < matchInvolveX.size(); i++) {
            if (playerWin[(int) matchInvolveX.get(i)] >= playerWin[0]) {
                playerWin[(int) matchInvolveX.get(i)]--;
                playerWin[0]++;
            }
        }

        int minWin = playerWin[0];
        int[] otherWin = Arrays.copyOfRange(playerWin, 1, playerWin.length);
        Arrays.sort(otherWin);

        if (minWin > otherWin[otherWin.length - 1]) {
            return playerWin[0];
        } else {
            return -1;
        }
    }

    public static int[] splitInputString(String input) {
        String splitInput[] = input.split(" ");
        int splitInt[] = new int[splitInput.length];

        for (int i = 0; i < splitInput.length; i++) {
            splitInt[i] = Integer.parseInt(splitInput[i]);
        }

        return splitInt;
    }
}
