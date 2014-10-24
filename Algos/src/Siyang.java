import java.util.*;
public class Siyang {
//geometry
	 static double dist( double[] x1, double[] x2 )
     {
             double ans = 0;
             for( int i = 0; i < x1.length; i++ ) ans += (x1[i]-x2[i])*(x1[i]-x2[i]);
             return Math.sqrt(ans);
     }

     double cross( double x0, double y0, double x1, double y1 )
     {
             return x0 * y1 - x1 * y0;
     }

     boolean intersects( double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3 )
     {
             double a = cross( x1 - x0, y1 - y0, x2 - x0, y2 - y0 );
             double b = cross( x1 - x0, y1 - y0, x3 - x0, y3 - y0 );
             double c = cross( x3 - x2, y3 - y2, x0 - x2, y0 - y2 );
             double d = cross( x3 - x2, y3 - y2, x1 - x2, y1 - y2 );
             return ( ( a >= 0 && b <= 0 ) || ( a <= 0 && b >= 0 ) ) && ( ( c >= 0 && d <= 0 ) || ( c <= 0 && d >= 0 ) );
     }

     /*
             implements Graham scan for a set of points;
             outputs points on convex hull in counterclockwise order,
             starting with the point in the leftmost, lowest point, e.g.,
             the point with lowest y coord, then lowest x coord
     */

     static ArrayList<double[]> convex_hull( double[][] pts )
     {
             int n = pts.length;
             // find starting pt
             double[] start = pts[0];
             for( int i = 0; i < n; i++ )
                     if( (pts[i][1]<start[1]) || (pts[i][1]==start[1]&&pts[i][0]<start[0]) )
                             start = pts[i];
             // sort by angle, then by distance, against starting point
             Integer[] ptr = new Integer[n+1];
             for( int i = 0; i < n; i++ ) ptr[i] = i;
             double[] a = new double[n], d = new double[n];
             for( int i = 0; i < n; i++ )
             {
                     a[i] = Math.atan2( pts[i][1] - start[1], pts[i][0] - start[0] );
                     d[i] = Math.hypot( start[0] - pts[i][0], start[1] - pts[i][1] );
             }
             Arrays.sort( ptr, 0, n, new AngleComparator( a, d ) );
             ptr[n] = ptr[0];
             // check counterclockwise turning ( turn(a,b,c) > 1 )
             ArrayList<double[]> h = new ArrayList<double[]>();
             h.add(pts[ptr[0]]);
             h.add(pts[ptr[1]]);
             int last = 1;
             for( int i = 2; i <= n; i++ )
             {
                     while( true )
                     {
                             double t = turn( h.get(last-1), h.get(last), pts[ptr[i]] );
                             if( t<0 || (t*t)<EPS )
                             {
                                     h.remove(last);
                                     last--;
                             }
                             else break;
                             if( last==0 ) break;
                     }
                     last++;
                     h.add(pts[ptr[i]]);
             }
             h.remove(last);
             // last case: if the entire thing was just a line
             if( h.size()==1 ) h.add(pts[ptr[n-1]]);
             return h;
     }

     static class AngleComparator implements Comparator<Integer>
     {
             double[] a, d;
             AngleComparator( double[] _a, double[] _d )
             { a = _a; d = _d; }
             public int compare( Integer x, Integer y )
             {
                     if( a[x] > a[y] ) return 1;
                     if( a[x] < a[y] ) return -1;
                     if( d[x] > d[y] ) return 1;
                     if( d[x] < d[y] ) return -1;
                     return 0;
             }
     }

     static double turn( double[] a, double[] b, double[] c )
     { return (b[0]-a[0])*(c[1]-a[1])-(b[1]-a[1])*(c[0]-a[0]); }


/*
     Calculates the area and centroid of the polygon given by x, y.
*/

     static double polygon_area( double[] x, double[] y )
     {
             double a = 0;
             for( int i = 1; i < x.length; i++ ) a += x[i-1]*y[i] - x[i]*y[i-1];
             a += x[x.length-1]*y[0] - x[0]*y[x.length-1];
             a /= 2;
             if( a < 0 ) a = -a;
             return a;
     }

     static double[] polygon_area_centroid( double[] x, double[] y )
     {
             double[] a = new double[3];
             int n = x.length;
             double cross;
             for( int i = 1; i < x.length; i++ )
             {
                     cross = x[i-1]*y[i] - x[i]*y[i-1];
                     a[0] += cross;
                     a[1] += cross*(x[i-1]+x[i]); a[2] += cross*(y[i-1]+y[i]);
             }
             cross = x[n-1]*y[0] - x[0]*y[n-1];
             a[0] += cross;
             a[0] /= 2;
             if( a[0] < 0 ) a[0] *= -1;
             a[1] += cross*(x[n]+x[0]); a[2] += cross*(y[n]+y[0]);
             a[1] /= 6*a[0]; a[2] /= 6*a[0];

             return a;
     }
     
