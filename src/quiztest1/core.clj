(ns quiztest1.core
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            ;[clojure.java.jdbc :as jdbc]
            [org.httpkit.server :refer [run-server]]
            ; [ring.middleware.defaults :refer :all]
            ;[stencil.core :as sen]
            ;[selmer.parser :refer :all ]
            [compojure.route :as route]
            ;[clojure.data.json :as json]
            [ring.util.response :as response]
            ))

(def db {:dbtype "mysql"
         :dbname "cm_a"
         :user "root"
         :password "root"})

; (defn test_db "test-db" []
  ; (jdbc/query db
              ; ["select 15 as result"]
              ; {:row-fn :result})
  ; )

(defroutes myapp
  (GET "/" [] "Hello World")
  (GET "/index" req (resource-response "html/quiz.html" {:root "public"}))
  (GET "/indextest" [] "hello world for test index")
  (route/resources "/")
  )

(defn -main []
  (println "start server at port 9000")
  ;(run-server (wrap-defaults  myapp site-defaults) {:port 9000}))
  (run-server myapp {:port 9000}))
