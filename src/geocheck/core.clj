(ns geocheck.core
  (:require [geocheck.spatial :refer :all]
            [geocheck.transforms :refer :all]
            [clojure.core.reducers :as r]
            [incanter.core :as i]
            [incanter.charts :as c]))

(defn file-pairwise-similarity
  [file1 file2]
  (with-open [rdr1 (clojure.java.io/reader file1)
              rdr2 (clojure.java.io/reader file2)]
    (let [geometry-line-seqs (map to-line-strings [rdr1 rdr2])
          geometry-pairs (partition 2 (apply interleave geometry-line-seqs))]
      (-> (r/map (fn [[g1 g2]] (similarity g1 g2)) geometry-pairs)
          r/foldcat))))

;
; Incanter visualisations
;

(defn to-dataset
  [similarities]
  (i/dataset [:index :similarity] (partition 2 (interleave (range) similarities))))

(defn similarity-scatter
  [similarities]
  (let [ds (to-dataset similarities)]
    (c/scatter-plot (i/sel ds :cols :index)
                    (i/sel ds :cols :similarity))))



;
; random data processing for benchmarks
;
(defn random-line-string
  "Generates a line string of length l with random coordinates: [0,90]."
  [l]
  (xys-to-line-string (repeatedly l #(* (rand) 90.0))))

(defn random-line-strings
  "Generates an infinite sequence of random line string pairs."
  [l]
  (let [gs (repeatedly #(random-line-string l))]
    (partition 2 gs)))

(defn process-random-single-threaded []
  (->> (take 10000 (random-line-strings 500))
       (map (fn [[a b]] (similarity a b)))
       (into [])))

(defn process-random-reducers []
  (->> (take 10000 (random-line-strings 500))
       (r/map (fn [[a b]] (similarity a b)))
       r/foldcat))


(file-pairwise-similarity "foo" "bar")
(similarity-scatter asd)