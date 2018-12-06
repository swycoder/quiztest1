(ns quiztest1.user.service.service
  (:require [quiztest1.search.recom :as reco]
            [quiztest1.db.dbutil :as user_db]

            ))

(defn register [user])

(defn user_info [uname pwd]
  ; 如果用户名密码匹配，登录
  (let [sele_str ["select * from mm_user_obs where name = ? and password = ?" uname pwd]
        ret (user_db/get-user sele_str)]
    ;(println sele_str)
    ;(println (type ret))
    ;(println (count ret))
    ;(println (first ret))
    ;(println (nth ret 0))
    ;(println (type (first ret)))
    ;(println (type (keys (first ret))))
    ;(println (keys (first ret)))
    ;(println (first (keys (first ret))))
    ;(println (type (first (keys (first ret)))) )
    ;(println (first (vals (first ret))))
    (println ret)
    (println (type ret))
    (println (count ret))
    ret
    ;(get (first ret) (keyword "count"))
    )
  )
;(user_info "first" "123")
