(ns geocheck.core
  (:require [geocheck.spatial :refer :all]
            [geocheck.transforms :refer :all]))

(defn process-lines []
  (with-open [rdr (clojure.java.io/reader "data.txt")]
    (doseq [[g1 g2] (->> (line-seq rdr)
                         (map convert-line)
                         (partition 2))]
      (println (similarity g1 g2)))))
