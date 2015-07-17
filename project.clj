(defproject cardshark "0.1.0-SNAPSHOT"
  :description "Card Dealer"
  :url "http://example.com/cardshark"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.combinatorics "0.1.1"]]
  :main ^:skip-aot cardshark.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
