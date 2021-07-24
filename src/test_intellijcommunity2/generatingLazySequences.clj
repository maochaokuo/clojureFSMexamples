(ns test-intellijcommunity2.generatingLazySequences
  (:require [reduce-fsm :as fsm]))

; Generating Lazy Sequences
; The fsm-seq functions return lazy sequences of values created by the emit function when a state change occurs. This example looks for log lines where the sequence of events was (a,c) instead of the expected (a,b,c) and adds the unexpected event to the output sequence.

(defn emit-evt [val evt] evt)

(fsm/defsm-seq log-search
           [[:start
             #".*event a" -> :found-a]
            [:found-a
             #".*event b" -> :found-b
             #".*event c" -> {:emit emit-evt} :start]
            [:found-b
             #".*event c" -> :start]])

(comment
  ;; The resulting function accepts a sequence of events
  ;; and returns a lazy sequence of emitted values
  (take 2 (log-search (cycle ["1 event a"
                              "2 event b"
                              "3 event c"
                              "another event"
                              "4 event a"
                              "event x"
                              "5 event c"])))

  ;; returns => ("5 event c" "5 event c")

  (fsm/show-fsm log-search)
  ;; displays the image below
  )