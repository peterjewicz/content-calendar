(ns content-calendar.core
    (:require
              [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as rd]
              [wscljs.client :as ws]))

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

(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:button {:on-click #(setup-ws)} "Connect"]]
)

(rd/render [hello-world]
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
