(ns sample.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
			[sample.config :as config]
            [sample.views :as views]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (dev-setup)
  (mount-root))
