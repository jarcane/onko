(ns onko.core
  (:require [clojure.repl :refer [demunge]]))

(defn- pretty-demunge
  "https://stackoverflow.com/questions/22116257/how-to-get-functions-name-as-string-in-clojure#22116649"
  [fn-object]
  (let [dem-fn (demunge (str fn-object))
        pretty (second (re-find #"(.*?\/.*?)[\-\-|@].*" dem-fn))]
    (if pretty pretty dem-fn)))

(defn check?
  "Check that the given val satisfies pred?"
  [pred? val]
  (pred? val))

(defn check-throw!
  "Checks val against pred? and throws error on failure"
  [pred? val]
  (if-not (pred? val)
    (throw 
     (ex-info (str "Type error: " val " does not satisfy " (pretty-demunge pred?))
              {:expected (pretty-demunge pred?)
               :recieved val}))))

