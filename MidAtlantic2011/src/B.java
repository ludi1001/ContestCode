import java.util.*;
public class B {

	/**
	 * @param args
	 */
	static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		//new B().go();
		int L;
		ArrayList<String> words = new ArrayList<String>();
		while((L = in.nextInt()) != 0) {
			words.clear();
			String line;
			in.nextLine();
			while(!((line = in.nextLine()).equals(""))) {
				for(String s : line.split(" "))
					words.add(s);
			}
			int[]lens = new int[words.size()];
			for(int i = 0; i < lens.length; ++i)
				lens[i] = words.get(i).length();
			int[][]dp = new int[lens.length][L+1];
			boolean[][] onNextLine = new boolean[lens.length][L+1];
			int[][] lastLen = new int[lens.length][L+1];
			int minRagged = Integer.MAX_VALUE;
			int minRagged2 = 0;
			for(int j = 0; j < lens.length; ++j)
				for(int k = 0; k <= L; ++k)
					dp[j][k] = -1;
			//System.out.println("current len: " + len);
			for(int i = 1; i < lens.length; ++i) { 
				dp[0][lens[0]] = 0;
				//System.out.println("word:" + words.get(i));
				for(int j = lens[i-1]; j <= L; ++j) {
					if(dp[i-1][j] != -1) { //legal 
						if(j + 1 + lens[i] <= L) {
							//fit on same line
							dp[i][j + 1 + lens[i]] = dp[i-1][j];
							lastLen[i][j + 1 + lens[i]] = j;
							//System.out.println("same line " + (j + 1 + lens[i]) + ": " + dp[len][i][j + 1 + lens[i]]);
						}
						//fit on new line
						int newdp = dp[i-1][j] + (L - j)*(L - j);
						//System.out.println(j + " : " + newdp);
						if(dp[i][lens[i]] == -1 || dp[i][lens[i]] > newdp) {
							dp[i][lens[i]] = newdp;
							lastLen[i][lens[i]] = j;
							onNextLine[i][lens[i]] = true;
							//System.out.println("new line: " + dp[len][i][lens[i]]);
							//System.out.println("i " + i + " lens[i]" + lens[i]);
						}
					}
				}
			}
			for(int i = lens[lens.length - 1]; i <= L; ++i) {
				int r = dp[lens.length-1][i];
				if(r  < minRagged && r != -1) {
					minRagged = r;
					minRagged2 = i;
				}
			}
			Stack<Integer> stack = new Stack<Integer>();
			int c = minRagged2;
			for(int i = lens.length - 1; i > 0; --i) {
				stack.push(c);
				c = lastLen[i][c];
			}
			System.out.print(words.get(0));
			for(int i = 1; i < lens.length; ++i) {
				int last = stack.pop();
				//System.out.print("(" + last + ")");
				if(onNextLine[i][last]) {
					System.out.println();
					System.out.print(words.get(i));
				}
				else {
					System.out.print(" " + words.get(i));
				}
			}
			System.out.println();
			System.out.println("===");
		}
	}
	
	private void go() {
		while(true){
			int n=in.nextInt();
			if(n==0)break;
			in.nextLine();
			ArrayList<String> words=new ArrayList<String>();
			while(true){
				String line=in.nextLine();
				if(line.equals(""))break;
				Scanner s=new Scanner(line);
				while(s.hasNext())words.add(s.next());
			}
			int[] lengths=new int[words.size()];
			for(int i=0;i<words.size();i++)lengths[i]=words.get(i).length();
			int[][] dp=new int[words.size()+1][2];
			for(int i=0;i<=words.size();i++)dp[i][0]=Integer.MAX_VALUE;
			dp[0][0]=0;
			for(int i=0;i<words.size();i++){
				int len=0;
				for(int j=1;j<=i+1;j++){
					len+=lengths[i-j+1]+1;
					if(len-1>n)break;
					int r=dp[i-j+1][0];
					if(i<dp.length-2)r+=(n-len+1)*(n-len+1);
					if(r<dp[i+1][0]){
						dp[i+1][0]=r;
						dp[i+1][1]=j;
					}
				}
			}
			ArrayList<Integer> lens=new ArrayList<Integer>();
			for(int on=words.size()-1;on>=0;){
				lens.add(dp[on+1][1]);
				on-=dp[on+1][1];
			}
			Collections.reverse(lens);
			Queue<String> q=new LinkedList<String>(words);
			for(int i:lens){
				String out="";
				for(int j=0;j<i;j++)out+=" "+q.poll();
				System.out.println(out.substring(1));
			}
			System.out.println("===");
		}
	}
}
