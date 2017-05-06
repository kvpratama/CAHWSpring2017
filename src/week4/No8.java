package week4;

import java.util.ArrayList;
import java.util.Scanner;

public class No8 {

    public static void main(String[] args) {
        int currentFuel = 0, currentLocation = 100, currentIndex = 0,
                 totalPrice = 0, fuelTofill;
        int maxDistance = currentFuel + currentLocation;
        boolean possible = true;

        Scanner scanner = new Scanner(System.in);
        int distance = scanner.nextInt();
        int numberOfStation = scanner.nextInt();

        ArrayList<Integer> gasStationLocationList = new ArrayList<Integer>();
        ArrayList<Integer> gasStationPriceList = new ArrayList<Integer>();

        for (int i = 0; i < numberOfStation; i++) {
            gasStationLocationList.add(scanner.nextInt());
            gasStationPriceList.add(scanner.nextInt());
        }

        int farthestPossibleGasStationIndex = farthestPossibleGasStationIndex(gasStationLocationList, maxDistance);
        int cheapestGasStationIndex = cheapestGasStationIndex(gasStationPriceList, currentIndex, farthestPossibleGasStationIndex);
        currentFuel = currentFuel - (gasStationLocationList.get(cheapestGasStationIndex) - currentLocation);
        currentLocation = gasStationLocationList.get(cheapestGasStationIndex);
        currentIndex = cheapestGasStationIndex;

        while (currentLocation < distance || currentFuel < 100) {

            if (currentLocation == distance) {
                fuelTofill = 100 - currentFuel;
                currentFuel += fuelTofill;
                totalPrice += fuelTofill * gasStationPriceList.get(currentIndex);
                break;
            }
            farthestPossibleGasStationIndex = farthestPossibleGasStationIndex(gasStationLocationList, 200 + currentLocation);

            if (farthestPossibleGasStationIndex == currentIndex) {
                possible = false;
                break;
            }

            if (farthestPossibleGasStationIndex == gasStationLocationList.size() - 1 && distance - currentLocation <= 100) {
                cheapestGasStationIndex = cheapestGasStationIndex(gasStationPriceList, currentIndex, farthestPossibleGasStationIndex);

                if (gasStationPriceList.get(cheapestGasStationIndex) >= gasStationPriceList.get(currentIndex)) {
                    fuelTofill = (distance - currentLocation) - currentFuel + 100;
                    currentFuel += fuelTofill;
                    totalPrice += fuelTofill * gasStationPriceList.get(currentIndex);
                    currentFuel -= distance - currentLocation;
                    break;
                }

            }

            cheapestGasStationIndex = cheapestGasStationIndex(gasStationPriceList, currentIndex, farthestPossibleGasStationIndex);
            fuelTofill = fuelToFill(gasStationPriceList, gasStationLocationList, currentFuel, currentIndex, cheapestGasStationIndex);


            currentFuel += fuelTofill;
            totalPrice += fuelTofill * gasStationPriceList.get(currentIndex);

            currentFuel = currentFuel - (gasStationLocationList.get(cheapestGasStationIndex) - currentLocation);
            currentLocation = gasStationLocationList.get(cheapestGasStationIndex);
            currentIndex = cheapestGasStationIndex;
        }
        if (possible) {
            System.out.println(totalPrice);
        } else {
            System.out.println("Not Possible");
        }
    }

    public static int farthestPossibleGasStationIndex(ArrayList<Integer> gasStationLocationList, int maxDistance) {
        int left = -1, farthestPossible;
        int right = gasStationLocationList.size();

        while (right > left + 1) {
            int middle = (left + right) / 2;
            if (gasStationLocationList.get(middle) >= maxDistance)
                right = middle;
            else
                left = middle;
        }

        if (right < gasStationLocationList.size() && gasStationLocationList.get(right) == maxDistance) {
            farthestPossible = right;
        } else {
            farthestPossible = left;
        }
        return farthestPossible;
    }

    public static int cheapestGasStationIndex(ArrayList<Integer> gasStationPriceList, int startStation, int endStation) {
        if (startStation == endStation)
            return startStation;

        int cheapestPrice = gasStationPriceList.get(startStation + 1), cheapestPriceIndex = startStation + 1;

        for (int i = startStation + 2; i <= endStation; i++) {
            if (gasStationPriceList.get(i) <= cheapestPrice) {
                cheapestPrice = gasStationPriceList.get(i);
                cheapestPriceIndex = i;
            }
        }
        return cheapestPriceIndex;
    }

    public static int fuelToFill(ArrayList<Integer> gasStationPriceList, ArrayList<Integer> gasStationLocationList,
                                 int currentFuel, int currentIndex, int nextGasStationIndex) {
        int toFill;
        if (nextGasStationIndex == gasStationLocationList.size() - 1) {
            if (gasStationPriceList.get(currentIndex) >= gasStationPriceList.get(nextGasStationIndex)) {
                toFill = (gasStationLocationList.get(nextGasStationIndex) - gasStationLocationList.get(currentIndex)) - currentFuel;
            } else {
                toFill = (gasStationLocationList.get(nextGasStationIndex) - gasStationLocationList.get(currentIndex)) - currentFuel + 100;
            }
        } else if (gasStationPriceList.get(currentIndex) >= gasStationPriceList.get(nextGasStationIndex)) {
            toFill = (gasStationLocationList.get(nextGasStationIndex) - gasStationLocationList.get(currentIndex)) - currentFuel;
        } else {
            toFill = 200 - currentFuel;
        }
        return toFill;
    }
}
