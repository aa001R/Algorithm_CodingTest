import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int [2];
        TreeMap<Integer, Integer> tree = new TreeMap<>();
        int opCnt = operations.length;
        for (int i = 0; i < opCnt; i++) {
				StringTokenizer st = new StringTokenizer(operations[i]);
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());

				if (cmd == 'I')
					tree.put(num, tree.getOrDefault(num, 0) + 1);
				else if (cmd == 'D') {
					if (tree.isEmpty()) continue;

					int res = 0;
					if (num == 1)
						res = tree.lastKey();
					else if (num == -1) {
						res = tree.firstKey();
					}
					
					if (tree.get(res) == 1)
						tree.remove(res);
					else
						tree.put(res, tree.get(res) - 1);
				}
			}
			if (tree.isEmpty()) {
                answer[0] = answer[1] = 0;
            }
			else {
                answer[0] = tree.lastKey();
                answer[1] = tree.firstKey();
			}
        return answer;
    }
}