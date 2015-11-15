#include<stdio.h>
#define y(i)for(i=0;i<81;++i)
int b[81],k;int g(){int i,j,w=0;y(j)if(!b[j]){y(i)w|=1<<b[i/9*9+j%9]|1<<b[j/9*9+i/9]|1<<b[j/27*27+j%9/3*3+i/9+i/27*6];y(i)if(~w>>i&i<=9&&(b[j]=i)*g())return 1;return b[j]=0;}y(k)printf("%d%c",b[k],k%9-8?32:10);scanf("%d",k);return 1;}int main(){y(k)scanf("%d",b+k);return g();}