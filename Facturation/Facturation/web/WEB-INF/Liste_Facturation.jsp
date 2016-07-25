<!DOCTYPE html>
<html lang="en">
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <title>Bootstrap 101 Template</title>
 <link href="bootstrap.css" rel="stylesheet">
 <link href="jquery-ui.css" rel="stylesheet">
 <link href="jquery-ui.theme.css" rel="stylesheet">
 <link href="fieldset.css" rel="stylesheet">
<script src="jquery.js"></script>
 <script src="jquery_ui.js"></script>
 <script src="bootstrap-3.1.1.min.js"></script>
 <script src="scriptCombobox.js"></script>
 <script src="script_listefacturation.js"></script>
</head>
<body>
 
 <div class="container">
   
     <div class="col-md-12">

<fieldset class="entete">
<legend class="entete">Liste Facture</legend>

<div class="form-group">
<table id="table" class="table">
    <thead>
        <tr>
            <th>Numéro Pièce</th>
            <th>Reference</th>
            <th>Date</th>
            <th>Client</th>
            <th>Collaborateur</th>
            <th></th>
        </tr>
    </thead>
    <tbody> 
    </tbody>
</table>
 </div>

<div class="form-group">
<div  class="col-md-6">
    <div class="col-md-3">
        <button type="button" id="nouveau" class="btn btn-success">Nouveau</button>
    </div>
</div>
</div>
   
</div>
</div>
    
<div id="dialog-confirm" title="Suppression">
  <p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>Voulez vous supprimez cette ligne ?</p>
</div>
</body>
</html>