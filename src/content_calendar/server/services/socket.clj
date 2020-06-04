(ns content-calendar.server.services.socket
  (:require
    [immutant.web.async       :as async]
    [cheshire.core            :refer :all]
    ; [story-planner.server.services.database :as DB]
))



; Handlers for our websocket functions
(defmulti handle-websocket-message (fn [data] (:type data)))
  ; (defmethod handle-websocket-message "create-project"
  ;   [data]
  ;   (async/send! (:channel data)
  ;                (generate-string
  ;                 (dissoc (DB/create-project {:name (:value data) :userId "123"}) :_id))))



