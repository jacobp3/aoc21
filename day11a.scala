import scala.io.Source
import scala.collection.immutable
import scala.collection.mutable.Queue

object Main {
  def main(args: Array[String]): Unit = {
    var grid = Array[Array[Int]]()
    var flashers = Queue[(Int,Int)]()
    var result = 0
    val filename = "day11.txt"
    val days = 100
    for (line <- Source.fromFile(filename).getLines) {
      grid ++= Array(line.toCharArray().map(_.asDigit))
    }
    for(n <- 1 until days) {
      for(row <- 0 until grid.length) {
        for(col <- 0 until grid(row).length) {
          grid(row)(col) = grid(row)(col) + 1
          if(grid(row)(col) == 9) {
            flashers += ((row,col))
          }
        }
      }
      while(!flashers.isEmpty) {
        var f = flashers.dequeue
        result++
        var r = f.row
      
        grid(row)(col) = 0
        for(i <- (row - 1) until (row + 1) {
          for(j <- (col - 1) until (col + 1)) {
            if(0 <= i && i < grid.length && 0 <= j && j < grid.length) {
              val x = grid(i)(j)
              if(x > 0 && x < 9) {
                if(x == 8) {
                  flashers += ((i,j))
                }
                grid(i)(j) = x + 1
              }
            }
          }
        }
      }
    }
    println(result)
  }
}
