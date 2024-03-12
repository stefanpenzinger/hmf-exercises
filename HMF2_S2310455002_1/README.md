# Hypermedia Frameworks Exercise 1
Create a JSP implementing the TimingError Example (html form + servlet).
Use a structure to distinguish between get (return the form) and post (do the servlet functionality) request like this:

```
<% if request.getMethod().equals("GET") { %>
   <!-- output form (interval, value) -->
<% } else { // POST %>
  <!-- implementation like thread error servlet code -->
<% } %>
```

## Reference Files
The two files (_TimingErrorServlet.java_ and _index.html_) in the package **hmf2.hmf2_s2310455002_1.reference** are just for reference and were used to solve this exercise.