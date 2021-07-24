(ns test-intellijcommunity2.differentDispatchTypes
  (:require [reduce-fsm :as fsm]))

;Different dispatch types
;When defining a state machine the matching rules for a transition only use the current event by default, by adding the :dispatch option you can make transitions conditional on the state as well as the current event. The following dispatch types are supported:
;
;    :event-only (default) - just the current event is available for matches (equivalent to (clojure.core.match/match evt ...))
;    :event-and-acc - both the current accumulated state and the event are passed (equivalent to (clojure.core.match/match [state evt] ...))
;    :event-acc-vec - the state and event are passed in a single vector (equivalent to (clojure.core.match/match [ [state evt] ] ...))
;
;The following example demonstrates :event-acc-vec dispatch.


(defn should-transition? [[state event]]
  (= (* state 2) event))

(defn event-is-even? [[state event]]
  (even? event))

(defn inc-count [cnt & _ ]
  (inc cnt))

(defn reset-count [& _]
  0)

;; transition to the next state when we get a value thats twice the number of even events we've seen
(fsm/defsm even-example
           [[:start
             [_ :guard should-transition?] -> {:action reset-count} :next-state
             [_ :guard event-is-even?] -> {:action inc-count} :start]
            [:next-state ,,,]]
           :default-acc  0
           :dispatch :event-acc-vec)

(comment
  (even-example [1 1 2])   ;; => 1 (the number of even events)
  (even-example [1 2 2 4]) ;; => 0 (we transitioned to next state)
  )