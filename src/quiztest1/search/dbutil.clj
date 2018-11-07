(ns quiztest1.search.dbutil
  (:require [compojure.core :refer :all]
            [clojure.java.jdbc :as jdbc]
            ; [jdbc.core :as jdbc]
            [org.httpkit.server :refer [run-server]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            [quiztest1.search.sql_formatter :as sql_format]

            )
  (:import (com.mchange.v2.c3p0 ComboPooledDataSource))
  )
(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//127.0.0.1:3306/mm"
   :user "root"
   :password "root"})

(def dbspec {:vendor "mysql"
             :name "mm"
             :host "localhost"      ;; Optional
             :port  3306            ;; Optional
             :user "root"       ;; Optional
             :password "root"}) ;; Optional

(def db-pool
  (loop [x 0 ret (list)]
    (if (= x 2)
      ret
      (recur (inc x) (conj ret (jdbc/connection dbspec)))
      )
    )
  )
(defn get-conn []
  (if (= (count db-pool) 0)
    (if (= (count db-pool) 0)
      nil
      (sql_format/peek-and-pop db-pool)
      )
    )
  )
(defn free-conn [conn]
  (concat db-pool conn)
  )
(defn test-get-user []
  (def conn (get-conn))
  (def result (jdbc/execute conn "SELECT * FROM mm_user_1 WHERE id != 0;"))
  (println "Result: " result)
  (free-conn conn)
  )
(defn get-user [sql]
  (def conn (get-conn))
  (let [stmt (jdbc/prepared-statement ["SELECT * FROM mm_user_1 WHERE id > ?", 0])
        result (jdbc/fetch conn stmt)]
    (println "Result: " result))
  (free-conn conn)
  )
; (:require [compojure.core :refer :all]
; [clojure.java.jdbc :as jdbc]
; [org.httpkit.server :refer [run-server]]
; [ring.middleware.defaults :refer :all]
; ;[stencil.core :as sen]
; [selmer.parser :refer :all ]
; [compojure.route :as route]
; [clojure.data.json :as json]
; [ring.util.response :as response]
; (defn test_db "test-db" []
; (jdbc/query db
; ["select 15 as result"]
; {:row-fn :result})
; )
; (defn get_question_in_db "in db question" []
; (def a (jdbc/query db
; ["select id, question, ops from mm_questions;"]
; {:as-arrays? true}))
; (def questions (subvec a 1))
; (def len (count questions))
; ; rets
; )
