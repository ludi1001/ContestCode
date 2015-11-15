/*
 * The code in this file can be used to estimate the largest number of factors
 * that a number <= MAX can have.
 *
 * A number is called `highly composite' if it has more factors than any number
 * less than it. The function `largest_highly_composite' returns the largest
 * highly composite number less than `bound', as well as the number of factors
 * that it has.
 *
 * Due to the low recursion depth and branching factor, the function runs
 * instantaneously for almost all long longs.
 */

#include <algorithm>
#include <iostream>
#include <vector>
#include <utility>

using namespace std;

#define N 1000
#define INF 9001
#define MAX 1e13

vector<int> primes;
bool is_prime[N+1];

void generate_primes()
{
        for( int i = 2; i <= N; i++ ) is_prime[i] = true;
        for( int i = 2; i <= N; i++ ) if( is_prime[i] )
        {
                primes.push_back(i);
                for( int j = 2 * i; j <= N; j += i ) is_prime[j] = false;
        }
}

pair< long long, int > rec( long long bound, int index, long long prod, int max_pow )
{
        long long best = prod;
        int num_div = 1;
        prod *= primes[index];
        for( int i = 1; i <= max_pow && prod <= bound; i++, prod *= primes[index] )
        {
                pair< long long, int > next = rec( bound, index + 1, prod, i );
                if( ( i + 1 ) * next.second > num_div )
                {
                        best = next.first;
                        num_div = ( i + 1 ) * next.second;
                }
        }
        return make_pair( best, num_div );
}

pair< long long, int > largest_highly_composite( long long bound )
{
        return rec( bound, 0, 1, INF );
}

int main()
{
        generate_primes();

        pair<long long,int> ans = largest_highly_composite( MAX );
        cout << "The largest highly composite number <= " << MAX;
        cout << " is " << ans.first;
        cout << " which has " << ans.second << " factors." << endl;

        return 0;
}


*
Warnings before you copy/paste this code:
- Indices are 1-based.

Description:
Suppose we wish to store a frequency table freq(i), where i is some integer
between 1 and N, inclusive. A Fenwick tree is a data structure which allows us
to perform the following operations in O(log N) time:
- add x to freq(i)
- compute freq(1) + freq(2) + ... + freq(i), the cumulative frequency of i.
- compute freq(i)
Additionally, you can simply implement the following functions:
- find the last i such that cum_freq(x) < t (assuming freq(j) >= 0 for all j)
- scale all frequencies by a constant
- extend the frequency tree to higher dimensions

Implementation:
Fenwick trees are implemented via a technique called BINARY INDEXING. The
reason for this name will become clear in a moment.

Consider some positive integer x. Define the PREDECESSOR of x to be x minus the
last nonzero bit of x. Similarly, we define the PARENT of x in the Fenwick tree
to be x plus the last nonzero bit of x. For example:

x (binary):                                predecessor (binary):                        parent (binary):
1<<n                                        0                                                                1<<(n+1)
3 (11)                                        2 (10)                                                        4 (100)
5 (101)                                        4 (100)                                                        6 (110)
12 (1100)                                8 (1000)                                                16 (10000)
42 (101010)                                40 (101000)                                                44 (101100)

If the Fenwick tree is of size N, then it should be clear that each integer in
the tree has at most O(log N) predecessors and parents.

Using bit operations, the predecessor of x is x&(x-1), and the parent of x is
(x|(x-1))+1. Another way to compute the predecessor and the parent is to
compute the last bit: x&(-x). Then
        x -= x&(-x);
moves x to its predecessor, and
        x += x&(-x);
moves x to its parent.

We store an array tree[x], where
        tree[x] = freq[y+1] + freq[y+2] + ... + freq[x]
where y is the predecessor of x. Then
        cum_freq(x) = tree[x] + cum_freq(y)
This computes cumulative frequency in O(log N) time.

We can also show the following:
        tree[x] = sum( tree[z], where z is a child of x )
