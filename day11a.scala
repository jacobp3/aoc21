import scala.io.Source
import scala.collection.immutable
import scala.collection.mutable.Queue

object Main {
  def main(args: Array[String]): Unit = {
    var grid = Array[Array[Int]]()
    var flashers = Queue[(Int,Int)]()
    val filename = "day11.txt"
    for (line <- Source.fromFile(filename).getLines) {
      grid ++= Array(line.toCharArray().map(_.asDigit))
    }
    for(row <- 0 until grid.length) {
      for(col <- 0 until grid(row).length) {
        grid(row)(col) = grid(row)(col) + 1
        if(grid(row)(col) == 9) {
          flashers += ((row,col))
        }
      }
    }
    while(!flashers.isEmpty) {
      f = flashers.dequeue
      grid(row)(col) = 0
      //dostuff
    }
  }
}
