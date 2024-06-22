import java.util.*;
import java.io.*;

class Main {
	static class Node {
		int num;
		Node left, right;
		public Node (int num){
			this.num = num;
			this.left = this.right = null;
		}
		public Node(int num, Main.Node left, Main.Node right) {
			this.num = num;
			this.left = left;
			this.right = right;
		}
		public void insert(int num) {
			if(this.num < num) {
				if(this.right == null ) this.right = new Node (num);
				else this.right.insert(num);
			} else {
				if(this.left == null ) this.left = new Node (num);
				else this.left.insert(num);				
			}
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws Exception {
		Node root = new Node(Integer.parseInt(br.readLine()));
		while(true) {
			String input = br.readLine();
			if(input == null || input.equals("")) break;
			root.insert(Integer.parseInt(input));
		}
		postOrder(root);
		bw.flush();
	}
	private static void postOrder(Node node) throws Exception {
		if(node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		bw.append(Integer.toString(node.num)); bw.newLine();
	}
}