This allows us to update freq(x) and tree[x] in O(log N) time also.

See implementation for more details about the other functions.

Links:
http://en.wikipedia.org/wiki/Fenwick_tree
http://community.topcoder.com/tc?module=Static&d1=tutorials&d2=binaryIndexedTrees
*/

// REMEMBER: INDICES ARE 1-BASED.
int tree[N+1];

// emulates freq[x] += amt
void add( int x, int amt )
{
        for( ; x <= N; x += x&(-x) ) tree[x] += amt;
}

// computes sum( freq[i] ), where 1 <= i <= x
int cum_freq( int x )
{
        int ans = 0;
        for( ; x; x -= x&(-x) ) ans += tree[x];
        return ans;
}

// emulates freq[x]
int freq( int x )
{
        int ans = tree[x], end = x - (x&(-x));
        for( x--; x > end; x -= x&(-x) ) ans -= tree[x];
        return ans;
}

// find last x such that cum_freq(x) < cf
int find( int cf )
{
        int ans = 0, del = 0;
        for( ans = N>>1; ans; ans >>= 1 ) del <<= 1;
        for( ans = 0; del; del >>= 1 ) if( ans+del <= N && tree[ans+del] < cf )
        {
                ans += del;
                cf -= tree[ans];
        }
        return ans;
}

// scale everything by c
void scale( int c )
{
        for( int i = 1; i <= N; i++ ) tree[i] *= c;
}




/*
Description:
We present several algorithms which solve the least common ancestor problem.


Method 1: Precomputing ancestors by powers of 2
O(N log N) preprocessing time
O(log N) time per query
O(N log N) memory

Pro: Easy to code.
Con: Larger memory requirement.


Method 2: Use range queries
O(N) preprocessing time
O(log N) time per query
O(N) memory

Pro: Time- and memory-efficient.
Con: Tricky to code: need to mess around with array lengths and other things.


Method 3: Offline algorithm using union-find (Tarjan's algorithm)
O(Q + N a(N)) total time amortised
O(N) memory
(Note: a(N) is the extremely slow growing inverse Ackermann function, which we
can take to be constant for all reasonable values of N.)

Pro: Extremely time- and memory-efficient. Easy to code.
Con: The algorithm is offline, so you need to know all the queries in advance.
*/


/*
Method 1: Precomputing ancestors by powers of 2
The main idea is to define p[i][v] to be the 2^i-th parent of node v.
Precomputation is a simple DP, and queries use binary search.

Here POW is taken such that 2^POW is the maximum depth of the tree.

To initialise the structure, you should call preprocess( root ).
*/

int p[POW][N], * par = p[0], d[N];
vector<int> g[N];

void preprocess( int v )
{
        for( int i = 1; i < POW; i++ ) p[i][v] = p[i-1][p[i-1][v]];
        for( int i = 0; i < g[v].size(); i++ )
        {
                int u = g[v][i];
                if( u == par[v] ) continue;
                par[v] = v;
                d[u] = d[v] + 1;
                preprocess(u);
        }
}

int lca( int a, int b )
{
        if( d[a] > d[b] ) a ^= b, b ^= a, a ^= b;
        if( d[a] < d[b] )
        {
                int del = d[b] - d[a];
                for( int i = 0; i < POW; i++ ) if( del & ( 1 << i ) ) b = p[i][b];
        }
        if( a != b )
        {
                for( int i = POW-1; i >= 0; i-- ) if( p[i][a] != p[i][b] ) a = p[i][a], b = p[i][b];
                a = par[a], b = par[b];
        }
        return a;
}



/*
Method 2: Using range queries
Consider a preorder traversal of the tree as in the dfs() routine below. If the
Then the lowest common ancestor of a and b is the node with minimum depth that
appears in the preorder traversal between index[a] and index[b]. This turns the
lowest common ancestor problem into a range query problem.

N should be a power of two for safety reasons.
*/

int n, d[N+1], index[N], tree[4*N], del, T;
vector<int> g[N];

