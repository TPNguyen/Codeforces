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
public class J_SchoolTeamContest_1 {

	public static long MOD = 1000000007;

	public static void main(String[] args) throws FileNotFoundException {
		// PrintWriter out = new PrintWriter(new FileOutputStream(new File(
		// "output.txt")));
		PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner();
		String a = in.next();
		String b = in.next();
		ArrayList<Entry> x = new ArrayList();
		ArrayList<Entry> y = new ArrayList();
		int s = -1;
		char last = 0;
		for (int i = 0; i < a.length(); i++) {
			if (s == -1) {
				s = i;

			} else if (a.charAt(i) != last) {
				x.add(new Entry(s, i - 1, last));
				s = i;
			}
			last = a.charAt(i);
		}
		x.add(new Entry(s, a.length() - 1, last));
		s = -1;
		last = 0;
		for (int i = 0; i < b.length(); i++) {
			if (s == -1) {
				s = i;

			} else if (b.charAt(i) != last) {
				y.add(new Entry(s, i - 1, last));
				s = i;
			}
			last = b.charAt(i);
		}
		y.add(new Entry(s, b.length() - 1, last));
		// System.out.println(x);
		boolean ok = true;
		int i = 0;
		int j = 0;
		int total = -1;
		s = -1;
		int e = -1;
		while (i < x.size() && j < y.size()) {
			int n = x.get(i).e - x.get(i).s + 1;
			int m = y.get(j).e - y.get(j).s + 1;
			//System.out.println(x.get(i) + " " + y.get(j));
			if (n == m && x.get(i).c == y.get(j).c) {
				i++;
				j++;
			} else if (x.get(i).c == y.get(j).c) {
				if (n < m) {
					if (i + 2 < x.size()) {
						if (x.get(i + 2).c == y.get(j).c) {
							int k = (x.get(i + 2).e - x.get(i + 2).s + 1);
							if (k + n == m) {
								int h = (x.get(i + 1).e - x.get(i + 1).s + 1);
								if (h == 1) {
									if (total == -1) {
										total = h;
										s = x.get(i + 1).s;
										e = x.get(i + 1).e;
									} else {
										ok = false;
										break;
									}
									i += 3;
									j++;
								} else {
									ok = false;
									break;
								}
							} else {
								ok = false;
								break;
							}
						} else {
							ok = false;
							break;
						}
					} else {
						ok = false;
						break;
					}

				} else if (n == m + 1) {
					if (total == -1) {
						total = n;
						s = x.get(i).s;
						e = x.get(i).e;
					} else {
						ok = false;
						break;
					}
					i++;
					j++;
				} else {
					ok = false;
					break;
				}
			} else {
				if (n == 1) {

					if (total == -1) {
						total = n;
						s = x.get(i).s;
						e = x.get(i).e;
					} else {
						ok = false;
						break;
					}
					i++;
				} else {
					ok = false;
					break;
				}
			}
		}

		if (ok) {
			if (j == y.size() && i + 1 == x.size()) {
				int n = x.get(i).e - x.get(i).s + 1;
				if (n == 1) {
					if (total == -1) {
						total = n;
						s = x.get(i).s;
						e = x.get(i).e;
					} else {
						ok = false;
					}
				} else {
					ok = false;
				}
			}
		}
		// System.out.println(ok);
		if (!ok) {
			out.println(0);
		} else {
			out.println(total);
			for (i = s; i <= e; i++) {
				out.print((i + 1) + " ");
			}
		}
		out.close();
	}

	static class Entry {
		int s, e;
		char c;

		public Entry(int s, int e, char c) {
			super();
			this.s = s;
			this.e = e;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Entry [s=" + s + ", e=" + e + ", c=" + c + "]";
		}

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
