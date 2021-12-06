; m is days since spawn, so 4 would be 2, 6 would be 0, etc.
(define (f m n)
(if (< n (- 9 m))
  (if (< n (- 7 m))
    1
    2)
  (+ (f 0 (- n (- 7 m))) 
     (f 0 (- n (- 9 m))))))

; 3,4,3,1,2 for 18 days
(+ 
  (+ 
    (+ 
      (* 
        2 
        (f 3 18)) 
      (f 2 18)) 
    (f 5 18))
  (f 4 18))

(+
  (+ 
    (+ 
      (+ 
        (* 
          69 
          (f 5 80)) 
        (* 
          60
          (f 4 80))) 
      (*
        45
        (f 3 80)))
    (* 
      67
      (f 2 80)))
  (*
    59
    (f 1 80)))
