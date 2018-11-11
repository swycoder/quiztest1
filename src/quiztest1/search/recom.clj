(ns quiztest1.search.recom
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]
            [quiztest1.search.dbutil :as dbUtil]
            [quiztest1.search.sql_formatter :as sql_format]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            ))
(defn get-total-score "get total score of string vector" [answer_vector]
  (def len (count answer_vector ))
  (def ret (loop [x 0 sum 0]
             (if (= x len)
               sum
               (recur (inc x)  (+ sum (Integer/parseInt (get answer_vector x)))))
             ))
  ret
  )
(defn recommend-user "recommend-user" [answer_vector]
  ; 获得用户的总得分
  ; 去对应的数据库中查找用户
  (def total-score (get-total-score answer_vector))
  (def sql (sql_format/get-sharding-sql-by-sharding-id "SELECT * FROM mm_user_options t1 INNER JOIN (SELECT RAND()*10 AS nid) t2 ON t1.id > t2.nid LIMIT 5;" total-score))
  (println sql)
  (println total-score)
  (dbUtil/get-user sql)
  ; (dbUtil/test-get-user )
  )
