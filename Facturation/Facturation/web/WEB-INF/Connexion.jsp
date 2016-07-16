<%-- 
    Document   : Connexion
    Created on : Jul 12, 2016, 11:17:56 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <link rel="stylesheet" href="style.css" media="screen" />
        <script type="text/javascript" src="jquery.js"></script>
 <script type="text/javascript" language="javascript">
 jQuery(function($){
 $('.message a').click(function(){
   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});
});
 </script>
 <title>JSP Page</title>
    </head>
    <body>
        <div class="login-page">
  <div class="form">
    <form class="login-form">
      <input type="text" placeholder="login"/>
      <input type="password" placeholder="mot de passe"/>
      <button>Connexion</button>
    </form>
  </div>
</div>
    </body>
</html>
