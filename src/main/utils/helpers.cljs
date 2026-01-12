(ns utils.helpers
  (:require [clojure.string :as s]))

;; `workspaceRoot` is deprecated, the new variable name is `workspaceFolder`.
(defn render-workspace [path workspace-root]
  (s/replace (s/replace path "${workspaceFolder}" workspace-root) "${workspaceRoot}" workspace-root))

(defn unrender-workspace [path workspace-root]
  (s/replace path workspace-root "${workspaceFolder}"))

(defn render-env-status [lang env-path devshell]
  (let [env-name (if env-path
                   (last (s/split env-path #"/"))
                   (-> lang :label :env-custom))
        display-name (if devshell
                       (str env-name "#" devshell)
                       env-name)]
    (s/replace (-> lang :label :env-selected)
               #"%ENV_NAME%"
               display-name)))
