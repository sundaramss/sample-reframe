(ns sample.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [sample.core-test]))

(doo-tests 'sample.core-test)
