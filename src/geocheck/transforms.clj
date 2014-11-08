(ns geocheck.transforms
  (:require [geocheck.spatial :refer :all])
  (:import (com.vividsolutions.jts.geom Coordinate)
           (com.vividsolutions.jts.geom Coordinate)))

(defn convert-line [s]
  (->> (re-seq #"\d+\.\d+", s)
       (map #(Float/parseFloat %))
       (partition 2)
       (map to-coordinate)
       (into-array Coordinate)
       to-line-string))
