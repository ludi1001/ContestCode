#Problem statement:
"""
Huarongdao is a well-known game in China. The purpose of this game is to move the Cao Cao block out of the board.

Acme is interested in this game, and he invents a similar game. There is a N*M board. Some blocks in this board are movable, while some are fixed. There is only one empty position. In one step, you can move a block to the empty position, and it will take you one second. The purpose of this game is to move the Cao Cao block to a given position. Acme wants to finish the game as fast as possible.

But he finds it hard, so he cheats sometimes. When he cheats, he spends K seconds to pick a block and put it in an empty position. However, he is not allowed to pick the Cao Cao block out of the board .

Note

Immovable blocks cannot be moved while cheating.
A block can be moved only in the directions UP, DOWN, LEFT or RIGHT.
Input Format
The first line contains four integers N, M, K, Q separated by a single space. N lines follow.
Each line contains M integers 0 or 1 separated by a single space. If the jth integer is 1, then the block in ith row and jth column is movable. If the jth integer is 0 then the block in ith row and jth column is fixed. Then Q lines follows, each line contains six integers EXi, EYi, SXi, SYi, TXi, TYi separated by a single space. The ith query is the Cao Cao block is in row SXi column SYi, the exit is in TXi, TYi, and the empty position is in row EXi column EYi. It is guaranteed that the blocks in these positions are movable. Find the minimum seconds Acme needs to finish the game. If it is impossible to finish the game, you should answer -1.

Output Format
You should output Q lines, i-th line contains an integer which is the answer to i-th query.

Constraints

N,M ≤ 200
1 ≤ Q ≤ 250
10 ≤ K≤ 15
1 ≤ EXi, SXi, TXi≤ N
1 ≤ EYi, SYi,TYi ≤ M

Sample Input

5 5 12 1
1 1 1 1 1
1 1 1 1 1
0 1 1 1 1
1 1 1 1 1
0 1 0 1 1
1 5 4 3 4 1
Sample Output

20
Explanation

Move the block in (1, 4) to (1, 5);
Move the block in (1, 3) to (1, 4);
Move the block in (1, 2) to (1, 3);
Move the block in (2, 2) to (1, 2);
Move the block in (3, 2) to (2, 2);
Move the block in (4, 2) to (3, 2);
Move the block in (4, 3) to (4, 2);
Move the block in (4, 1) to (4, 3) by cheating;
Move the block in (4, 2) to (4, 1).

So, 1 + 1 + 1 + 1 + 1 + 1 + 1 + 12 + 1 = 20.
"""


#Solution Description:
"""
The main idea is to use Dijkstra to find the shortest path.

===Observations===
We motivate the following algorithm by making several observations. First, note that the state consists of the location of the kao kao and the location of the empty tile. Second, note that we cannot simply perform bfs on these states because of the large branching factor due to the possibility of cheating. To reduce the branching factor, we notice that we can make progress only by making the kao kao into the empty tile, meaning that the empty tile must be adjacent to the kao kao prior to moving the kao kao. In fact, right before (and after) moving the kao kao, the empty tile must be adjacent. This suggests that we can run Dijkstra on the states where the kao kao is adjacent to the empty tile, i.e. merge the state of the kao kao and the tile. Hereafter, the state consists of the location of the kao kao plus an index indicating the direction to which the empty tile is located (see declaration of DIRS).
e.g.
((4,3),0) means that the kao kao is at (4,3) and the empty tile at (4,4)

===Start States===
The start states are the kao kao location with the four possible directions of the empty tile. To account for the fact that the empty tile may not start out adjacent to the kao kao, each of the starting states has a nonzero cost computed in the similarly to the edge weights.

===Goal States===
The goal states have the empty tile in the goal position and the kao kao location adjacent.

===Edge Weights===
Edge weights are computed using BFS. To get from one state to another, only the empty tile can move. We thus treat the kao kao as an immovable tile and perform BFS on the empty tile to determine the cost from moving the empty tile from one location to another. Since we are allowed to cheat, this is a limited depth BFS with the depth limited to K. If BFS ever reaches past length K, it is best to cheat with cost K. Note that the true weight is the cost of moving the empty tile plus one because there is a cost associated with moving the kao kao into the empty tile (or rather, swapping the empty tile and the kao kao).
"""

