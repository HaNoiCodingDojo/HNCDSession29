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

(deftest test-fizzbuzz-1-returns-0-fizz-0-buzz
  (is (= [0 0] (fizzbuzz-cps 1 id))))