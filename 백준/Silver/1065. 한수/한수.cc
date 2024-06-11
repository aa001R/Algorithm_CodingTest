#include <stdio.h>

void Print_x(int x){
	int cnt = 0;
	for(int i = 1; i <= x ; i++){
		if (i < 100) 
			cnt++;
		else if (i < 1000){
			int b = (i%1000) / 100;
			int c = (i % 100) / 10;
			int d = i % 10;
			
			if((b-c) == (c-d))
				cnt++;
		}
	}
	printf("%d", cnt);
	return ;
}

int main(){
	int X;
	scanf("%d", &X);
	Print_x(X);
	return 0;
}