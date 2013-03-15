(ns task01.core
  (:require [pl.danieljanus.tagsoup :refer :all])
  (:gen-class))


(defn- link? [vect]
  (= "r" (:class (get vect 1) )))

(defn- get-href [vect]
  ( :href ( get (get vect 2) 1)))

(defn- rrest [vect]
  (rest (rest vect)))

(defn- not-empty-vector? [vect]
  (and (vector? vect) ( not (empty? vect))))


(defn get-links []
  (let [data ( conj '() (parse "clojure_google.html"))]
    (loop [ tags  data
            hrefs '()]
      (if (or ( empty? tags) (= 10 (count hrefs)))
        hrefs
        (recur (filter  not-empty-vector?
                 (reduce concat 
                   (map rrest tags )))
               (into hrefs
                 (map get-href
                   (filter  link?  tags))))))))

(defn -main []
  (println (str "Found " (count (get-links)) " links!")))