void dfs( int v )
{
        index[v] = del+T;
        tree[del+T] = v;
        T++;
        for( int i = 0; i < g[v].size(); i++ )
        {
                int u = g[v][i];
                if( d[u] ) continue;
                d[u] = d[v] + 1;
                dfs(u);
                tree[del+T] = v;
                T++;
        }
}

void preprocess( int root )
{
        del = 1;
        int m = 2 * ( n - 1 );
        while( del < m ) del *= 2;

        d[root] = 1, d[N] = N;
        dfs(root);
        for( ; T < del; T++ ) tree[del+T] = N;

        for( int x = del - 1; x; x-- )
        {
                int y = tree[2*x], z = tree[2*x+1];
                tree[x] = d[y] < d[z] ? y : z;
        }
}

int lca( int a, int b )
{
        int ans = N;
        if( index[a] > index[b] ) a ^= b, b ^= a, a ^= b;
        for( int i = index[a], j = index[b]; i <= j; i = ( i + 1 ) / 2, j = ( j - 1 ) / 2 )
        {
                if( i % 2 == 1 && d[tree[i]] < d[ans] ) ans = tree[i];
                if( j % 2 == 0 && d[tree[j]] < d[ans] ) ans = tree[j];
        }
        return ans;
}



/*
Method 3: Offline algorithm using union-find (Tarjan's algorithm)
We use a union-find data structure in our dfs to maintain the lowest ancestor
for any given node during our traversal. Specifically, whenever dfs(v) is
called, then if done[u] is true, then pred[find(u)] stores the LCA of u and v.

Notes:
- q[v] contains u if and only if there is a query (v,u).
- d[root] should be set to 1.
- Call dfs( root ) to run the queries.

Union-find code can be found elsewhere.
*/

int d[N], p[N], pred[N];
vector<int> g[N], q[N];
bool done[N];

void dfs( int v )
{
        p[v] = -1;
        pred[v] = v;
        for( int i = 0; i < g[v].size(); i++ )
        {
                int u = g[v][i];
                if( d[u] ) continue;
                d[u] = d[v] + 1;
                dfs(u);
                merge( u, v );
                pred[find(v)] = v;
        }
        done[v] = true;

        for( int i = 0; i < q[v].size(); i++ )
        {
                int u = q[v][i];
                if( !done[u] ) continue;
                int lca = pred[find(u)];
                // At this point, lca is the least common ancestor of u and v.
        }
}


        map<pi,bool> seen;
        set<pi> q;
        q.insert( mp(0,0)) );
        while( !q.empty() )
        {
                int v = (*q.begin()).second;
                q.erase(q.begin());
                if( !seen[v] )
                {
                        seen[v] = true;
                        // add neighbors of v
                        for( int c = 0; c < n; c++ ) q.insert( mp(d[v][c]), c );
                }
        }



		/*
        Kosaraju's and Tarjan's algorithms for finding strongly connected components.
        (Note: Tarjan's needs to be rewritten at some point.)
*/

// BEGIN KOSARAJU'S (C++)

vector<int> g[MAX], h[MAX]; // graph, reverse graph
int n, c[MAX], nc, q[MAX], nq; // num nodes, component map, num components, queue, queue index
bool seen[MAX];

void dfs1( int x )
{
        if(seen[x]) return;
        seen[x] = true;
        rep(y,g[x].size()) dfs1(g[x][y]);
        q[nq++] = x;
}

void dfs2( int x )
{
        if(c[x]>-1) return;
        c[x] = nc;
        rep(y,h[x].size()) dfs2(h[x][y]);
}

        nq = 0;
        rep(i,n) seen[i] = false;
        rep(i,n) dfs1(i);

        nc = 0;
        rep(i,n) c[i] = -1;
        for( int i = n-1; i >= 0; i-- ) if(c[q[i]]==-1)
        {
                dfs2(q[i]);
                nc++;
        }

// END KOSARAJU'S (C++)