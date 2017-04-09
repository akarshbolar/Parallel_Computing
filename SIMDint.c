#include<stdio.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
int main()
{
	int nn = 1000000000;
	char *arr= (char *)malloc(nn*sizeof(char));
	char *arr2= (char *)malloc(nn*sizeof(char));	
	clock_t start,end;
	double cpu_time;
	for(int i =0;i<nn;i++)
	{
		arr[i]='0';
		arr2[i]='0';
	}
	arr[999999999]='1';
	int n = strlen(arr2);
	
	int *iarr = (int *)arr;
	int *iarr2 = (int *)arr2;
	start = clock();
	for(int i = 0;i<n/sizeof(int);i++)
	{
		if(iarr[i]!=iarr2[i])
		{
			//printf("%d\n",i);
			printf("breaking \n");
			break;
		}
		
	}
	
	if(n%sizeof(int)!=0)
	{
		for(int i = n-n%sizeof(int);i<n;i++)
		{
			if(arr[i]!=arr2[i])
			{
				printf("breaking\n");
				break;
			}
		}
	}
	end = clock();
	cpu_time = (double)(end-start);
	printf("Time taken for array comparison using int pointer: %f milliseconds",cpu_time);
	
}