(ns quiztest1.core
  (:require [compojure.core :refer :all]
            [org.httpkit.server :refer [run-server]]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.util.response :as response]
            [quiztest1.search.recom :as reco]
            [ring.util.response :refer :all]
            [quiztest1.user.controller.controller :as user_controller]
            [quiztest1.common.util.resp-util :refer :all]
            [clojure.string :as str]))

(defn printReq [req]
  (println req)
  (hash-map :body "helloworld" :status 200 :headers {"Content-Type" "text/text"})
  )

(defn finish_question "finish_question" [req]
  (println req)
  (let [body (slurp (:body req))]
    (println body)
    (def bd (json/read-str body))
    (def result (reco/recommend-user bd))
    (println result)
    )
  "success"
  )
(defn register [req]
  (println "register is called")
  (println req)
  "success"
  )
(defn get_username_pwd_from_body [req]
  (let [body (slurp (:body req))]
    (println body)
    ; 默认格式是username=111&pass=1
    (let [username (nth (str/split (first (str/split body #"&")) #"=") 1)
          pwd (nth (str/split (nth (str/split body #"&") 1) #"=") 1)]
      (println username)
      (println pwd)
      (if (or (nil? username) (= username "") (nil? pwd) (= pwd ""))
        "input user name or pass is nil"
        (hash-map "username" username "password" pwd)
        )
      )
    )

  )
(defn login [req]
  (println "login is called")
  (println req)
  (println "get_username_pwd_from_body")
  (let [u_p_map (get_username_pwd_from_body req)]
    (println u_p_map)
    (user_controller/login (get u_p_map "username")
                           (get u_p_map "password"))

    )

  ; 先校验用户是否成功登录，未登录返回提示
  ; 看下用户答题过没有， 如果没答题，跳转quiz.html，如果已经答题，则显示推荐用户页
  ;(let [uname ]
  ;  )
  ;(user_controller/login  )
  ;"success"
  )
(defroutes myapp
           (GET "/" [] "Hello World")
           ; (GET "/index" req (resource-response "html/quiz.html" {:root "public"}))
           (GET "/index" req (resource-response "html/index.html" {:root "public"}))
           (GET "/indextest" [] "hello world for test index")
           (POST "/finishtest" req (printReq req))
           (POST "/finish" req (associateStatus (finish_question req)))
           (POST "/register" req (associateStatus (register req)))
           (POST "/login" req (login req))
           (route/resources "/")
           )

(defn -main []
  (println "start server at port 8080")
  (run-server myapp {:port 8080}))
