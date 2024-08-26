import java.io.*;
import java.util.*;


class Node{
	char n;
	Node left, right;
	Node(char n){
		this.n = n;
		left = null;
		right = null;
	}
}

public class Main {
	static Node[] tree;
	static StringBuilder sb;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new Node[N+1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);
			
			if(tree[parent-'A'] == null)
				tree[parent-'A'] = new Node(parent);
			if(left != '.') {
				tree[left-'A'] = new Node(left);
				tree[parent-'A'].left = tree[left-'A'];
			}
			if(right != '.') {
				tree[right-'A'] = new Node(right);
				tree[parent-'A'].right = tree[right-'A'];
			}
		}
		
		// 전위 순회
        preorder(tree[0]); sb.append("\n");
        // 중위 순회
        inorder(tree[0]); sb.append("\n");
        // 후위 순회
        postorder(tree[0]); sb.append("\n");
		
		System.out.println(sb);
	}

	// 전위 순회
	private static void preorder(Node node) {
		if(node == null) return;
		sb.append(node.n);
		preorder(node.left);
		preorder(node.right);
	}

	private static void inorder(Node node) {
		if(node == null) return;
		inorder(node.left);
		sb.append(node.n);
		inorder(node.right);

	}

	private static void postorder(Node node) {
		if(node == null) return;
		postorder(node.left);
		postorder(node.right);
		sb.append(node.n);
	}
}