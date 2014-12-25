(defproject geocheck "0.1.0-SNAPSHOT"
  :description "Spatial analysis of geometries"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [yieldbot/flambo "0.3.2"]
                 [org.apache.spark/spark-core_2.10 "1.0.1"]
                 [factual/geo "1.0.0"]
                 [criterium "0.4.3"]
                 [incanter "1.5.5"]]

  :profiles {:uberjar {:aot :all}}
  :jvm-opts ["-Xmx2g"]
  :main ^:skip-aot geocheck.flambo
  :target-path "target/%s")
