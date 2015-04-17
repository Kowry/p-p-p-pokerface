 (ns p-p-p-pokerface)

(defn rank [card]
  (let [[rank _] card
    kkortit (fn[rank] ({\T 10, \J 11, \Q 12, \K 13, \A 14} rank))]
  (if (Character/isDigit rank) (Integer/valueOf (str rank)) (kkortit rank))))


(defn suit [card]
  (let [[_ suit] card ]
    (str suit)))

(defn pair? [hand]
  (= 2 (apply max (vals (frequencies (map rank hand))))))

(defn three-of-a-kind? [hand]
  (= 3 (apply max (vals (frequencies (map rank hand))))))

(defn four-of-a-kind? [hand]
  (= 4 (apply max (vals (frequencies (map rank hand))))))


(defn flush? [hand]
  (= 5 (Integer/valueOf(apply str (vals (frequencies (map suit hand)))))))

(defn full-house? [hand]
  (= [2 3] (sort (vals (frequencies (map rank hand))))))

(defn two-pairs? [hand]
  (= [1 2 2] (sort (vals (frequencies (map rank hand))))))

(defn straight? [hand]
    (let [
     arvot (map rank hand)
     handSorted (sort arvot)
     handSortedLowAce (sort (replace {14 1} arvot))]
  (or
   (and (apply < handSorted) (= 4 (- (apply max arvot) (apply min arvot))))
   (= (range 1 6) handSortedLowAce ))))

(defn straight-flush? [hand]
  (and (flush? hand) (straight? hand)))

(defn high-card? [hand]
  true)

(defn value [hand]
(let [checkers #{[high-card? 0]  [pair? 1]
                 [two-pairs? 2]  [three-of-a-kind? 3]
                 [straight? 4]   [flush? 5]
                 [full-house? 6] [four-of-a-kind? 7]
                 [straight-flush? 8]}]
  (apply max (second checkers))))



