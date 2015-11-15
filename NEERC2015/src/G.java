import java.util.*;
import java.awt.Point;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class G {
	public static ArrayList<Integer> topSort(ArrayList<ArrayList<Integer>> g) {
		ArrayList<Integer> top = new ArrayList<Integer>();
		int n = g.size();
		int[]c = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int u : g.get(i)) {
				++c[u];
			}
		}
		ArrayList<Integer> qu = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			if (c[i] == 0) {
				qu.add(i);
			}
		}
		while (!qu.isEmpty()) {
			Collections.sort(qu);
			ArrayList<Integer> nq = new ArrayList<Integer>();
			for (int u : qu) {
				top.add(u);
				for (int v : g.get(u)) {
					--c[v];
					if (c[v] == 0) nq.add(v);
				}
			}
			qu = nq;
		}
		return top;
	}
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(new File("graph.in"));
		int n = in.nextInt();
		int m = in.nextInt();
		int k = in.nextInt();
		ArrayList<ArrayList<Integer>> g = new ArrayList<>();
		for (int i = 0; i < n; ++i) g.add(new ArrayList<Integer>());
		for (int i = 0; i < m; ++i) {
			int u = in.nextInt() - 1, v = in.nextInt() - 1;
			if (!g.get(u).contains(v))
				g.get(u).add(v);
		}
		
		ArrayList<Point> ne = new ArrayList<Point>();
		int[]c = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int u : g.get(i)) {
				++c[u];
			}
		}
		ArrayList<Integer> qu = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			if (c[i] == 0) {
				qu.add(i);
			}
		}
		while (k > 0 && !qu.isEmpty()) {
			Collections.sort(qu);
			for (int i = qu.size() - 1; i > 0; --i) {
				if (k == 0) break;
				--k;
				ne.add(new Point(qu.get(i),qu.get(i-1)));
				g.get(qu.get(i)).add(qu.get(i-1));
			}
			ArrayList<Integer> nq = new ArrayList<Integer>();
			for (int u : qu) {
				for (int v : g.get(u)) {
					--c[v];
					if (c[v] == 0) nq.add(v);
				}
			}
			qu = nq;
		}
		ArrayList<Integer> top = topSort(g);
		PrintWriter out = new PrintWriter(new FileWriter(new File("graph.out")));
		for (int i : top) {
			out.print((i + 1) + " ");
		}
		out.println();
		out.println(ne.size());
		for (int i = 0; i < ne.size(); ++i) {
			out.println((ne.get(i).x+1) + " " + (ne.get(i).y+1));
		}
		out.close();
	}

}
