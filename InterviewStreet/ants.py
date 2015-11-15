"""
There is one friendly number and N unfriendly numbers. We want to find how many numbers are there which exactly divide the friendly number, but does not divide any of the unfriendly numbers.

Input Format:
The first line of input contains two numbers N and K seperated by spaces. N is the number of unfriendly numbers, K is the friendly number.
The second line of input contains N space separated unfriendly numbers.
 
Output Format:
Output the answer in a single line.
 
Constraints:
1 <= N <= 10^6
1 <= K <= 10^13
1 <= unfriendly numbers <= 10^18
 
Sample Input:
8 16
2 5 7 4 3 8 3 18
 
Sample Output:
1
 
Explanation :
Divisors of the given friendly number 16, are { 1, 2, 4, 8, 16 } and the unfriendly numbers are {2, 5, 7, 4, 3, 8, 3, 18}. Now 1 divides all unfriendly numbers, 2 divide 2, 4 divide 4, 8 divide 8 but 16 divides none of them. So only one number exists which divide the friendly number but does not divide any of the unfriendly numbers. So the answer is 1.   
"""

"""
Solution:
First consider the game in 10s intervals. In this case, each ant moves exactly 1m per turn.
Note the two conditions under which ants will collide:
* + * - *
* + - * *
where + represents ant moving to right, - represents ants moving to left and * represents blank.
In the former case, after two turns, the resulting position is:
* - * + *
In the latter case, after one turn, the resulting position is:
* - + * *
Notice that these positions are the same as if the ants did NOT turn around and simply passed each other.
This means we can solve the problem where the ants only pass each other but do not change direction.
Now note that after 1000 turns, the ant must return to its original spot so therefore the entire track must look identical every 1000 turns.
Moreover, in that time, ants moving in same direction cannot cross each other.
Two ants moving in opposite directions will pass each other twice.
Thus, in 1000 turns,
# of passes = 2 * # of ants moving clockwise * # of ants moving counterclockwise
We immediately note that # of passes is maximized when # of ants moving clockwise is as close to # of ants moving counterclockwise as possible.
Next, we simply multiple this number by 10^5 to get what happens in 10^9 seconds (it takes 10^4 seconds for 1000 turns).
All that remains is to determine number of passes during last 6 seconds.
In that time, only ants adjacent to each other can pass each other.
Thus, we simply calculate the "runs" of ants, where a run consists of n number of ants in a row.
The number of passes generated from such a run is 2 * floor(n / 2).
Note that for a run we assign directions by alternating between clockwise/counterclockwise.
This means we don't actually have to keep track of number of clockwise and number of counterclockwise
because we can always find an assignment that keeps number of clockwise and number of counterclockwise as close as possible.
"""
N = int(raw_input())
locs = [int(x) for x in raw_input().split(" ")]
locs.sort()
s = 0
if N % 2 == 0:
	s += (N / 2) * (N / 2)
else:
	s += (N / 2) * (N / 2 + 1)
s *= 100000 * 2
count = 0
last_index = N - 1
if locs[0] == 0:
	if locs[last_index] == 999:
		while locs[last_index] - locs[last_index - 1] == 1:
			last_index -= 1
		count += N - last_index
i = 0
while i < last_index:
	while i < last_index and locs[i + 1] - locs[i] == 1:
		i += 1
		count += 1
	count += 1
	s += count//2
	count = 0
	i += 1
s *= 2
print s