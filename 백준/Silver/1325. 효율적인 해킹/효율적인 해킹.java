import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.BufferedOutputStream;
import java.util.StringTokenizer;

import java.util.List;
import java.util.ArrayList;

public class Main {

    static final int IBUFFER_SIZE = 1 << 21;
    static final int OBUFFER_SIZE = 1 << 16;

    public static void main(String[] args) throws IOException {
        InputReader in = new InputReader(System.in, IBUFFER_SIZE);
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out, OBUFFER_SIZE), false);
        Solver solver = new Solver();
        int T = 1;
        for(int tt = 1; tt <= T; tt++)
            solver.solve(tt, in, out);
        out.close();
        return;
    }

    static class Solver {

        public void solve(int tc, InputReader in, PrintWriter out) {
            int N = in.nextInt();
            int M = in.nextInt();
            Vertex[] vertices = new Vertex[N];
            for(int i = 0; i < N; i++)
                vertices[i] = new Vertex();
            while(M-- > 0){
                Vertex v = vertices[in.nextInt() - 1];
                Vertex u = vertices[in.nextInt() - 1];
                u.adj.add(v);
            }
            Vertex[] bfsq = new Vertex[N];
            int[] ans = new int[N];
            int sz = 0, mx = 0;
            for(int i = 0; i < N; i++){
                int res = bfs(vertices[i], i, bfsq);
                if(res < mx)
                    continue;
                if(res > mx){
                    sz = 0;
                    mx = res;
                }
                ans[sz++] = i + 1;
            }
            for(int i = 0; i < sz; i++)
                out.printf("%d ", ans[i]);
            return;
        }

        int bfs(Vertex s, int mark, Vertex[] bfsq) {
            int l = 0, r = 0;
            s.visited = mark;
            bfsq[r++] = s;
            while(l < r){
                Vertex v = bfsq[l++];
                for(Vertex u : v.adj){
                    if(u.visited == mark)
                        continue;
                    u.visited = mark;
                    bfsq[r++] = u;
                }
            }
            return r;
        }

        class Vertex {
            List<Vertex> adj;
            int visited;

            Vertex(){
                adj = new ArrayList<>();
                visited = -1;
            }
        }
    }

    static class InputReader {
        BufferedReader br;
        StringTokenizer stt;

        public InputReader(InputStream inputStream, int size) {
            br = new BufferedReader(new InputStreamReader(inputStream), size);
            stt = null;
        }

        public String readLine() {
            try {
                return br.readLine();
            } catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
        public String next() {
            while(stt == null || !stt.hasMoreTokens())
                stt = new StringTokenizer(readLine());
            return stt.nextToken();
        }
        public int nextInt() {
            return Integer.parseInt(next());
        }
        public long nextLong() {
            return Long.parseLong(next());
        }
    }

}