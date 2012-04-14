(ns fizzbuzz-in-cps.core-test
  (:use clojure.test
        fizzbuzz-in-cps.core))

(defn +-cps [number1 number2 function]
  2)

(deftest test-1-+-1-with-id-function-in-cps-is-2
  (is (= 2 (+-cps 1 1
                  (fn [res] res)))))
