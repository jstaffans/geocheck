(ns geocheck.core
  (:require [geocheck.spatial :refer :all]
            [geocheck.transforms :refer :all]))

(defn get-line-strings [rdr]
  (map convert-text-line (line-seq rdr)))

(defn print-similarities
  "Given two sequences of geometries, prints their similarities"
  [gs1 gs2]
  (doseq [s (pmap similarity gs1 gs2)]
    (println s)))

; read data from csv files
(defn process-lines []
  (with-open [rdr1 (clojure.java.io/reader "data1.txt")
              rdr2 (clojure.java.io/reader "data2.txt")]
    (let [gs1 (get-line-strings rdr1)
          gs2 (get-line-strings rdr2)]
      (print-similarities gs1 gs2))))


(defn random-line-string
  "Generates a line string of length l with random coordinates: [0,90]"
  [l]
  (xys-to-line-string (repeatedly l #(* (rand) 90.0))))

; process random data
(defn process-random []
  (let [gs1 (take 100 (repeatedly #(random-line-string 1000)))
        gs2 (take 100 (repeatedly #(random-line-string 1000)))]
    (print-similarities gs1 gs2)))
