#include <stdlib.h>
#include <stdio.h>

int grafo[8][8];
int vet[8];
int pos=1;


void percorrer(int i){
	int j = 0;
	int count = 0;
	int flag = 0;
	
	for(j=0;j<8;j++){
		if(grafo[i][j]!=0){
			flag=0;
			for(count=0;count<8;count++){
				if(vet[count]==j+1){
					flag=1;
					break;
				}
			}
			if(flag==0){
				vet[pos]=j+1;
				pos++;
				percorrer(j);
			}
		}
	}
		
}



main(){
	
	int i;
	int j;
	
	for(i=0;i<8;i++){
		vet[i]=0;
	}
	
	for(i=0;i<8;i++){
		for(j=0;j<8;j++){
			if(i==j){
				grafo[i][j]=0;
			}else{
			system("cls");
			printf("Qual a incidencia o no %d tem no no %d? ", i+1, j+1);
			scanf(" %d", &grafo[i][j]);
			}
		}
	}
	
	vet[0]=1;
	percorrer(0);
	
	printf("\nVetor:\n");
	for(i=0;i<8;i++){
		printf("%d ", vet[i]);
	}
	
	return 0;
}

