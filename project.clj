(defproject test-intellijcommunity2 "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [reduce-fsm "0.1.4"]
                 [server-socket "1.0.0"]
                 [metosin/tilakone "0.0.4"]
                 [metosin/tilakone.core "0.0.4"]
                 [metosin/tilakone.schema "0.0.4"]]

  :repl-options {
                 ;:init-ns test-intellijcommunity2.core
                 ;:init-ns test-intellijcommunity2.basicFsm
                 ;:init-ns test-intellijcommunity2.incrementalFSM
                 ;:init-ns test-intellijcommunity2.generatingLazySequences
                 ;:init-ns test-intellijcommunity2.statefulFiltering
                 ;:init-ns test-intellijcommunity2.differentDispatchTypes
                 ;:init-ns test-intellijcommunity2.fsm-dispatch-examples
                 ;:init-ns test-intellijcommunity2.simple-server
                 ;:init-ns test-intellijcommunity2.tilakoneCountAbExample
                 ;:init-ns test-intellijcommunity2.simple
                 ;:init-ns test-intellijcommunity2.count-ab-example
                 ;:init-ns test-intellijcommunity2.fn-actions-and-guards
                 :init-ns test-intellijcommunity2.light-machine
                 })
