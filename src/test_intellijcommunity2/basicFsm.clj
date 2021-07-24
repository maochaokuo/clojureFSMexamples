(ns test-intellijcommunity2.basicFsm
  (:require [reduce-fsm :as fsm]))

; Basic FSM
; The following example counts the number of times "ab" occurs in a sequence

(defn inc-val [val & _] (inc val))

(fsm/defsm count-ab
           [[:start
             \a -> :found-a]
            [:found-a
             \a ->  :found-a
             \b -> {:action inc-val} :start
             _ -> :start]])

(comment
  ;; We can use the generated fsm like any function
  (map (partial count-ab 0) ["abaaabc" "aaacb" "bbbcab"])
  ;; returns => (2 0 1)

  (fsm/show-fsm count-ab)
  ;; displays the fsm diagram below
  )
