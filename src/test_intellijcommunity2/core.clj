(ns test-intellijcommunity2.core
  ;(require '[reduce-fsm :as fsm])
  ;(:require [reduce-fsm :as fsm])
  (:require [test-intellijcommunity2.basicFsm :as bf]
            [test-intellijcommunity2.incrementalFSM :as if])
  )

;(defn foo
;  "I don't do a whole lot."
;  [x]
;  (println x "Hello, World!"))

; basic FSM
(comment
  ;; We can use the generated fsm like any function
  (map (partial count-ab 0) ["abaaabc" "aaacb" "bbbcab"])
  ;; returns => (2 0 1)

  (fsm/show-fsm count-ab)
  ;; displays the fsm diagram below
  )

; Incremental FSM
(comment
  (:value @fsm-state)
  ;; returns the current accumulated value => 1

  (:state @fsm-state)
  ;; the current state of the fsm => :start

  ;; count the number of ab occurences in a string
  (:value (reduce fsm/fsm-event (count-ab 0) "abaaabc"))
  ;; => 2
  )
