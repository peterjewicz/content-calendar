(ns content-calendar.server.services.socket
  (:require
    [immutant.web.async       :as async]
    [cheshire.core            :refer :all]
    [content-calendar.server.services.database :as db]))



; Handlers for our websocket functions
(defmulti handle-websocket-message (fn [data] (:type data)))
  (defmethod handle-websocket-message "create-project"
    [data]
    (async/send! (:channel data) (generate-string (db/create-project (:value data)))))
  (defmethod handle-websocket-message "create-content"
    [data]
    (async/send! (:channel data) (generate-string (db/create-content (:value data)))))
  (defmethod handle-websocket-message "create-platform"
    [data]
    (async/send! (:channel data) (generate-string (db/create-platform (:value data)))))




