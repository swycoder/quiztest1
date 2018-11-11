(ns quiztest1.core
  (:use ring.util.response)
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            [quiztest1.search.recom :as reco]
            ))

(defn printReq  [req]
  (println req)
  (hash-map :body "helloworld" :status 200 :headers {"Content-Type" "text/text"})
  )
(defn associateStatus [returnMap]
  (println returnMap)
  (hash-map :status 200 :body returnMap)
  )

(defn finish_question "finish_question" [req]
  (println req)
  (let [body  (slurp (:body req))]
    (println body)
    (def bd (json/read-str body))
    (def result  (reco/recommend-user bd)) 
    (println result)
    )
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
  (run-server myapp {:port 9000}))
