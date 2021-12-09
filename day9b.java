import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.*; // Import the Scanner class to read text files

class Main {
  static int size = 100;
  static HashSet<Integer> basin;
  static int big = 0;
  static int bigger = 0;
  static int biggest = 0;

  public static void mapBasin(int[][] hm, int i, int j) {
    if(hm[i][j] < 9) {
      basin.add(size*i + j);
      if(i > 0) {
        if(!basin.contains(size*(i-1) + j)) {
          mapBasin(hm, i - 1, j);
        }
      }
      if(i < (size - 1)) {
        if(!basin.contains(size*(i+1) + j)) {
          mapBasin(hm, i + 1, j);
        }
      }
      if(j > 0) {
        if(!basin.contains(size*i + j - 1)) {
          mapBasin(hm, i, j - 1);
        }
      }
      if(j < (size - 1)) {
        if(!basin.contains(size*i + j + 1)) {
          mapBasin(hm, i, j + 1);
        }
      }
    } 
  }

  public static void main(String[] args) {
    int[][] heightMap = new int[size][size];
    try {
      File myObj = new File("day9.txt");
      Scanner myReader = new Scanner(myObj);
      int lineNum = 0;
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        for(int i = 0; i < data.length(); i++) {
          heightMap[lineNum][i] = Character.getNumericValue(data.charAt(i));
        }
        lineNum++;
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
        if((i == 0 || heightMap[i-1][j] > heightMap[i][j]) 
        && (i == (size - 1) || (heightMap[i+1][j] > heightMap[i][j]))
        && (j == 0 || (heightMap[i][j-1] > heightMap[i][j]))
        && (j == (size - 1) || (heightMap[i][j+1] > heightMap[i][j]))) {
          basin = new HashSet<Integer>();
          mapBasin(heightMap, i, j);
          if(basin.size() > biggest) {
            big = bigger;
            bigger = biggest;
            biggest = basin.size();
          } else if(basin.size() > bigger) {
            big = bigger;
            bigger = basin.size();
          } else if(basin.size() > big) {
            big = basin.size();
          }
        }
      }
    }
    System.out.println("Result = " + (big*bigger*biggest));
  }
}
