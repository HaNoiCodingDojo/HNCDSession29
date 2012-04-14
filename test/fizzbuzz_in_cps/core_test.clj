(ns fizzbuzz-in-cps.core-test
  (:use clojure.test
        fizzbuzz-in-cps.core))

(defn +-cps [number1 number2 function]
  (function (+ number1 number2)))

(defn +-cps-3 [number1 number2 number3 function]
  (+-cps number1 number2
         (fn [res] (+-cps res number3 function))))

(defn id [arg]
  arg)

(deftest test-1-+-1-with-id-function-in-cps-is-2
  (is (= 2 (+-cps 1 1 id))))

(deftest test-1-+-2-with-id-function-in-cps-is-3
  (is (= 3 (+-cps 1 2 id))))

(deftest test-1-+-2-with-divided-by-2-function-in-cps-is-3_2
  (is (= (/ 3 2) (+-cps 1 2 (fn [res]
                              (/ res 2))))))

(deftest test-1+1+1-with-id-function-in-cps-is-3
  (is (= 3 (+-cps-3 1 1 1 id))))

(deftest test-1+2+3-with-id-function-in-cps-is-6
  (is (= 6 (+-cps-3 1 2 3 id))))

(deftest test-1+2+3-with-div-2-function-in-cps-is-3
  (is (= 3 (+-cps-3 1 2 3 (fn [res]
                            (/ res 2))))))

(defn count-buzz-cps [number function]
  (function (cond (= number 7) 1
                  (= number 6) 1
                  (= number 5) 1
                  (= number 4) 0               
                  (= number 3) 0
                  (= number 2) 0
                  (= number 1) 0)))

(defn count-fizz-cps [number function]
  (function (cond (= number 7) 2
                  (= number 6) 2
                  (= number 5) 1
                  (= number 4) 1
                  (= number 3) 1
                  (= number 2) 0
                  (= number 1) 0)))

(defn fizzbuzz-cps [number function]
  (function [(count-fizz-cps number id)
             (count-buzz-cps number id)]))

(deftest test-fizzbuzz-1-returns-0-fizz-0-buzz
  (is (= [0 0] (fizzbuzz-cps 1 id))))

(deftest test-fizzbuzz-1-with-total-returns-0
  (is (= 0 (fizzbuzz-cps 1 (fn [res] (apply + res))))))

(deftest test-fizzbuzz-3-returns-1-fizz-0-buzz
  (is (= [1 0] (fizzbuzz-cps 3 id))))

(deftest test-fizzbuzz-4-returns-1-fizz-0-buzz
  (is (= [1 0] (fizzbuzz-cps 4 id))))

(deftest test-fizzbuzz-5-returns-1-fizz-1-buzz
  (is (= [1 1] (fizzbuzz-cps 5 id))))

(deftest test-fizzbuzz-6-returns-2-fizzes-1-buzz
  (is (= [2 1] (fizzbuzz-cps 6 id))))

(deftest test-fizzbuzz-7-returns-2-fizzes-1-buzz
  (is (= [2 1] (fizzbuzz-cps 7 id))))

(deftest test-fizzbuzz-2-returns-0-fizz-0-buzz
  (is (= [0 0] (fizzbuzz-cps 2 id))))

