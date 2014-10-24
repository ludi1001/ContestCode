import java.util.*;
public class J {
	public static double[][] fastexp(double[][]mat, int power, int N) {
		
		if(power == 0) {
			double[][]res = new double[N][N];
			for(int i = 0; i < N; ++i)
				res[i][i] = 1;
			return res;
		}
		double[][]hres = fastexp(mat, power/2, N);
		double[][]res = new double[N][N];
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j)
				for(int k = 0; k < N; ++k)
					res[i][j] += hres[i][k] * hres[k][j];
		for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j)
			hres[i][j] = res[i][j];
		if(power % 2 == 1) {
			for(int i = 0; i < N; ++i) for(int j = 0; j < N; ++j) res[i][j] = 0;
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					for(int k = 0; k < N; ++k)
						res[i][j] += hres[i][k] * mat[k][j];
		}
		return res;
	}
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		int testCases = in.nextInt();
		while(testCases-- > 0) {
			int N = in.nextInt();
			int links = in.nextInt();
			int T = in.nextInt();
			double[]vec = new double[N];
			for(int i = 0; i < N; ++i)
				vec[i] = in.nextInt();
			
			double[][]mat = new double[N][N];
			for(int i = 0; i < links; ++i) {
				int j = in.nextInt();
				int k = in.nextInt();
				mat[k][j] += in.nextDouble();
			}
			
			for(int i = 0; i < N; ++i) {
				double sum = 0;
				for(int j = 0; j < N; ++j)
					sum += mat[j][i];
				mat[i][i] = 1.0 - sum;
			}
			/*for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j)
					System.out.print(mat[i][j] + " ");
				System.out.println();
			}*/
			boolean[][]connected = new boolean[N][N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					connected[i][j] = mat[i][j] > 0.0;
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					if(connected[i][j])
						connected[j][i] = true;
					else if(connected[j][i])
						connected[i][j] = true;
			
			double[][]nmat = new double[N][N];
			nmat = fastexp(mat, T, N);
			//for(int i = 0; i < N; ++i)
			//	nmat[i][i] = 1;
			/*while(T-- > 0) {
				double[][]nnmat = new double[N][N];
				for(int i = 0; i < N; ++i)
					for(int j = 0; j < N; ++j)
						for(int k = 0; k < N; ++k)
							nnmat[i][j] += nmat[i][k]*mat[k][j];
				nmat = nnmat;
			}*/
			
			/*for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j)
					System.out.print(nmat[i][j] + " ");
				System.out.println();
			}*/
			
			double[]nvec = new double[N];
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					nvec[i] += nmat[i][j] * vec[j];
			
			/*for(int i = 0; i < N; ++i) {
				System.out.print(nvec[i] + " ");
			}
			System.out.println();*/
			double min = Double.MAX_VALUE;
			for(int i = 0; i < N; ++i) {
				double sum = 0;
				for(int j = 0; j < N; ++j)
					if(connected[i][j]) {
						sum += nvec[j];
					}
				min = Math.min(min, sum);
			}
			System.out.println(min);
		}
	}
}
