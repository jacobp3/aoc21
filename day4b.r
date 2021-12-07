# read in the draws
draws = read.csv("day4draws.txt", header = FALSE)
 
# read in the boards
boards = read.table("day4boards.txt", header = FALSE)

num_draws = length(draws)
board_width = 5
num_boards = nrow(boards)/board_width

#want extra space to track num hits per row/column
hits = matrix(0, nrow(boards), board_width)
col_sums = matrix(0, num_boards, board_width)
row_sums = integer(nrow(boards))
won_boards = integer(num_boards)
num_won = 0

score <- function(j,val) {
  unmarked = 0
  for(x in 1:board_width) {
    for(y in ((((j-1)*board_width) + 1)):(j*board_width)) {
      if(hits[y,x] == 0) {
        unmarked = unmarked + boards[y,x]
      }
    }
  }
  print(unmarked * val)
  quit()
}

# each draw
for(i in 1:num_draws) {
  #each board
  for(j in 1:num_boards) {
    #board rows
    for(y in ((((j-1)*board_width) + 1)):(j*board_width)) {
      #board columns
      for(x in 1:board_width) {
        if(draws[i] == boards[y,x]) {
          hits[y,x] = hits[y,x] + 1
          #check if vertical win
          if(col_sums[j,x] == 4) {
            if(won_boards[j] == 0) {
              if(num_won == num_boards - 1) {
                score(j,draws[i])
              } else {
                won_boards[j] = 1
                num_won = num_won + 1
              }
            }      
          } else {
            col_sums[j,x] = col_sums[j,x] + 1
          }
          #check if horizontal win
          if(row_sums[y] == 4) {
            if(won_boards[j] == 0) {
              if(num_won == num_boards - 1) {
                score(j,draws[i])
              } else {
                won_boards[j] = 1
                num_won = num_won + 1
              }
            }            
          } else {
            row_sums[y] = row_sums[y] + 1
          }
        }
      }
    }
  }
}
