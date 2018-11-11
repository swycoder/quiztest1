(defproject quiztest1 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  ; :dependencies [[org.clojure/clojure "1.8.0"]
                 ; [compojure "1.1.8"]
                 ; [http-kit "2.1.16"]                 
                 ; [org.clojure/java.jdbc "0.7.8"]
                 ; [mysql/mysql-connector-java "8.0.11"]
                 ; [ring/ring-defaults "0.3.2"]
                 ; [ring/ring-core "1.6.3"]

                 ; ]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.1.8"]
                 [http-kit "2.1.16"]                 
                 [org.clojure/java.jdbc "0.7.8"]
                 [mysql/mysql-connector-java "8.0.11"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.3.2"]
                 [org.clojure/data.json "0.2.6"]
                 [clojure.jdbc/clojure.jdbc-c3p0 "0.3.3"]
                 ]
  :main quiztest1.core)