     /*
     Java implementations of Ford-Fulkerson and Edmonds-Karp.
*/

     ArrayList<Edge>[] g;
     int n;

     class Edge
     {
             int j, c, f;
             Edge r;

             Edge(int j, int c)
             {
                     this.j = j;
                     this.c = c;
             }
     }

     void addEdge(int i, int j, int c)
     {
             Edge e = new Edge(j, c), f = new Edge(i, 0);
             e.r = f;
             f.r = e;
             g[i].add(e);
             g[j].add(f);
     }

     int maxFlow(int s, int t)
     {
             /*
             seen = new boolean[n];
             do Arrays.fill(seen, false);
             while(maxFlowDFS(s, t, INF) > 0);
             */
             pre = new Edge[n];
             do Arrays.fill(pre, null);
             while(maxFlowBFS(s, t) > 0);
             int ans = 0;
             for(Edge e : g[s]) ans += e.f;
             return ans;
     }

     // Ford-Fulkerson
     boolean[] seen;

     int maxFlowDFS(int s, int t, int mf)
     {
             if(s == t) return mf;
             if(seen[s]) return 0;
             seen[s] = true;
             for(Edge e : g[s])
             {
                     if(e.c == e.f) continue;
                     int mf2 = Math.min(mf, e.c - e.f);
                     mf2 = Math.min(mf2, maxFlowDFS(e.j, t, mf2));
                     if(mf2 > 0)
                     {
                             e.f += mf2;
                             e.r.f -= mf2;
                             return mf2;
                     }
             }
             return 0;
     }

     // Edmonds-Karp
     Edge[] pre;

     int maxFlowBFS(int s, int t)
     {
             LinkedList<Integer> q = new LinkedList<Integer>();
             q.add(s);
             while(!q.isEmpty())
             {
                     int i = q.poll();
                     for(Edge e : g[i]) if(e.c > e.f && e.j != s && pre[e.j] == null)
                     {
                             pre[e.j] = e;
                             q.add(e.j);
                     }
             }
             if(pre[t] == null) return 0;
             int f = INF;
             for(Edge e = pre[t]; e != null; e = pre[e.r.j])
                     f = Math.min(f, e.c - e.f);
             for(Edge e = pre[t]; e != null; e = pre[e.r.j])
             {
                     e.f += f;
                     e.r.f -= f;
             }
             return f;
     }
     
     /*
     Java implementations of min-cost max flow using Bellman-Ford.
*/

     ArrayList<Edge>[] g;
     int n;

     class Edge
     {
             int j, ca, co, f;
             Edge r;

             Edge(int j, int ca, int co)
             {
                     this.j = j;
                     this.ca = ca;
                     this.co = co;
             }
     }

     void addEdge(int i, int j, int ca, int co)
     {
             Edge e = new Edge(j, ca, co), f = new Edge(i, 0, -co);
             e.r = f;
             f.r = e;
             g[i].add(e);
             g[j].add(f);
     }

     int[] minCostMaxFlow(int s, int t)
     {
             int mc = 0, mf = 0, dist[] = new int[n];
             Edge[] pre = new Edge[n];
             while(true)
             {
                     Arrays.fill(dist, INF);
                     Arrays.fill(pre, null);
                     dist[s] = 0;
                     for(int q = 0; q < n; q++)
                     {
                             boolean change = false;
                             for(int i = 0; i < n; i++) for(Edge e : g[i])
                             if(e.f < e.ca && dist[i] + e.co < dist[e.j])
                             {
                                     dist[e.j] = dist[i] + e.co;
                                     pre[e.j] = e;
                                     change = true;
                             }
                             if(!change) break;
                     }
                     if(pre[t] == null) break;
                     int f = INF;
                     for(Edge e = pre[t]; e != null; e = pre[e.r.j])
                             f = Math.min(f, e.ca - e.f);
                     for(Edge e = pre[t]; e != null; e = pre[e.r.j])
                     {
                             e.f += f;
                             e.r.f -= f;
                     }
                     mf += f;
                     mc += f * dist[t];
             }
             return new int[]{mc, mf};
     }
     
     
     /*
     Generates next permutations (in C++, use std::next_permutation).
*/

     // permutes entries of an int array; returns false on end
     static boolean next_permutation( int[] p )
     {
             int n = p.length;
             if( n < 2 ) return false;
             int i = n-2;
             while( i >= 0 && p[i] >= p[i+1] ) i--;
             if( i >= 0 )
             {
                     int j = n-1;
                     while( p[j] <= p[i] ) j--;
                     p[i] ^= p[j]; p[j] ^= p[i]; p[i] ^= p[j];
             }
             for( int d = 1; i + d < n - d; d++ )
             {
                     p[i+d] ^= p[n-d]; p[n-d] ^= p[i+d]; p[i+d] ^= p[n-d];
             }
             return i == -1 ? false : true;
     }

