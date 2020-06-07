(ns content-calendar.core
    (:require
              [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as rd]
              [wscljs.client :as ws]
              [wscljs.format :as fmt]))

(enable-console-print!)

(println "This text is printed from src/content-calendar/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))


(defn handle-onOpen []
  (print "Connection Opened"))

(defn handle-onClose []
  (print "Connection Closes"))

(defn handle-onMessage [e]
  (print "message-received"))

(def handlers {:on-message (fn [e] (handle-onMessage e))
               :on-open    #(handle-onOpen)
               :on-close   #(handle-onClose)})

(defn setup-ws []
  (def socket (ws/create "ws://localhost:8080" handlers))
)

(defn send-message [type value]
  (ws/send socket {:type type :value value} fmt/json))

(def test-project {:name "tester" :description "a test projct" :ownerId 1 :startTime "2020-01-20"})


(defn hello-world []
  (setup-ws)
  [:div
   [:h1 (:text @app-state)]
   [:button {:on-click #(send-message "create-project" test-project)} "send msg"]]
)

(rd/render [hello-world]
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
