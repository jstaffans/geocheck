(ns geocheck.spatial
  (:import (com.vividsolutions.jts.algorithm.match HausdorffSimilarityMeasure))
  (:require [geo.jts :as jts]))


(def similarity-measure
  (HausdorffSimilarityMeasure.))

(defn to-line-string
  [coordinates]
  (.createLineString jts/gf coordinates))

(defn to-coordinate
  [[x y]]
  (jts/coordinate x y))

(defn length
  [lstr]
  (.getLength lstr))

(defn similarity
  "Computes a [0,1] similarity measure based on the Hausdorff distance"
  [g1 g2]
  (.measure similarity-measure g1 g2))
