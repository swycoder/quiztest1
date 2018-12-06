(ns quiztest1.user.controller.controller
  (:require [quiztest1.user.service.service :as sv]))
(defn user_finish_question [user_info]
  (let [state (get user_info (keyword "state"))]
    (if (zero? state)
      "quiz.html"
      "login.html")
    )
  (get user_info (keyword "state"))

  )

(defn login [name pwd]
  "
  先校验用户是否成功登录，未登录返回提示
  看下用户答题过没有， 如果没答题，跳转quiz.html，如果已经答题，则显示推荐用户页
  "
  (let [info (sv/user_info name pwd)]

    (if (zero? (count info))
      (println "user not exist")
      (println "user exist")
      )
    )
  )
;(login "first" "12")
