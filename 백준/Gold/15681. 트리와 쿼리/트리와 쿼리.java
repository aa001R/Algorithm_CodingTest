import java.util.*;
import java.io.*;

class Main {
	static class Node{
		Node next;
		int num;
		public Node(int num, Node next) {
			this.num = num;
			this.next = next;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, root, query;
	static int [] size;
	static Node [] node, child;
	public static void main(String[] args) throws IOException
	{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		root = Integer.parseInt(st.nextToken());
		query = Integer.parseInt(st.nextToken());
		node = new Node[N+1];
		child = new Node[N+1];
		size = new int[N+1];
		for(int i = 0; i < N-1; i++) { // 트리 간선 입력 (사이클 없음)
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			node[from] = new Node(to, node[from]);
			node[to] = new Node(from, node[to]);
		}
		makeTree(root, root);
		countSubtreeNodes(root);
		for(int i = 0; i < query; i++) {
			int parent = Integer.parseInt(br.readLine());
			bw.append(Integer.toString(size[parent])); bw.newLine();
		}
		bw.flush();
	}
	private static void countSubtreeNodes(int curNode) {
		size[curNode] = 1;
		for(Node temp = child[curNode]; temp != null; temp = temp.next) {
			countSubtreeNodes(temp.num);
			size[curNode] += size[temp.num];
		}
	}
	private static void makeTree(int curNode, int parent) {
		for(Node temp = node[curNode]; temp != null; temp = temp.next) {
			if(temp.num == parent) continue;
			child[curNode] = new Node(temp.num, child[curNode]);
			makeTree(temp.num, curNode);
		}
	}
}