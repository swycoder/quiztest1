(ns quiztest1.common.util.resp-util)

(defn associateStatus [returnMap]
  (println returnMap)
  (hash-map :status 200 :body returnMap)
  )
