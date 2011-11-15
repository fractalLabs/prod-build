(ns prod-build.core)


(defn read-file [name] (eval (read-string (str "'(" (slurp name) ")"))))

(defn defn? [data] (= (first data) 'defn))

(defn defmacro? [data] (= (first data) 'defmacro))

(defn defprotocol? [data] (= (first data) 'defprotocol))

(defn drop-nth [n coll] (concat (take (- n 1) coll) (drop n coll)))

(defn drop-doc [data] (if (or (defn? data) (defmacro? data) (defprotocol? data)) (drop-nth 3 data) data))

(defn -main [file outfile] (spit outfile (pr-str (drop-doc (read-file file)))))

;TODO: lo de [p1__66#]
