 (ns p-p-p-pokerface)

(defn rank [card]
  (let [[rank _] card
    kkortit (fn[rank] ({\T 10, \J 11, \Q 12, \K 13, \A 14} rank))]
  (if (Character/isDigit rank) (Integer/valueOf (str rank)) (kkortit rank))))


(defn suit [card]
  (let [[_ suit] card ]
    (str suit)))

(defn pair? [hand]
  (= 2 (cards-of-same-value hand)))

(defn three-of-a-kind? [hand]
  (= 3 (cards-of-same-value hand)))

(defn four-of-a-kind? [hand]
  (= 4 (cards-of-same-value hand)))

(defn cards-of-same-value [hand]
  (apply max (vals (frequencies (map rank hand)))))

(defn flush? [hand]
  (= 5 (Integer/valueOf(apply str (vals (frequencies (map suit hand)))))))

(defn full-house? [hand]
  nil)

(defn two-pairs? [hand]
  nil)

(defn straight? [hand]
  nil)

(defn straight-flush? [hand]
  nil)

(defn value [hand]
  nil)
