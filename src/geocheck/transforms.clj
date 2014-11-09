(ns geocheck.transforms
  (:require [geocheck.spatial :refer :all])
  (:import (com.vividsolutions.jts.geom Coordinate)
           (com.vividsolutions.jts.geom Coordinate)))

(defn xys-to-line-string
  "Converts a flat sequence of alternating x,y floating point coords to a line string"
  [xys]
  (->> xys
       (partition 2)
       (map to-coordinate)
       (into-array Coordinate)
       to-line-string))

(defn convert-text-line
  "Converts a string of alternating x,y to a line string"
  [s]
  (->> (re-seq #"\d+\.\d+", s)
       (map #(Float/parseFloat %))
       (xys-to-line-string)))
