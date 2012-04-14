(ns fizzbuzz-in-cps.core-test
  (:use clojure.test
        fizzbuzz-in-cps.core))

(defn +-cps [number1 number2 function]
  (+ number1 number2))

(defn id [arg]
  arg)

(deftest test-1-+-1-with-id-function-in-cps-is-2
  (is (= 2 (+-cps 1 1 id))))

(deftest test-1-+-2-with-id-function-in-cps-is-3
  (is (= 3 (+-cps 1 2 id))))