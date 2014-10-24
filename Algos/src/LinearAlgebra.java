
public class LinearAlgebra {
	public double determinant(double[][] mat) { 
		double result = 0; 

		if(mat.length == 1) { 
			result = mat[0][0]; 
			return result; 
		} 

		if(mat.length == 2) { 
			result = mat[0][0] * mat[1][1] - mat[0][1] * mat[1][0]; 
			return result; 
		} 

		for(int i = 0; i < mat[0].length; i++) { 
			double temp[][] = new double[mat.length - 1][mat[0].length - 1]; 

			for(int j = 1; j < mat.length; j++) { 
				System.arraycopy(mat[j], 0, temp[j-1], 0, i); 
				System.arraycopy(mat[j], i+1, temp[j-1], i, mat[0].length-i-1); 
			} 

			result += mat[0][i] * Math.pow(-1, i) * determinant(temp); 
		} 
		return result; 
	}
	/**
	 * solves A*X=b
	 * @param A coefficients
	 * @param b constants
	 * @return values of the variables
	 */
	public double[] linearEquationSolve( double[][] A, double[] b ){
		double EPS=.000001;//or whatever you need it to be
		int n = A.length;
		double a[][] = new double[n][n+1], temp[], scale;
		for( int i = 0; i < n; i++ ) for( int j = 0; j < n; j++ ) a[i][j] = A[i][j];
		for( int i = 0; i < n; i++ ) a[i][n] = b[i];
		for( int i = 0; i < n; i++ ){
			for( int j = i; j < n; j++ )if( Math.abs(a[j][i])>EPS ){
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
					break;
			}
			scale = 1/a[i][i];
			for( int j = i; j <= n; j++ ) a[i][j] *= scale;
			for( int j = 0; j < n; j++ )if( i != j && Math.abs(a[j][i])>EPS ){
				scale = -a[j][i];
				for( int k = i; k <= n; k++ ) a[j][k] += scale*a[i][k];
			}
		}
		double[] x = new double[n];
		for( int i = 0; i < n; i++ ) x[i] = a[i][n];
		return x;
	}
	public static void main(String[] args) {
		LinearAlgebra l = new LinearAlgebra();
		double[][][]mat = {{{1}}, {{1,2},{3,4}}, {{1,2,3},{4,5,6},{7,8,9}}, {{2,1,3},{4,4,4},{5,6,7}}};
		System.out.println(l.determinant(mat[0])); //1
		System.out.println(l.determinant(mat[1])); //-2
		System.out.println(l.determinant(mat[2])); //0
		System.out.println(l.determinant(mat[3])); //12
		
		double[][]A = { {1,2},{3,4} };
		double[] b = {-1, 3};
		double[] x = l.linearEquationSolve(A, b);
		for(int i = 0; i < 2; ++i) System.out.println(x[i]); //5, -3
	}

}
