package main

import (
    "bufio"
    "fmt"
    "log"
    "os"
    "strings"
    "strconv"
)

func main() {
    file, err := os.Open("day7.txt")
    if err != nil {
        log.Fatal(err)
    }
    defer file.Close()

    var y [1000]int64 = [1000]int64{}
    var bestSum int64 = 9999999999999
    var bestPos int = -1
    var sum int64 = 0
    scanner := bufio.NewScanner(file)
    for scanner.Scan() {
      s := strings.Split(scanner.Text(), ",")
      for i := 0; i < 1000; i++ {
        sum = 0
        for j, a := range s {
          val, err := strconv.Atoi(a)
          var absdiff int64 = int64(i) - int64(val)
          if (absdiff < 0) {
            absdiff *= -1
          }
          sum += ((absdiff) * (absdiff + 1) / 2)
          if(err != nil) {
            fmt.Println(j, a, err)
          }
        }
        y[i] = sum
        fmt.Println(i,sum,bestPos,bestSum)
        if (sum < bestSum) {
          bestSum = sum
          bestPos = i
        }
      }
    }

    fmt.Println(bestPos)

    if err := scanner.Err(); err != nil {
        log.Fatal(err)
    }
}
