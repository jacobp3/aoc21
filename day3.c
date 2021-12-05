#include <stdio.h>

int main(void) {
  int LENGTH = 12;

  FILE *fp;
  char buff[255];
  int counts[LENGTH];  
  int lines = 0;

  for(int i=0; i < LENGTH; ++i){
    counts[i] = 0;
  }

  fp = fopen("day3.txt", "r");

  while(fgets(buff, 255, (FILE*)fp) > 0) {
    lines++;
    for(int i=0; i < LENGTH; ++i){
      if(buff[i] == '1') {
        counts[i]++;
      }
    }
  }
  
  printf("lines: %i\n", lines);
  int gamma = 0;
  int epsilon = 0;
  for(int i=0; i < LENGTH; ++i){
    if(counts[i] > (lines/2)) {
      gamma = gamma*2 + 1;
      epsilon = epsilon*2;
    } else {
      gamma = gamma*2;
      epsilon = epsilon*2 + 1;
    }
  }
  int power = gamma * epsilon;

  printf("gamma: %i\n", gamma);
  printf("epsilon: %i\n", epsilon);
  printf("power: %i\n", power);

  fclose(fp);
}
