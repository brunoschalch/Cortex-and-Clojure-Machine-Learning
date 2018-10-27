(ns cortextutorial.LoadAndClean
  (:require [clojure.string :as string]))
(defn loadData []

  (do

    (defn parse-int [s]
      (Integer. (re-find  #"\d+" s )))


    (def everything [])
    (def allLyrics "test")
    (defn reading []
      (with-open [rdr (clojure.java.io/reader "../../resources/after.csv")]
        (doseq [line (line-seq rdr)]
          (def everything ( conj everything (string/split line #",")))
          )
        )
      )

    (reading)
    (print "Number of rows: ")
    (println (count everything))
    (print "Number of columns: ")
    (println (count (nth everything 0)))

    ;; Row 97, column 7 has an empty value.
    (println (= (nth (nth everything 97) 7) ""))

    ;; Now check there are no empty values
    (for [i (range 0 (count everything))]
      (for [j (range 0 (count (nth everything 0)))]
        (do
          ;; (println (= (nth (nth everything i) j) ""))
          (if (= (nth (nth everything i) j) "") (do
                                                  ;; Element was null
                                                  (println "Element was null")
                                                  ;; subst. value with 0
                                                  ;;   (def everything (assoc (nth everything i) j 0))
                                                  (println (nth (nth everything i) j))
                                                  )
                                                (do
                                                  ;; (println "Element was not null")
                                                  )
                                                )
          )
        )
      )



    (def splitPosition (int (Math/floor (* (count everything) 0.75))))
    (def splitData (split-at splitPosition everything))
    (def train (nth splitData 0))
    (def test (nth splitData 1))

    (print "Number of rows of train: ")
    (println (count train))
    (print "Number of rows of test: ")
    (println (count test))
    )
  )

(loadData)