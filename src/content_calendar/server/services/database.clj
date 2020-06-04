(ns content-calendar.server.services.database
  (:require [toucan.db :as db]
             [toucan.models :refer [defmodel]]))

(defmodel Content :content)

(def mysql-db {
  :classname   "org.mysql.Driver"
   :subprotocol "mysql"
   :subname     "//localhost:3306/contentCalendar"
   :user        "peter"
   :password  ""
   :serverTimezone "UTC"})

(db/set-default-db-connection! mysql-db)
(db/set-default-quoting-style! :mysql)

(defn test-db []
  (println (db/select Content)))