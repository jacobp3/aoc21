#include <stdio.h>
#include <string.h>

int LENGTH = 12;
char *inputFile = "day3.txt";

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
  int oxygenLines;
  int coTwoLines;
  int oxygenCount;
  int coTwoCount;
  char oxygenPrefix[LENGTH];
  strcpy(oxygenPrefix, "");
  char coTwoPrefix[LENGTH];
  strcpy(coTwoPrefix, "");
  int numOxygenMatched;
  int numCoTwoMatched;
  char oxygenResult[LENGTH];
  char coTwoResult[LENGTH];

  for(int i = 0; i < LENGTH; i++) {
    if((numOxygenMatched <=2) && (numCoTwoMatched <= 2)) {
      break;
    }

    numOxygenMatched = 0;
    numCoTwoMatched = 0;
    oxygenCount = 0;
    coTwoCount = 0;
    oxygenLines = 0;
    coTwoLines = 0;

    input = fopen(inputFile, "r");

    while(fgets(buff, 255, (FILE*)input) > 0) {
      int match = 1;
      for(int j = 0; j < i; j++) {
        if(buff[j] != oxygenPrefix[j]) {
          match = 0;
          break;
        }
      }
      if(match) {
        oxygenLines++;
        if(numOxygenMatched == 0) {
          strcpy(oxygenResult,buff);
        } else if(numOxygenMatched == 1 && buff[i] == '1') {
          strcpy(oxygenResult,buff);
        }
        numOxygenMatched++;
        if(buff[i] == '1') {
          oxygenCount++;
        }
      }
      match = 1;
      for(int j = 0; j < i; j++) {
        if(buff[j] != coTwoPrefix[j]) {
          match = 0;
          break;
        }
      }
      if(match) {
        coTwoLines++;
        if(numCoTwoMatched == 0) {
          strcpy(coTwoResult,buff);
        } else if(numCoTwoMatched == 1 && buff[i] == '0') {
          strcpy(coTwoResult,buff);
        }
        numCoTwoMatched++;
        if(buff[i] == '1') {
          coTwoCount++;
        }
      }
    }

    fclose(input);
    if(oxygenCount*2 >= (oxygenLines)) {
      strcat(oxygenPrefix,"1");
    } else {
      strcat(oxygenPrefix,"0");
    }

    if(coTwoCount*2 >= (coTwoLines)) {
      strcat(coTwoPrefix,"0");
    } else {
      strcat(coTwoPrefix,"1");
    }
  }

  printf("oxygen result: %i\n", toDecimal(oxygenResult));
  printf("coTwo result: %i\n", toDecimal(coTwoResult));
  printf("final answer: %i\n", toDecimal(oxygenResult) * toDecimal(coTwoResult));
}
