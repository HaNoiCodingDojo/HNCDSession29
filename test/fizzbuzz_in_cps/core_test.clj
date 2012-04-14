(ns fizzbuzz-in-cps.core-test
  (:use clojure.test
        fizzbuzz-in-cps.core))

(defn +-cps [number1 number2 function]
  (function (+ number1 number2)))

(defn id [arg]
  arg)

(deftest test-1-+-1-with-id-function-in-cps-is-2
  (is (= 2 (+-cps 1 1 id))))

(deftest test-1-+-2-with-id-function-in-cps-is-3
  (is (= 3 (+-cps 1 2 id))))

(deftest test-1-+-2-with-divided-by-2-function-in-cps-is-3_2
  (is (= (/ 3 2) (+-cps 1 2 (fn [res]
                        (/ res 2))))))