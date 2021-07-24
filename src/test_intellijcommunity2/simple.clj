(ns test-intellijcommunity2.simple
  (:require [tilakone.core :as tk :refer [_]]
            [tilakone.schema :as tks]))

; Serializable state description:

(def count-ab
  [{::tk/name        :start
    ::tk/transitions [{::tk/on \a, ::tk/to :found-a}
                      {::tk/on _}]}
   {::tk/name        :found-a
    ::tk/transitions [{::tk/on \a}
                      {::tk/on \b, ::tk/to :start, ::tk/actions [:inc-val]}
                      {::tk/on _, ::tk/to :start}]}])
(comment
  (tks/validate-states count-ab)
  ;=> [::tk/name :start, ...
  )
; Non-serializable state:

(def count-ab-process
  {::tk/states  count-ab
   ::tk/state   :start
   ::tk/action! (fn [{::tk/keys [action] :as fsm}]
                  (case action
                    :inc-val (update fsm :value inc)))
   :value       0})

(comment
  (tks/validate-process count-ab-process)
  ;=> {::tk/states ...
  )
; Try to send some signals:

(comment
  (-> count-ab-process
      (tk/apply-signal \a))
  ;=> {::tk/state :found-a
  ;    :value 0
  ;    ...
  )
(comment
  (-> count-ab-process
      (tk/apply-signal \a)
      (tk/apply-signal \b))
  ;=> {::tk/state :start
  ;    :value 1
  ;    ...
  )
(comment
  (reduce tk/apply-signal
          count-ab-process
          "abaaabc")
  ;=> {::tk/state :start
  ;    :value 2
  ;    ...
  )
