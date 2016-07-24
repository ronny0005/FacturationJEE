<%-- 
    Document   : recouvrement
    Created on : 18 juil. 2016, 19:51:42
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap 101 Template</title><link href="bootstrap.css" rel="stylesheet">
        <link href="jquery-ui.css" rel="stylesheet">
        <link href="jquery-ui.theme.css" rel="stylesheet">
        <link href="fieldset.css" rel="stylesheet">
        <script src="jquery.js"></script>
        <script src="jquery_ui.js"></script>
        <script src="bootstrap-3.1.1.min.js"></script>
        <script src="jquery.multiselect.min.js"></script>
        <script src="scriptCombobox.js"></script>
        <script src="scriptRecouvrement.js"></script>
    </head>
    <body>
        <div class="container">

            <div class="col-md-12">
                <fieldset class="entete">
                    <legend class="entete">Entete</legend>
                    <form class="form-horizontal" >
                        <div class="form-group">

                            <label for="inputdateofbirth" class="col-md-2 control-label">Client</label>
                            <div class="col-md-2">
                                <select class="form-control" name="client" id="client">
                                </select>
                            </div>


                            <div class="col-md-1">
                                <button type="button" class="btn btn-success" id="recherche">Rechercher</button>
                            </div>




                        </div>

                    </form>
                </fieldset>

                <fieldset class="entete">
                    <legend class="entete">Ligne</legend>
                    <div class="form-group">
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="dateRec" placeholder="Date" />
                        </div>
                        <div class="col-md-5">
                            <input type="text" class="form-control" id="libelleRec" placeholder="Libelle" />
                        </div>
                        <div class="col-md-2">
                            <input type="text" class="form-control" id="montantRec" placeholder="Montant" />
                        </div>

                        <div class="col-md-1">
                            <button type="button" class="btn btn-success" id = "validerRec">Valider</button>
                        </div>
                    </div>

                    <div class="form-group">
                        <table class="table" id="tableRecouvrement">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Libelle</th>
                                    <th>Montant</th>
                                    <th>Solde</th>
                                    <th>Caisse</th>
                                    <th>Caissier</th>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                        <table class="table" id="tableFacture">
                            <thead>
                                <tr>
                                    <th>Date</th>
                                    <th>Libelle</th>
                                    <th>Référence</th>
                                    <th>Avance</th>
                                    <th>TTC</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
 
                    </div>

                </fieldset>

            </div>
        </div>
    </body>
</html>
