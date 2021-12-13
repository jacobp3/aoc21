use std::fs;
use std::collections::HashSet;

fn main() {
  let mut coords = [[0u32; 2]; 2000];
  let mut folds = [[0u32; 2]; 12];

  //parse the coordinates
  let contents = fs::read_to_string("src/day13.txt")
        .expect("Something went wrong reading the file");
  let mut i = 0;
  for line in contents.lines() {
    let mut j = 0;
    for s in line.split(",") {
      coords[i][j] = s.parse().expect("Not a number!");
      j += 1;
    }
    i += 1;
  }
  let count = i;

  //parse the folds
  let contents2 = fs::read_to_string("src/day13.2.txt")
        .expect("Something went wrong reading the file");
  i = 0;
  for line in contents2.lines() {
    let mut j = 0;
    for s in line.split("=") {
      if s.eq("x") {
        folds[i][j] = 0;
      } else if s.eq("y") {
        folds[i][j] = 1;
      } else {
        folds[i][j] = s.parse().expect("Not a number");
      }
      j += 1;
    }
    i += 1;
  }

  //perform the folds
  for f in &folds {
    println!("folding:{}",f[1]);
    for k in 0..count {
      if f[0] == 0 {
        if coords[k][0] > f[1] {
          coords[k][0] = 2 * f[1] - coords[k][0];
        }
      } else {
        if coords[k][1] > f[1] {
          coords[k][1] = 2 * f[1] - coords[k][1];
        }
      }
    } 

    //dedupe
    let mut dots : HashSet<u32> = HashSet::new();
    for c in &coords {
      dots.insert(c[0]*2000 + c[1]);
    }
    
    println!("length: {}",dots.len());
  }
}
