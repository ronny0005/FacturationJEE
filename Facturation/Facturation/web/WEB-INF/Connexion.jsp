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
        <link href="bootstrap.css" rel="stylesheet">
        <link href="jquery-ui.css" rel="stylesheet">
        <link href="jquery-ui.theme.css" rel="stylesheet">
        <link href="fieldset.css" rel="stylesheet">
        <script src="jquery.js"></script>
        <script src="jquery_ui.js"></script>
        <script src="bootstrap-3.1.1.min.js"></script>
        <script src="scriptCombobox.js"></script>
        <script src="script.js"></script>
        <script type="text/javascript" language="javascript">
        jQuery(function($){
            $('.message a').click(function(){
                $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
            });
        });
        </script>
 <title>Connexion</title>
    </head>
    <body>
        <div class="login-page">
  <div class="form">
      <input type="text" placeholder="login" id="login" name="login"/>
      <input type="password" placeholder="mot de passe" id="mdp" name="mdp" />
      <button id="connexion">Connexion</button>
  </div>
</div>
    </body>
</html>
