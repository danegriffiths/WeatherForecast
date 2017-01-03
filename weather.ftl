<#-- @ftlvariable name="" type="com.example.views.PersonView" -->
<html>
    <body>
        <!-- calls getPerson().getName() and sanitizes it -->
        <h1>Hello, ${person.name?html}!</h1>
    </body>
</html>