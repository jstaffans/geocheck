(ns geocheck.flambo
  (:require [geocheck.core :refer :all]
            [geocheck.spatial :refer :all]
            [geocheck.transforms :refer :all]
            [flambo.api :as f]
            [flambo.conf :as fconf])
  (:gen-class))

(def c (->
         (fconf/spark-conf)
         (fconf/master "local[*]")
         (fconf/app-name "geocheck")))

(defonce sc (delay (f/spark-context c)))

(defn process-random-flambo
  []
  (let [line-string-pairs (f/parallelize @sc (take 10000 (random-line-strings 500)))]
    (-> line-string-pairs
        (f/map (f/fn [[a b]] (similarity a b)))
        f/collect
        clojure.pprint/pprint)))

; REPL
(comment
  (process-random-flambo))

