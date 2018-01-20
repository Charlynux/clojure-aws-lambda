(ns hello
    (:gen-class
     :implements [com.amazonaws.services.lambda.runtime.RequestStreamHandler])
    (:require [clojure.data.json :as json]
              [clojure.string :as s]
              [clojure.java.io :as io]
              [clojure.pprint :refer [pprint]]))
  
  (defn handle-event [event]
    (pprint event)
    { :name (s/upper-case (:name event))})

  (defn -handleRequest [this is os context]
    (let [w (io/writer os)]
      (-> (json/read (io/reader is) :key-fn keyword)
          (handle-event)
          (json/write w))
      (.flush w)))