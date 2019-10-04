(ns onko.core-test
  (:require [clojure.test :refer :all]
            [onko.core :refer :all]))


(deftest check?-test
  (testing "check? verifies values safely"
    (is (true? (check? string? "dave")))
    (is (true? (check? int? 34)))
    (is (false? (check? string? 34)))
    (is (false? (check? int? "dave")))))

(deftest check-throw!-test
  (testing "check-throw! throws on failed checks"
    (is (thrown? Exception (check-throw! string? 34)))
    (is (thrown-with-msg? Exception #"Type error" (check-throw! int? "dave")))))

(deftest map?-test
  (testing "map? creates a check function that validates a map against the given model"
    (let [foo? (map? {:foo int? :bar string?})]
      (is (true? (check? foo? {:foo 34 :bar "dave"})))
      (is (false? (check? foo? {:foo 34 :bar 42}))))))
