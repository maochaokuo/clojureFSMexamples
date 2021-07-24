(ns test-intellijcommunity2.statefulFiltering
  (:require [reduce-fsm :as fsm]))

;Stateful Filtering
;States in filters are defined as passing values (default) or suppressing them {:pass false}. For each event the filter will return the pass value of the state it is in after processing the event (input sequence element).
;
;The following example suppresses values from the time a 3 is encountered until we see a 6.

(fsm/defsm-filter sample-filter
              [[:initial
                3 -> :suppressing]
               [:suppressing {:pass false}
                6 -> :initial]])

(comment
  ;; The resulting fsm is used with the clojure.core/filter and remove functions like this.
  (filter (sample-filter) [1 2 3 4 5 1 2 6 1 2])
  ;; returns => (1 2 6 1 2)

  (fsm/show-fsm sample-filter)
  ;; displays the diagram below
  )