     // permutes bits of an int; returns -1 on end
     static int next_permutation( int x, int n )
     {
             if( n < 2 ) return -1;
             int i = 0;
             while( i < n-1 && ( (x>>i)&3 ) != 1 ) i++;
             i++;
             if( i == n ) return -1;
             int cnt = 0;
             for( int j = 0; j < i; j++ ) cnt += (x>>j)&1;
             return x ^ ( x & ((1<<i)-1) ) ^ (1<<i) ^ ( (1<<(cnt-1)) - 1 );
     }
     
  // Finds gcd for two numbers, as well as other fancy things.

  // Euclidean algorithm
  // for BigIntegers, use BigInteger.gcd(...)
  static int gcd(int a, int b)
  {
          while(b != 0)
          {
                  int t = a%b;
                  a = b;
                  b = t;
          }
          return a;
  }

  // extended Euclidean algorithm
  // solves for (x,y) in Bezout's Identity: ax+by = gcd(a,b)
  static int[] extended_euclidean(int a, int b)
  {
          int x = 0, lx = 1, y = 1, ly = 0;
          while(b != 0)
          {
                  int t = a%b, q = a/b;
                  a = b; b = t;
                  t = x; x = lx-q*x; lx = t;
                  t = y; y = ly-q*y; ly = t;
          }
          return new int[]{ lx, ly };
  }

  // uses extended Euclidean algorithm to calculate inverse of a modulo m
  static int modulo_inverse(int a, int m)
  {
          int inv = extended_euclidean(a,m)[0];
          if(inv<0) inv += m;
          return inv;
  }
  
  /*
  Compresses an array of longs into a corresponding array of ints.
*/

  int[] compress_longs( long[] p )
  {
          long[] copy = new long[p.length];
          for( int i = 0; i < copy.length; i++ ) copy[i] = p[i];
          Arrays.sort( copy );
          TreeMap<Long,Integer> lookup = new TreeMap<Long,Integer>();
          for( int i = 0; i < p.length; i++ ) if( !lookup.containsKey( copy[i] ) ) lookup.put( copy[i], i );
          int ans[] = new int[p.length];
          for( int i = 0; i < p.length; i++ ) ans[i] = lookup.get( p[i] );
          return ans;
  }
  
  /*
  Template for Dijkstra's algorithm.
*/

  static int[] dijkstra( int[][] g, int start )
  {
          int[] d = new int[g.length];
          for( int i = 0; i < g.length; i++ ) d[i] = INF;
          d[start] = 0;
          TreeSet<Integer> q = new TreeSet<Integer>( new DistanceComparator( d ) );
          q.add( start );
          while( !q.isEmpty() )
          {
                  int v = q.poll();
                  for( int c = 0; c < g.length; c++ ) if( d[c] > d[v]+g[v][c] )
                  {
                          d[c] = d[v]+g[v][c];
                          q.remove( c );
                          q.add( c );
                  }
          }
          return d;
  }

  class DistanceComparator implements Comparator<Integer>
  {
          int[] d;
          DistanceComparator( int[] _d ) { d = _d; }
          public int compare( Integer a, Integer b )
          {
                  if( d[a] > d[b] ) return 1;
                  if( d[a] < d[b] ) return -1;
                  return 0;
          }
  }
  
  
  /*
  Returns the Levenshtein distance between two Strings.
  Code is in Java. Commented out are variations using C++ or Java char[]'s.
*/

  //int edit_distance( string a, string b )
  //static int edit_distance( char[] a, char[] b )
  static int edit_distance( String a, String b )
  {
          //int m = a.size(), n = b.size();
          //int m = a.length, n = b.length;
          int m = a.length(), n = b.length();
          int[][] d = new int[m+1][2];
          for( int i = 0; i <= m; i++ ) d[i][0] = i;

          for( int j = 1; j <= n; j++ )
          {
                  d[0][j%2] = j;
                  for( int i = 1; i <= m; i++ )
                  {
                          //if( a[i-1] == b[j-1] ) d[i][j] = d[i-1][(j-1)%2];
                          if( a.charAt(i-1) == b.charAt(j-1) ) d[i][j%2] = d[i-1][(j-1)%2];
                          else d[i][j%2] = 1+Math.min( d[i-1][(j-1)%2], Math.min( d[i][(j-1)%2], d[i-1][j%2] ) );
                  }
          }

          return d[m][n%2];
  }
  
  
  static double[] gaussian_elim( double[][] A, double[] b )
  {
          int n = A.length;
          double a[][] = new double[n][n+1], temp[], scale;
          for( int i = 0; i < n; i++ ) for( int j = 0; j < n; j++ ) a[i][j] = A[i][j];
          for( int i = 0; i < n; i++ ) a[i][n] = b[i];
          for( int i = 0; i < n; i++ )
          {
                  for( int j = i; j < n; j++ )
                          if( Math.abs(a[j][i])>EPS )
                          {
                                  // swap rows
                                  temp = a[j];
                                  a[j] = a[i];
                                  a[i] = temp;
                                  break;
                          }
                  // scale a[i] by 1/a[i][i]
                  scale = 1/a[i][i];
                  for( int j = i; j <= n; j++ ) a[i][j] *= scale;
                  for( int j = 0; j < n; j++ )
                          if( i != j && Math.abs(a[j][i])>EPS )
                          {
                                  // add -a[j][i]*a[i] to a[j]
                                  scale = -a[j][i];
                                  for( int k = i; k <= n; k++ ) a[j][k] += scale*a[i][k];
                          }
          }
          double[] x = new double[n];
          for( int i = 0; i < n; i++ ) x[i] = a[i][n];
          return x;
  }

