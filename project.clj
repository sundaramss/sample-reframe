(defproject sample "0.1.0"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [reagent "0.8.0"]
                 [re-frame "0.10.2"]
                 [secretary "1.2.3"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [ring "1.4.0"]
                 [day8.re-frame/http-fx "0.1.4"]
                 [cljsjs/react-input-autosize "2.0.0-1"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-ancient "0.6.15"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/" "target"
                                    "test/js"]

  :figwheel {:css-dirs ["resources/public/css"]
             :ring-handler sample.handler/dev-handler}

  :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.9.4"]
                   [cljsjs/d3 "4.3.0-5"]
                   [day8.re-frame/trace "0.1.7"]
                   
                   [figwheel-sidecar "0.5.13"]
                   [com.cemerick/piggieback "0.2.2"]
                   [re-frisk "0.5.0"]]

    :plugins      [[lein-figwheel "0.5.13"]
                   [lein-doo "0.1.8"]]}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "sample.core/mount-root"}
     :compiler     {:main                 sample.core
                    :output-to            "resources/public/js/app.js"
                    :output-dir           "resources/public/js/out"
                    :asset-path           "js/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload
                                           day8.re-frame.trace.preload
                                           re-frisk.preload
                                           ]
                    :closure-defines      {"re_frame.trace.trace_enabled_QMARK_" true}
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :jar true
     :compiler     {:main            sample.core
                    :output-to       "resources/public/js/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    {:id           "test"
     :source-paths ["src/cljs" "test/cljs"]
     :compiler     {:main          sample.runner
                    :output-to     "resources/public/js/test.js"
                    :output-dir    "resources/public/js/test/out"
                    :optimizations :none}}
    ]}

  :main sample.server

  :aot [sample.server]

  :uberjar-name "sample.jar"

  :prep-tasks [["cljsbuild" "once" "min"] "compile"]
  )
