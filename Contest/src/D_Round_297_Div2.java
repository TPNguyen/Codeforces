import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * #
 * 
 * @author pttrung
 */
public class D_Round_297_Div2 {

	public static long MOD = 1000000007;
	static int[] X = { 0, 1, 0, -1 };
	static int[] Y = { 1, 0, -1, 0 };
	static int minX, maxX, minY, maxY;

	public static void main(String[] args) throws FileNotFoundException {
		// PrintWriter out = new PrintWriter(new FileOutputStream(new File(
		// "output.txt")));
		PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner();
		final int n = in.nextInt();
		final int m = in.nextInt();
		String[] data = new String[n];

		ArrayList<Point> list = new ArrayList();
		for (int i = 0; i < n; i++) {
			data[i] = in.next();
			for (int j = 0; j < m; j++) {

				if (data[i].charAt(j) == '.') {
					list.add(new Point(i, j));
				}

			}
		}
		Collections.sort(list, new Comparator<Point>(){

			@Override
			public int compare(Point o1, Point o2) {
				int a = Math.min(Math.min(o1.x, n - o1.x) , Math.min(o1.y, m - o1.y));
				int b = Math.min(Math.min(o2.x, n - o2.x) , Math.min(o2.y, m - o2.y));
				return a - b;
			}});

		int[][] check = new int[n][m];
		char[][] result = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				result[i][j] = data[i].charAt(j);
			}
		}
		int index = 1;

		for (Point p : list) {
			if (check[p.x][p.y] == 0) {
				check[p.x][p.y] = index;

				minX = p.x;
				maxX = p.x;
				minY = p.y;
				maxY = p.y;

				// System.out.println(p.x + " " + p.y);
				boolean ok = false;
				while (!ok) {
					ok = true;

					if (minX - 1 >= 0) {
						boolean found = false;
						for (int i = minY; i <= maxY; i++) {
							if (result[minX - 1][i] == '.'
									&& check[minX - 1][i] != index) {
								check[minX - 1][i] = index;

								found = true;
								break;

							}
						}
						if (found) {
							for (int i = minY; i <= maxY; i++) {
								result[minX - 1][i] = '.';
								check[minX - 1][i] = index;
							}
							ok = false;
							minX--;
						}
					}

					if (maxX + 1 < n) {
						boolean found = false;
						for (int i = minY; i <= maxY; i++) {
							if (result[maxX + 1][i] == '.'
									&& check[maxX + 1][i] != index) {
								check[maxX + 1][i] = index;

								found = true;
								break;
							}
						}
						if (found) {
							for (int i = minY; i <= maxY; i++) {
								result[maxX + 1][i] = '.';
								check[maxX + 1][i] = index;

							}
							ok = false;
							maxX++;
						}
					}
					if (minY - 1 >= 0) {
						boolean found = false;
						for (int i = minX; i <= maxX; i++) {
							if (result[i][minY - 1] == '.'
									&& check[i][minY - 1] != index) {
								check[i][minY - 1] = index;

								found = true;
								break;
							}
						}
						if (found) {
							for (int i = minX; i <= maxX; i++) {
								result[i][minY - 1] = '.';
								check[i][minY - 1] = index;
							}
							ok = false;
							minY--;
						}
					}
					if (maxY + 1 < m) {
						boolean found = false;
						for (int i = minX; i <= maxX; i++) {
							if (result[i][maxY + 1] == '.'
									&& check[i][maxY + 1] != index) {
								check[i][maxY + 1] = index;
								found = true;
								break;
							}
						}
						if (found) {
							for (int i = minX; i <= maxX; i++) {
								result[i][maxY + 1] = '.';
								check[i][maxY + 1] = index;
							}
							ok = false;
							maxY++;
						}
					}
				}

				index++;
			}

		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				out.print(result[i][j]);
			}
			out.println();
		}

		out.close();
	}

	public static int[] KMP(String val) {
		int i = 0;
		int j = -1;
		int[] result = new int[val.length() + 1];
		result[0] = -1;
		while (i < val.length()) {
			while (j >= 0 && val.charAt(j) != val.charAt(i)) {
				j = result[j];
			}
			j++;
			i++;
			result[i] = j;
		}
		return result;

	}

	public static boolean nextPer(int[] data) {
		int i = data.length - 1;
		while (i > 0 && data[i] < data[i - 1]) {
			i--;
		}
		if (i == 0) {
			return false;
		}
		int j = data.length - 1;
		while (data[j] < data[i - 1]) {
			j--;
		}
		int temp = data[i - 1];
		data[i - 1] = data[j];
		data[j] = temp;
		Arrays.sort(data, i, data.length);
		return true;
	}

	public static int digit(long n) {
		int result = 0;
		while (n > 0) {
			n /= 10;
			result++;
		}
		return result;
	}

	public static double dist(long a, long b, long x, long y) {
		double val = (b - a) * (b - a) + (x - y) * (x - y);
		val = Math.sqrt(val);
		double other = x * x + a * a;
		other = Math.sqrt(other);
		return val + other;

	}

	public static class Point implements Comparable<Point> {

		int x, y;

		public Point(int start, int end) {
			this.x = start;
			this.y = end;
		}

		@Override
		public int hashCode() {
			int hash = 5;
			hash = 47 * hash + this.x;
			hash = 47 * hash + this.y;
			return hash;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			final Point other = (Point) obj;
			if (this.x != other.x) {
				return false;
			}
			if (this.y != other.y) {
				return false;
			}
			return true;
		}

		@Override
		public int compareTo(Point o) {
			return x - o.x;
		}
	}

	public static class FT {

		long[] data;

		FT(int n) {
			data = new long[n];
		}

		public void update(int index, long value) {
			while (index < data.length) {
				data[index] += value;
				index += (index & (-index));
			}
		}

		public long get(int index) {
			long result = 0;
			while (index > 0) {
				result += data[index];
				index -= (index & (-index));
			}
			return result;

		}
	}

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}

	public static long pow(long a, long b) {
		if (b == 0) {
			return 1;
		}
		if (b == 1) {
			return a;
		}
		long val = pow(a, b / 2);
		if (b % 2 == 0) {
			return val * val % MOD;
		} else {
			return val * (val * a % MOD) % MOD;

		}
	}

	static class Scanner {

		BufferedReader br;
		StringTokenizer st;

		public Scanner() throws FileNotFoundException {
			// System.setOut(new PrintStream(new
			// BufferedOutputStream(System.out), true));
			br = new BufferedReader(new InputStreamReader(System.in));
			// br = new BufferedReader(new InputStreamReader(new
			// FileInputStream(new File("input.txt"))));
		}

		public String next() {

			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (Exception e) {
					throw new RuntimeException();
				}
			}
			return st.nextToken();
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public String nextLine() {
			st = null;
			try {
				return br.readLine();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		public boolean endLine() {
			try {
				String next = br.readLine();
				while (next != null && next.trim().isEmpty()) {
					next = br.readLine();
				}
				if (next == null) {
					return true;
				}
				st = new StringTokenizer(next);
				return st.hasMoreTokens();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
	}
}
