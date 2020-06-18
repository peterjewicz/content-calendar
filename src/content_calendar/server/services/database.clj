(ns content-calendar.server.services.database
  (:require [toucan.db :as db]
             [toucan.models :refer [defmodel]]))

(defmodel Content :content)
(defmodel Projects :projects)
(defmodel Platforms :platforms)

(def mysql-db {
  :classname   "org.mysql.Driver"
   :subprotocol "mysql"
   :subname     "//localhost:3306/contentCalendar"
   :user        "peter"
   :password  ""
   :serverTimezone "UTC"})

(db/set-default-db-connection! mysql-db)
(db/set-default-quoting-style! :mysql)

; (defn test-db []
;   (println (db/select Content)))

(defn create-project [project-map]
  (db/insert! Projects project-map))

(defn create-content [content-map]
  (db/insert! Content content-map))

(defn create-platforms [platforms-map]
  (db/insert! Platforms platforms-map))