(ns geocheck.spatial
  (:import (com.vividsolutions.jts.algorithm.match HausdorffSimilarityMeasure))
  (:require [geo.jts :as jts]))

(defn to-line-string
  [coordinates]
  (.createLineString jts/gf coordinates))

(defn to-coordinate
  [[x y]]
  (jts/coordinate x y))

(defn length
  [lstr]
  (.getLength lstr))

(def similarity-measure (ref (HausdorffSimilarityMeasure.)))

(defn similarity
  "Computes a [0,1] similarity measure based on the Hausdorff distance"
  [g1 g2]
  (dosync
    (.measure @similarity-measure g1 g2)))