from collections import deque
import heapq
class Heap:
  """Min-heap that allows for custom comparator"""
  def __init__(self, key):
    self.key = key
    self.data = []
  
  def push(self, item):
    heapq.heappush(self.data, (self.key(item), item))

  def pop(self):
    return heapq.heappop(self.data)[1]
    
  def __len__(self):
    return len(self.data)

DIRS = [(0,1),(1,0),(0,-1),(-1,0)]
N, M, K, Q = (int(x) for x in raw_input().split())
board = [] #True if movable
#parse in the board
for i in range(N):
  board.append([True if int(x) == 1 else False for x in raw_input().split()])

#helper functions to save typing
def legal_pos(pos):
  return pos[0] >= 0 and pos[0] < N and pos[1] >= 0 and pos[1] < M and board[pos[0]][pos[1]]
  
def pos_in_dir(pos, dir):
  return (pos[0] + DIRS[dir][0], pos[1] + DIRS[dir][1])
  
#bfs limited to K depth
def bfs(start, end):
  qu = deque([start])
  qu.append(None)
  length = 0
  visited = set([start])
  while len(qu) != 0:
    item = qu.popleft()
    if item == end:
      return length
    if item is None:
      length += 1
      if length == K:
        return K
      qu.append(None)
      continue
    for dir in DIRS:
      new_pos = (item[0] + dir[0], item[1] + dir[1])
      if legal_pos(new_pos):
        if new_pos not in visited:
          qu.append(new_pos)
          visited.add(new_pos)
  return K 

#computes edge weights, put in function to memoize  
memoize = {}
def get_cost(start_pos, empty_pos, end_empty_pos):
  global memoize
  #memoize
  key = (start_pos, empty_pos, end_empty_pos)
  if key in memoize:
    return memoize[key]
  global board
  board[start_pos[0]][start_pos[1]] = False
  cost = bfs(empty_pos, end_empty_pos)
  board[start_pos[0]][start_pos[1]] = True
  memoize[key] = cost
  return cost

for i in range(Q):
  init = [int(x) - 1 for x in raw_input().split()]
  empty = (init[0], init[1])
  start = (init[2], init[3])
  end = (init[4], init[5])
  
  #dijkstra
  min_cost = -1
  dist = {}
  def heap_key(x):
    return dist[x]
  qu = Heap(heap_key)
  visited = set()
  marked = set()
  
  #setup goal states
  end_states = []
  for j in range(len(DIRS)):
    end_pos = (end[0] + DIRS[j][0], end[1] + DIRS[j][1])
    if legal_pos(end_pos):
      end_states.append((end_pos, (j + 2) % 4))
  
  #setup start states
  for j in range(len(DIRS)):
    empty_start_pos = (start[0] + DIRS[j][0], start[1] + DIRS[j][1])
    if legal_pos(empty_start_pos):
      board[start[0]][start[1]] = 0
      bfs_cost = bfs(empty, empty_start_pos)
      board[start[0]][start[1]] = 1
      if bfs_cost == -1:
        continue
      dist[(start, j)] = bfs_cost
      qu.push((start,j))
      
  while len(qu) != 0:
    item = qu.pop()
    if item in end_states:
      min_cost = dist[item] + 1
      break
    if item in marked:
      continue
    marked.add(item)
    new_pos = pos_in_dir(item[0], item[1]) #block moves into empty pos
    for j in range(len(DIRS)):
      new_empty_pos = pos_in_dir(new_pos, j)
      if not legal_pos(new_empty_pos):
        continue
      if new_empty_pos == item[0]:
        continue
      cost = 1 + get_cost(new_pos, item[0], new_empty_pos)
      if cost == 0:
        continue
      new_item = (new_pos, j)
      if new_item not in dist or dist[new_item] > dist[item] + cost:
        dist[new_item] = dist[item] + cost
        qu.push(new_item)    
  print(min_cost)