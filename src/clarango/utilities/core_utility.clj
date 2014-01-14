;; Please note:
;; The methods in this namespace are only intended for internal use.

(ns clarango.utilities.core-utility)

(defn get-connection-url
  "Gets the globally in clarango.core set connection url. If no url is set, an exception is thrown."
  []
  (if (clarango.core/connection-set?) 
	(:connection-url (clarango.core/get-connection)) 
	(throw (Exception. "No host URL set."))))

(defn get-safe-connection-url
  "Gets a safe connection url from clarango.core. If it does not start with 'http://', an exception is thrown."
  []
  (let [conn-url (get-connection-url)]
    (if (.startsWith conn-url "http://") 
      conn-url 
      (throw (Exception. "Somethings wrong with the URL. Does not start with http.")))))

(defn get-default-db
  "Gets the globally in clarango.core set db name. If none is set, an exception is thrown."
  []
  (if (clarango.core/connection-set?)
    (let [db-name (:db-name (clarango.core/get-connection))]
      (if (nil? db-name) (throw (Exception. "No default database set.")) db-name))
        (throw (Exception. "No connection set."))))

(defn get-default-collection-name
  "Gets the globally in clarango.core set collection id. If none is set, an exception is thrown."
  []
  (if (clarango.core/connection-set?) 
    (let [collection-name (:collection-name (clarango.core/get-connection))]
      (if (nil? collection-name) (throw (Exception. "No default collection set.")) collection-name)) 
        (throw (Exception. "No connection set."))))

(defn remove-map
  "Takes a vector and returns it without the elements that are of type map."
  [vect]
  (remove #(= (type %) clojure.lang.PersistentArrayMap) vect))

(defn filter-out-map
  "Returns the first element in a vector that is of type map."
  [vect]
  (first (filter #(= (type %) clojure.lang.PersistentArrayMap) vect)))