  // The following code performs gaussian elimination on matrix modulo p, for a prime p.
  // Note: memoizes the inverse mod p
  static int[] gaussian_elim_mod( int[][] A, int[] b, int p )
  {
          int n = A.length;
          int a[][] = new int[n][n+1], temp[], scale, inv[] = new int[p];
          inv[1] = 1;
          for( int i = 2; i < p; i++ ) inv[i] = -1;
          for( int i = 0; i < n; i++ ) for( int j = 0; j < n; j++ ) a[i][j] = A[i][j];
          for( int i = 0; i < n; i++ ) a[i][n] = b[i];
          for( int i = 0; i < n; i++ )
          {
                  for( int j = i; j < n; j++ )
                          if( a[j][i] != 0 )
                          {
                                  // swap rows
                                  temp = a[j];
                                  a[j] = a[i];
                                  a[i] = temp;
                                  break;
                          }
                  // scale a[i] by 1/a[i][i]
                  if( inv[a[i][i]]==-1 )
                  {
                          inv[a[i][i]] = modulo_inverse(a[i][i],p);
                          inv[inv[a[i][i]]] = a[i][i];
                  }
                  scale = inv[a[i][i]];
                  for( int j = i; j <= n; j++ ) a[i][j] = (scale*a[i][j])%p;
                  for( int j = 0; j < n; j++ )
                          if( i != j && a[j][i] != 0 )
                          {
                                  // add -a[j][i]*a[i] to a[j]
                                  scale = p-a[j][i];
                                  for( int k = i; k <= n; k++ ) a[j][k] = (a[j][k]+scale*a[i][k])%p;
                          }
          }
          int[] x = new int[n];
          for( int i = 0; i < n; i++ ) x[i] = a[i][n];
          return x;
  }
  
  
  static double[][] matrix_mult( double[][] a, double[][] b )
  {
          double[][] c = new double[a.length][b[0].length];
          int n = a[0].length;

          for( int i = 0; i < c.length; i++ )
                  for( int j = 0; j < c[0].length; j++ )
                          for( int k = 0; k < n; k++ )
                                  c[i][j] += a[i][k] * b[k][j];

          return c;
  }
  
  

//BEGIN KOSARAJU'S (Java)

       boolean[]seen;
       LinkedList<Integer>[]g,[]h; // graph, reverse graph
       int[]q, nq, []c, nc; // queue, queue index, component map, num components

       void dfs1( int x )
       {
               if( seen[x] ) return;
               seen[x] = true;
               for( int y : g[x] ) dfs1(y);
               q[nq++] = x;
       }

       void dfs2( int x )
       {
               if( c[x] > -1 ) return;
               c[x] = nc;
               for( int y : h[x] ) dfs2(y);
       }

               nq = 0;
               q = new int[n];
               seen = new boolean[n];
               for( int i = 0; i < n; i++ ) dfs1(i);

               nc = 0;
               c = new int[n];
               Arrays.fill( c, -1 );
               for( int i = n-1; i >= 0; i-- ) if( c[q[i]] == -1 )
               {
                       dfs2(q[i]);
                       nc++;
               }

//END KOSARAJU'S (Java)
               
               
               
               // implements Heron's method to find square root of a BigInteger
               static BigInteger sqrt( BigInteger n )
               {
                       int d = n.bitLength();
                       BigInteger x = BigInteger.ONE.shiftLeft( d >> 1 );
                       BigInteger y = n.divide( x );
                       BigInteger diff = x.subtract( y ).abs();
                       while( !( diff.equals( BigInteger.ZERO ) || diff.equals( BigInteger.ONE ) ) )
                       {
                               x = x.add( y ).shiftRight( 1 );
                               y = n.divide( x );
                               diff = x.subtract( y ).abs();
                       }
                       return x;
               }

}
