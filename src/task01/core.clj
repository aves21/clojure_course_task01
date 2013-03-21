(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn- link [vect]
  (if (and
        (= :h3 (first vect))
        (= "r" (get-in vect [1 :class])))
    (remove nil? [(get-in vect [2 1 :href])])
    []))

(defn- get-hrefs [tags]
  (mapcat link  tags))

(defn- get-all-links [func data]
  (loop [tags  data
         hrefs '()]
    (if (empty? tags) hrefs
        (recur (mapcat #(remove (comp not vector?) (nnext %)) tags)
                 (into hrefs (func  tags))))))

(defn get-links []
  (let [data ( conj '() (parse "clojure_google.html"))]
    (get-all-links get-hrefs data)))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


