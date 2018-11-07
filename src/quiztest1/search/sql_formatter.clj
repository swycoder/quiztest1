(ns quiztest1.search.sql_formatter)

(defn peek-and-pop "peek a list and pop" [vec_or_list]
  (def result (peek vec_or_list ))
  (pop vec_or_list)
  result
  )
(defn get-from-pos "get select sql from position" [sql_split]
  (def pos
    (loop [x 0 ret nil]
      (def this (get sql_split x))
      (def veryify_from (compare (clojure.string/lower-case this) "from"))
      (if (not= ret nil)
        ret
        (recur (inc x) (if (= veryify_from 0 )
                         x
                         nil
                         ))
        )
      ))
  pos
  )
(defn replace_sharding_id [sql_split pos_from sharding_id]
  (def pos_from_plus_one (inc pos_from))
  (def len_split (count sql_split))
  (loop [x 0 ret (vector)]
    (def this (get sql_split x))
    (if (= x len_split)
      ret
      (recur (inc x) (conj ret (if (= x pos_from_plus_one)
                                 (str this "_" sharding_id)
                                 this
                                 ) ))
      )
    )
  )
; (get-from-pos (clojure.string/split (clojure.string/replace "select * from   group where id = 2321;" #" +" " ")) )
; (replace_sharding_id (clojure.string/split (clojure.string/replace "select * from   group where id = 2321;" #" +" " ")  #" ") 7 9)

(defn get-sharding-sql-by-sharding-id "get sharding" [sql_str sharding_id]
  (println sharding_id)
  (def normalize_sql (clojure.string/replace sql_str #" +" " "))
  (def sql_split (clojure.string/split normalize_sql #" "))
  (def from_pos (get-from-pos sql_split))
  (def result_str (replace_sharding_id sql_split from_pos sharding_id))
  (clojure.string/join " " result_str)
  )
; (get-sharding-sql-by-sharding-id "select * from group where id = 2;" 4)
