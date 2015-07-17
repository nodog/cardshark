(ns cardshark.core
  (:require [clojure.math.combinatorics :as combo])
  (:gen-class))

(require '[clojure.string :as str])

(def card-values ["A" "K" "Q" "J" "10" "9" "8" "7" "6" "5" "4" "3" "2"])

(def card-suits ["C" "D" "H" "S"])

(defn generate-all-cards-with-doseq
  []
  (println "with doseq")
  (def all-cards [])
  (doseq [c-value card-values] (doseq [c-suit card-suits] (def all-cards (conj all-cards (str/join [c-value "-" c-suit])))))
  all-cards)

(defn generate-all-cards-with-map
  []
  (println "with map")
  (flatten (map (fn [c-value] (for [c-suit card-suits] (str c-value "-" c-suit))) card-values)))

(defn generate-all-cards-with-reduce
  []
  (println "with reduce")
  (flatten (reduce (fn [all-cards c-value]
            (conj all-cards
                  (for [c-suit card-suits] (str c-value "-" c-suit))))
          []
          card-values)))

(defn generate-all-cards-with-combo
  []
  (println "with combo")
  (map (fn [x] (str (first x) "-" (last x))) (combo/cartesian-product card-values card-suits)))

(defn select-values-once
  [collection indices]
  (keep-indexed (fn [index item] (if (contains? (set indices) index) item)) collection))

(defn deal-hands
  [n-hands n-cards deck]
  (let [hand-indices (for [i (range 0 n-hands)] (range i (+ i (* n-cards n-hands)) n-hands ))]
    (map (fn [single-hand-indices] (select-values-once deck single-hand-indices)) hand-indices)))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "A card shark makes a deck.")
  (println (generate-all-cards-with-doseq))
  (println (generate-all-cards-with-map))
  (println (generate-all-cards-with-reduce))
  (def ordered-deck (generate-all-cards-with-combo))
  (println ordered-deck)
  (println "A card shark shuffles a deck.")
  (def shuffled-deck (shuffle ordered-deck))
  (println shuffled-deck)
  (println "Four hands from the deck.")
  (def hands (deal-hands 4 5 shuffled-deck))
  (println hands))

