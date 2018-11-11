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
  )
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

(defn test-get-user []
  (def result (jdbc/db-do-commands spec "SELECT * FROM mm_user_1 WHERE id != 0;"))
  (println "Result: " result)
  )
(defn get-user [sql]
  (let [stmt (jdbc/query spec sql)]
    (println "Result: " stmt))
  )
