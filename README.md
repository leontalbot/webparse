# webparse

A small collection of functions that simplify the use of Enlive for parsing websites.

## Installation

Add the following dependency to your `project.clj` file:

    [webparse "0.1.0"]

## Usage

Three functions:

#### href
A higher order fn that takes a node, extracts the :href attribute and, optionnaly takes a domain name as argument and adds it at the beginning of the URL.

* Use `(href)` instead of `#(:href (:attrs %))`
* Use `(href "http://www.site.com")` instead of `#(str "http://www.site.com" (:href (:attrs %)))`

#### text
Like html/text but a higher order fn that can optionally take a negative nbr of char to substract at beginning and at the end. 

* Use `(text -5)` instead of `#(apply str (drop 5 (html/text %)))`
* Use `(text -5 -2)` instead of `#(apply str (drop-last 2 (drop 5 (html/text %))))`
    
#### select+
Like html/select but optionnaly accepts a trimming fn as a last selector item. 

* Use `(select+ a-node [:a (html/text)])` instead of `(map (html/text) (html/select a-node [:a]))`

  
## License

Copyright © 2013 Léon Talbot

Distributed under the Eclipse Public License, the same as Clojure.
