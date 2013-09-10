(ns webparse.core
  (:require [net.cgrand.enlive-html :as html]))

(defn href
  "Returns a function that takes the node and get the href value,
  and then merge it with the root-url passed in the closure param.
  Ex1: (href)  Ex2: (href \"http://www.site.com\")
  => #(str \"http://www.ccquebec.ca\" (:href (:attrs %)))"
  ([] (fn [node] (:href (:attrs node))))
  ([root-url] (fn [node] (str root-url (:href (:attrs node))))))

(defn text
  "Like html/text but a higher order fn that can optionally
  take a negative nbr of char to substract at beginning
  and at the end. Ex1: (text) Ex2: (text -5 -2)"
  ([] (fn [node] (html/text node)))
  ([char-before]
     (fn [node] (apply str (drop (- char-before) (html/text node)))))
  ([char-before char-after]
     (fn [node] (apply str (drop-last (- char-after)
                           (drop (- char-before) (html/text node)))))))

(defn select+
  "Like html/select but optionnaly accepts a trimming fn as
   a last selector item. Ex: (select+ a-node [:a (html/text)])"
  [node selector]
  (if (fn? (peek selector))
    (map (peek selector)
         (html/select node (pop selector)))
    (html/select node selector)))