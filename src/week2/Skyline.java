package week2;


import java.util.*;

public class Skyline {

    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> skyline = new ArrayList<>();
        List<Corner> corners = new ArrayList<>();

        for (int i = 0; i < buildings.length; i++) {
            Corner startCorner = new Corner(buildings[i][0], buildings[i][1], true);
            corners.add(startCorner);
            Corner endCorner = new Corner(buildings[i][2], buildings[i][1], false);
            corners.add(endCorner);
        }

        Collections.sort(corners, new Comparator<Corner>() {
            public int compare(Corner a, Corner b) {
                if (a.x != b.x)
                    return Integer.compare(a.x, b.x);

                if (a.isStart && b.isStart) {
                    return Integer.compare(b.height, a.height);
                }

                if (!a.isStart && !b.isStart) {
                    return Integer.compare(a.height, b.height);
                }

                return a.isStart ? -1 : 1;
            }
        });

        PriorityQueue<Integer> heightHeap = new PriorityQueue<Integer>(10, Collections.reverseOrder());

        for (int i = 0; i < corners.size(); i++) {
            if (corners.get(i).isStart) {
                if (heightHeap.isEmpty() || corners.get(i).height > heightHeap.peek()) {
                    skyline.add(new int[] { corners.get(i).x, corners.get(i).height });
                }
                heightHeap.add(corners.get(i).height);
            } else {
                heightHeap.remove(corners.get(i).height);

                if(heightHeap.isEmpty()){
                    skyline.add(new int[] {corners.get(i).x, 0});
                }else if(corners.get(i).height > heightHeap.peek()){
                    skyline.add(new int[]{corners.get(i).x, heightHeap.peek()});
                }
            }
        }

        return skyline;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfBuilding = scanner.nextInt();
        int[][] buildings = new int[numOfBuilding][3];

        for (int i = 0; i < numOfBuilding; i++) {
            for (int j = 0; j < 3; j++) {
                buildings[i][j] = scanner.nextInt();
            }
        }

        Skyline skyline = new Skyline();
        List<int[]> theSkyline = skyline.getSkyline(buildings);
        for (int i = 0; i < theSkyline.size(); i++) {
            System.out.print(theSkyline.get(i)[0] + ", ");
            System.out.println(theSkyline.get(i)[1]);
        }
    }
}
