(ns quiztest1.search.dbutil
  (:require [compojure.core :refer :all]
            [clojure.java.jdbc :as jdbc]
            [jdbc.pool.c3p0 :as pool]
            [org.httpkit.server :refer [run-server]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            [quiztest1.search.sql_formatter :as sql_format]
            )
  ; (:import (com.mchange.v2.c3p0 ComboPooledDataSource))
  )
(def db-spec
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//127.0.0.1:3306/mm"
   :user "root"
   :password "root"})

;; Convert the standard dbspec to an other dbspec with `:datasource` key
(def dbspec (pool/make-datasource-spec {:subprotocol "mysql"
                                        :subname "//localhost:3306/mm"}))

(def dbspec {:vendor "mysql"
             :name "mm"
             :host "localhost"      ;; Optional
             :port  3306            ;; Optional
             :user "root"       ;; Optional
             :password "root"}) ;; Optional
(def spec
  (pool/make-datasource-spec
    {:subprotocol "mysql"
     :subname "//127.0.0.1:3306/mm"
     :user "root"
     :password "root"
     :min-pool-size 1
     :max-pool-size 3
     :initial-pool-size 2
     }))
; testing
; (println (jdbc/query spec "select * from mm_user_1")) 
; (defn test-get-user []
  ; (println "test-get-user")
  ; )

; (defn pool
  ; [spec]
  ; (let [cpds (doto (ComboPooledDataSource.)
               ; (.setDriverClass (:classname spec))
               ; (.setJdbcUrl (str "jdbc:" (:subprotocol spec) ":" (:subname spec)))
               ; (.setUser (:user spec))
               ; (.setPassword (:password spec))
               ; ;; expire excess connections after 30 minutes of inactivity:
               ; (.setMaxIdleTimeExcessConnections (* 30 60))
               ; (.setMaxPoolSize 2)  ; 自己加上的
               ; ;; expire connections after 3 hours of inactivity:
               ; (.setMaxIdleTime (* 3 60 60)))]

    ; {:datasource cpds}))
; (def pooled-db (delay (pool db-spec)))

; (defn db-connection [] @pooled-db)

(defn test-get-user []
  (def result (jdbc/db-do-commands spec "SELECT * FROM mm_user_1 WHERE id != 0;"))
  (println "Result: " result)
  )
(defn get-user [sql]
  (let [stmt (jdbc/query spec sql)]
    (println "Result: " stmt))
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
; (def db-pool
  ; (loop [x 0 ret (list)]
    ; (if (= x 2)
      ; ret
      ; (recur (inc x) (conj ret (jdbc/connection dbspec)))
      ; )
    ; )
  ; )
; (defn get-conn []
  ; (if (= (count db-pool) 0)
    ; (if (= (count db-pool) 0)
      ; nil
      ; (sql_format/peek-and-pop db-pool)
      ; )
    ; )
  ; )
; (defn free-conn [conn]
  ; (concat db-pool conn)
  ; )
