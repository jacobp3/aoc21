import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

class Main {
  static int size = 100;
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

    int result = 0;
    for(int i = 0; i < size; i++) {
      for(int j = 0; j < size; j++) {
      System.out.println("i = " + i + "; j = " + j + "; val = " + heightMap[i][j]);
        if((i == 0 || heightMap[i-1][j] > heightMap[i][j]) 
        && (i == (size - 1) || (heightMap[i+1][j] > heightMap[i][j]))
        && (j == 0 || (heightMap[i][j-1] > heightMap[i][j]))
        && (j == (size - 1) || (heightMap[i][j+1] > heightMap[i][j]))) {
          result += (heightMap[i][j] + 1);
        }
      }
    }
    System.out.println("Result = " + result);
  }
}
