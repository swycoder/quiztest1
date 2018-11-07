(ns quiztest1.core
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            ; [clojure.java.jdbc :as jdbc]
            [org.httpkit.server :refer [run-server]]
            ; [ring.middleware.defaults :refer :all]
            ;[stencil.core :as sen]
            ;[selmer.parser :refer :all ]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            [quiztest1.search.recom :as reco]
            ))

; (def db {:dbtype "mysql"
         ; :dbname "cm_a"
         ; :user "root"
         ; :password "root"})

; (defn test_db "test-db" []
; (jdbc/query db
; ["select 15 as result"]
; {:row-fn :result})
; )
(defn printReq  [req]
  (println req)
  ; (hash-map :status 200 :headers {"Content-Type" "text/css"} :body "hfdasfdsa")
  (hash-map :body "helloworld" :status 200 :headers {"Content-Type" "text/text"})
  )
(defn associateStatus [returnMap]
  (println returnMap)
  (hash-map :status 200 :body returnMap)
  )

(defn finish_question "finish_question" [req]
  (println req)
  ; (println (:query-string req))
  ; (def q-string (:query-string req))
  ; (println (json/write-str q-string))
  ; (println (get-in req [:body ]))
  (let [body  (slurp (:body req))]
    (println body)
    (def bd (json/read-str body))
    (def result  (reco/recommend-user bd)) 
    (println result)
    ; (println  (type bd)); clojure.lang.PersistentVector
    ; (println  (get bd 0))
    ; (println  (type (get bd 0)));clojure.lang.PersistentArrayMap
    )
  ; (str "hello")
  "success"
  )
(defroutes myapp
  (GET "/" [] "Hello World")
  (GET "/index" req (resource-response "html/quiz.html" {:root "public"}))
  (GET "/indextest" [] "hello world for test index")
  (POST "/finishtest" req (printReq req))
  (POST "/finish" req (associateStatus  (finish_question req) ))
  (route/resources "/")
  )

(defn -main []
  (println "start server at port 9000")
  ;(run-server (wrap-defaults  myapp site-defaults) {:port 9000}))
  (run-server myapp {:port 9000}))
