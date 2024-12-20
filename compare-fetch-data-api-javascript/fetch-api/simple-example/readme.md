# Fetch API
- Docs : https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API

- It is more powerful and flexible replacement for XMLHTTPREQUEST

- For making req & fetch

- The fetch() method takes one mandatory argument, the path to the resource you want to fetch. It returns a Promise that resolves to the Response to that request — as soon as the server responds with headers — even if the server response is an HTTP error status. You can also optionally pass in an init options object as the second argument (see Request).

Once a Response is retrieved, there are a number of methods available to define what the body content is and how it should be handled.

You can create a request and response directly using the Request() and Response() constructors, but it's uncommon to do this directly. Instead, these are more likely to be created as results of other API actions (for example, FetchEvent.respondWith() from service workers).

Find out more about using the Fetch API features in Using Fetch.

------

````
fetch(resource)
fetch(resource, options)
````

fetch available on window & worker global scope

 - Headers - Represent request/response headers
 - Request - Represent request
 - Response - Represent response


Apakah fetch tersedia di server ?
- Tidak, namun ada libtary eksternal yang menyediakan implementasinya.