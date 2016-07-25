<%-- 
    Document   : Menu
    Created on : Jul 12, 2016, 11:52:15 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="bootstrap.css" rel="stylesheet">
        <link href="jquery-ui.css" rel="stylesheet">
        <link href="jquery-ui.theme.css" rel="stylesheet">
        <link href="fieldset.css" rel="stylesheet">
        <script src="jquery.js"></script>
        <script src="jquery_ui.js"></script>
        <script src="bootstrap-3.1.1.min.js"></script>
        <script src="jquery.multiselect.min.js"></script>
        <script src="scriptCombobox.js"></script>
        <script src="scriptRecouvrement.js"></script>
        <script>
        jQuery(function($){
            
            function $_GET(param) {
                var vars = {};
                window.location.href.replace( location.hash, '' ).replace( 
                        /[?&]+([^=&]+)=?([^&]*)?/gi, // regexp
                        function( m, key, value ) { // callback
                                vars[key] = value !== undefined ? value : '';
                        }
                );
                if ( param ) {
                        return vars[param] ? vars[param] : null;	
                }
                return vars;
            }
            
            $('#facturation').click(function(){
                window.location.replace("ListeFacturationServlet?login="+$_GET("login")+"&mdp="+$_GET("login"));
            });
            
            $('#recouvrement').click(function(){
                window.location.replace("RecouvrementServlet?login="+$_GET("login")+"&mdp="+$_GET("login"));
            });
        });
        </script>
    </head>
    <body>
        <div><h3>Menu</h3></div>
        <div class="col-md-1">
            <button type="button" class="btn btn-success" id = "facturation">Facturation</button>
        </div>
        <div class="col-md-1">
            <button type="button" class="btn btn-success" id = "recouvrement">Recouvrement</button>
        </div>
    </body>
</html>
