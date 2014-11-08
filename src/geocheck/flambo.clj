(ns geocheck.flambo
  (:require [geocheck.spatial :refer :all]
            [geocheck.transforms :refer :all]
            [flambo.api :as f]
            [flambo.conf :as fconf])
  (:gen-class))

(def c (->
         (fconf/spark-conf)
         (fconf/master "local[*]")
         (fconf/app-name "geocheck")))

(defonce sc (f/spark-context c))

(defn process-lines []
  (-> (f/text-file sc "data.txt")
      (f/map (f/fn [s] (convert-line s)))
      (f/reduce (f/fn [x y] (+ x y)))))


