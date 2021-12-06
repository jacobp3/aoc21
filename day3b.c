#include <stdio.h>
#include <string.h>

int LENGTH = 5;
char *inputFile = "day3simple.txt";

int toDecimal (char *row) {
  int retVal = 0;
  for(int i=0; i < LENGTH; ++i){
    retVal = retVal*2;
    if(row[i] == '1') {
      retVal++;
    } 
  }
  return retVal;
}

int main(void) {
  FILE *input;
  char buff[255];
  int lines = 0;
  int oxygenCount = 0;
  int coTwoCount = 0;
  char *oxygenPrefix = "";
  char *coTwoPrefix = "";
  int numOxygenMatched = 0;
  int numCoTwoMatched = 0;
  char *oxygenResult = "x";
  char *coTwoResult = "x";

  for(int i = 0; i < LENGTH; i++) {
    if(!strcmp(oxygenResult, "x") && !strcmp(coTwoResult, "x")) {
      break;
    }
    input = fopen(inputFile, "r");

    while(fgets(buff, 255, (FILE*)input) > 0) {
      lines++;
      if(strcmp(oxygenResult, "x")) {
        int match = 1;
        for(int j = 0; j < i; j++) {
          if(buff[j] != oxygenPrefix[j]) {
            match = 0;
            break;
          }
        }
        if(match) {
          if(numOxygenMatched == 0) {
            oxygenResult = buff;
          }
          numOxygenMatched++;
          if(buff[i] == '1') {
            oxygenCount++;
          }
        }
      }
      if(strcmp(coTwoResult, "x")) {
        int match = 1;
        for(int j = 0; j < i; j++) {
          if(buff[j] != coTwoPrefix[j]) {
            match = 0;
            break;
          }
        }
        if(match) {
          if(numCoTwoMatched == 0) {
            coTwoResult = buff;
          }
          numCoTwoMatched++;
          if(buff[i] == '1') {
            coTwoCount++;
          }
        }
      }
    }

    fclose(input);

    if(oxygenCount >= (lines/2)) {
      strcat(oxygenPrefix,"1");
    } else {
      strcat(oxygenPrefix,"0");
    }

    if(coTwoCount >= (lines/2)) {
      strcat(coTwoPrefix,"0");
    } else {
      strcat(coTwoPrefix,"1");
    }
  }

  printf("oxygen result: %s\n", oxygenResult);
  printf("coTwo result: %s\n", coTwoResult);
}
