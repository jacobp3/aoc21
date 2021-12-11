import scala.io.Source
import scala.collection.immutable

object Main {
  def main(args: Array[String]): Unit = {
    var grid = Array[Array[Int]]()
    val filename = "day11.txt"
    for (line <- Source.fromFile(filename).getLines) {
      grid ++= Array(line.toCharArray().map(_.asDigit))
    }
    println(grid(5)(5))
    grid = grid.map(_.map(x => x+1))
    println(grid(5)(5))
    for(row <- 0 until grid.length) {
      for(col <- 0 until row.length) {
        if(grid(row)(col) >= 9) {
          println(grid(row)(col))
        }
      }
    }
  }
}
