(ns quiztest1.user.controller.controller
  (:require [quiztest1.user.service.service :as sv]
            [ring.util.response :refer :all]
            [quiztest1.common.util.resp-util :as util]

            ))
(defn user_finish_question [user_info]
  (let [state (get user_info (keyword "state"))]
    (if (zero? state)
      ; 没完成答卷，去完成答卷，否则推荐人
      (resource-response "html/quiz.html" {:root "public"})
      (util/associateStatus "success login and finish question")
      )
    )
  )


(defn login [name pwd]
  "
  先校验用户是否成功登录，未登录返回提示
  看下用户答题过没有， 如果没答题，跳转quiz.html，如果已经答题，则显示推荐用户页
  "
  (let [info (sv/user_info name pwd) exist_flag (not (zero? (count info)))]
    ;exist_flag

    (if (= exist_flag true)
      (user_finish_question (first info))
      (resource-response "html/index.html" {:root "public"})
      )
    )
  )

;(login "first" "12")
