import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        TreeMap<Integer, Integer> tree = new TreeMap<>();

        for (String op : operations) {
            StringTokenizer st = new StringTokenizer(op);
            String cmd = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (cmd.equals("I")) {
                tree.put(num, tree.getOrDefault(num, 0) + 1);
            } else if (cmd.equals("D") && !tree.isEmpty()) {
                int key = (num == 1) ? tree.lastKey() : tree.firstKey();
                if (tree.get(key) == 1) {
                    tree.remove(key);
                } else {
                    tree.put(key, tree.get(key) - 1);
                }
            }
        }

        if (!tree.isEmpty()) {
            answer[0] = tree.lastKey();
            answer[1] = tree.firstKey();
        }
        return answer;
    }
}
