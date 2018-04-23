# sample-reframe Hello World! App
This is Hello World cljs Re-frame

## Application contains:

  a. Server
  b. Client
  
a. Server java based jetty server, 
   sources under `src/clj`
b. Client side
   sources under `src/cljs`

   
## Compile Client Source files
----------------------------
`$lein cljsbuild once dev`

## Run server 
----------
`$lein run main`

Launch Browse: `http://localhost:3000`


## Auto compile and manual refresh 

`$lein cljsbuild auto dev`

`$lein run main` (or) go to resources\public and run `$python -m SimpleHTTPServer 3000`

Launch Browse: `http://localhost:3000`

## Auto compile and refresh

`$lein figwheel dev`

Launch Browse: `http://localhost:3449`

## Modularized Build app.js, vendor.js, cljs_base.js
`$lein cljsbuild once min`
