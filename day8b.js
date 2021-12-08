var lineReader = require('readline').createInterface({
  input: require('fs').createReadStream('day8.txt')
});

var zero;
var one;
var two;
var three;
var four;
var five;
var six;
var seven;
var eight;
var nine;
var sizeFives;
var sizeSixes;
var t;
var ul;
var ur;
var m;
var ll;
var lr;
var b;

function init(all_words) {
  sizeFives = []
  sizeSixes = []
  all_words.forEach(e => {
    if(e.length == 2) {
      one = e
    } else if(e.length == 3) {
      seven = e
    } else if(e.length == 4) {
      four = e
    } else if(e.length == 5) {
      sizeFives.push(e)
    } else if(e.length == 6) {
      sizeSixes.push(e)
    } else {
      eight = e
    }
  });
  //console.log(eight)
}

function findT() {
  for (var i = 0; i < seven.length; i++) {
    found = false
    for (var j = 0; j < one.length; j++) {
      if(seven[i] == one[j]) {
        found = true
      }
    }
    if(!found) {
      t = seven[i]
    }
  }
  //console.log(t)
}

function findURLR() {
  sizeSixes.forEach(e => {
    var matches = 0
    var matched
    for (var i = 0; i < e.length; i++) {
      for (var j = 0; j < one.length; j++) {
        if(e[i] == one[j]) {
          matches++
          matched = e[i]
        }
      }
    }
    if(matches == 1) {
      six = e
      for (var j = 0; j < one.length; j++) {
        if (one[j] != matched) {
          ur = one[j]
          lr = matched
        }
      }
    }
  });
}

function findLL() {
  sizeFives.forEach(e => { 
    var matched = 0
    var missingChar
    for (var i = 0; i < six.length; i++) {
      var foundThis = false
      for (var j = 0; j < e.length; j++) {
        if(six[i] == e[j]) {
          matched++
          foundThis = true
        } 
      }
      if (!foundThis) {
        missingChar = six[i]
      }
    }
    if(matched == 5) {
      ll = missingChar
      five = e
    }
  });
}

function findUL() {
  for (var i = 0; i < five.length; i++) {
    var matched = 0
    sizeFives.forEach(e => { 
      if(e != five) {
        for (var j = 0; j < e.length; j++) {
          if(five[i] == e[j]) {
            matched++
          }
        }
      }
    });
    if(matched == 0) {
      ul = five[i]
    }
  }
}

function findNineZero() {
  sizeSixes.forEach(e => {
    if(e != six) {
      matched = 0
      for(var i = 0; i < five.length; i++) {
        for (var j = 0; j < e.length; j++) {
          if(five[i] == e[j]) {
            matched ++
          }
        }
      }
      if(matched == 5) {
        nine = e
      } else {
        zero = e
      }
    }
  });
}

function findM() {
  for(var i = 0; i < eight.length; i++) {
    found = false
    for (var j = 0; j < zero.length; j++) {
      if(eight[i] == zero[j]) {
        found = true
      }
    }
    if(!found) {
      m = eight[i]
    }
  }
}

function findB() {
  for(var i = 0; i < eight.length; i++) {
    if(eight[i] != t 
    && eight[i] != ul 
    && eight[i] != ur
    && eight[i] != m
    && eight[i] != ll
    && eight[i] != lr) {
      b = eight[i]
    }
  }
}

function findTwoThree() {
  sizeFives.forEach(e => { 
    if(e != five) {
      for(var i = 0; i < e.length; i++) {
        if(e[i] == ll) {
          two = e
        } else if (e[i] == lr) {
          three = e
        }
      }
    }
  });
}

function sameWord(a,b) {
  if (a.length != b.length) {
    return false
  }
  for(var i = 0; i < a.length; i++) {
    found = false
    for (var j = 0; j < b.length; j++) {
      if(a[i] == b[j]) {
        found = true
        break
      }
    }
    if(!found) {
      return false
    }
  }
  return true
}

function calc_words(target_words) {
  result = 0
  target_words.forEach(e => {
    if(sameWord(e,zero)) {
      result = result * 10
    } else if(sameWord(e,one)) {
      result = result * 10 + 1
    } else if(sameWord(e,two)) {
      result = result * 10 + 2
    } else if(sameWord(e,three)) {
      result = result * 10 + 3
    } else if(sameWord(e,four)) {
      result = result * 10 + 4
    } else if(sameWord(e,five)) {
      result = result * 10 + 5
    } else if(sameWord(e,six)) {
      result = result * 10 + 6
    } else if(sameWord(e,seven)) {
      result = result * 10 + 7
    } else if(sameWord(e,eight)) {
      result = result * 10 + 8
    } else if(sameWord(e,nine)) {
      result = result * 10 + 9
    }
  });
//  console.log(result)
  return result
}

grand_total = 0

lineReader.on('line', function (line) {
  const all_words = line.split(' | ')[0]
  const target_words = line.split('|')[1]
  init(all_words.split(' '))
  findT()
  findURLR()
  findLL()
  findUL()
  findNineZero()
  findM()
  findB()
  findTwoThree()
  grand_total = grand_total + calc_words(target_words.split(' '))
  console.log(grand_total)    
});
