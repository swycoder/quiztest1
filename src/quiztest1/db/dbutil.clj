(ns quiztest1.db.dbutil
  (:require [compojure.core :refer :all]
            [clojure.java.jdbc :as jdbc]
            [jdbc.pool.c3p0 :as pool]
            )
  )
(def spec
  (pool/make-datasource-spec
    {:subprotocol       "mysql"
     :subname           "//127.0.0.1:3306/mm"
     :user              "root"
     :password          "root"
     :min-pool-size     1
     :max-pool-size     3
     :initial-pool-size 2
     }))

(defn test-get-user []
  (def result (jdbc/db-do-commands spec "SELECT * FROM mm_user_1 WHERE id != 0;"))
  (println "Result: " result)
  )
(defn get-user [sql]
  (println sql)
  (let [stmt (jdbc/query spec sql)]
    (println "Result: " stmt)
    stmt
    )
  )
;(let [stmt (jdbc/query spec ["select * from mm_user_obs where id = ?" 1] )]
;  (println "Result: " stmt))

