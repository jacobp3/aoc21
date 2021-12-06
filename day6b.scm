(define v (make-vector 256 0))

; m is days since spawn, so 4 would be 2, 6 would be 0, etc.
(define (f m n)
(if (< n (- 9 m))
  (if (< n (- 7 m))
    1
    2)
  (+ (cache (- n (- 7 m))) 
     (cache (- n (- 9 m))))))

(define (cache x) 
  (if (= (vector-ref v x) 0)
      (vector-set! v x (f 0 x)))
  (vector-ref v x)
)

(+
  (+ 
    (+ 
      (+ 
        (* 
          69 
          (f 5 256)) 
        (* 
          60
          (f 4 256))) 
      (*
        45
        (f 3 256)))
    (* 
      67
      (f 2 256)))
  (*
    59
    (f 1 256)))
