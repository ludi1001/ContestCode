N, C = (int(x) for x in raw_input().split())
a = [int(x) for x in raw_input().split()]
b = [int(x) for x in raw_input().split()]
fmin_g = [0]*N
fmax_g = [0]*N
fc = [0]*N
g = 0
forward_min_g = 0
for i in xrange(0, N):
  fmin_g[i] = min(fmin_g[(i-1)%N], g)
  g += a[i]
  fmax_g[i] = max(fmax_g[(i-1)%N],g)
  fc[i] = g
  g -= b[i]
  if g < forward_min_g:
    forward_min_g = g
leftover = forward_min_g
print(fc)
print(fmin_g)
print(fmax_g)

bmin_g = [0]*N
bmax_g = [0]*N
bc = [0]*N
g = 0
for i in xrange(N-2,-1,-1):
  bc[i] = g
  bmin_g[i] = min(bmin_g[i+1],g)
  g += b[i]
  bmax_g[i] = max(bmax_g[i+1], g)
  g -= a[i]
print(bmin_g)
print(bmax_g)

count = 0
for i in xrange(N):
  forward_min = fmin_g[i] + a[i] - fc[i] + leftover
  backward_min = bmin_g[i] + a[i] - bc[i]
  true_min = min(forward_min, backward_min)
  
  forward_max = fmax_g[i] + a[i] - fc[i] + leftover
  backward_max = bmax_g[i] + a[i] - bc[i]
  true_max = max(forward_max, backward_max)
  if true_min >= 0:
    count += 1
print(count)