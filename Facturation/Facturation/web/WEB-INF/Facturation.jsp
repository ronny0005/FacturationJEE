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
 <script src="script.js"></script>
</head>
<body>


 <div class="container">
   
     <div class="col-md-12">
<fieldset class="entete">
<legend class="entete">Entete</legend>
		<form class="form-horizontal" >
  <div class="form-group">
     <label for="inputfirstname" class="col-md-1 control-label">
       N Doc</label>
     <div class="col-md-3">
          <input type="text" class="form-control" id="inputfirstname" placeholder="N Document" />
     </div>
	 
	 <label for="inputfirstname" class="col-md-1 control-label">
       Souche</label>
     <div class="col-md-3">
          <input type="text" class="form-control" id="souche" name="souche" placeholder="Souche" />
     </div>
	 
	 <label for="inputfirstname" class="col-md-1 control-label">
       Affaire</label>
     <div class="col-md-3">
          <input type="text" class="form-control" id="affaire" name="affaire" placeholder="Affaire" />
     </div>
	 
  </div>
<div class="form-group">
    <label for="inputdateofbirth" class="col-md-1 control-label">Client</label>
        <div class="col-md-3">
          <select class="form-control" name="client" id="client">
          </select>
            <input type="hidden" name="cat_tarif" id="cat_tarif" value="4" />
            <input type="hidden" name="cat_compta" id="cat_compta" value="15"/>
            <input type="hidden" name="taxe1" id="taxe1" value="0" />
            <input type="hidden" name="taxe2" id="taxe2" value="0"/>
            <input type="hidden" name="taxe3" id="taxe3" value="0"/>
        </div>
		 <label for="inputdateofbirth" class="col-md-1 control-label">Depot</label>
        <div class="col-md-3">
            <select name="depot" id="depot" class="form-control">
            </select>
        </div>
		<label for="inputdateofbirth" class="col-md-1 control-label">Collaborateur</label>
        <div class="col-md-3">
            <select name="collaborateur" id="collaborateur" class="form-control">
            </select>
        </div>
     </div>
</form>
</fieldset>

<fieldset class="entete">
<legend class="entete">Ligne</legend>
<div class="form-group">
     <div class="col-md-2">
        <input class="form-control" id="reference" name="reference" placeholder="Référence" />
    
     </div>
	<div class="col-md-5">
        <input class="form-control" id="designation" name="designation" placeholder="Désignation" />
     
     </div>
	 <div class="col-md-2">
          <input type="text" class="form-control" id="prix" name="prix" placeholder="P.U" />
     </div>
	 <div class="col-md-2">
          <input type="text" class="form-control" id="quantite" placeholder="Quantité" />
     </div>
	 
	 <div class="col-md-1">
         <button type="button" id="ajouter" name="ajouter" class="btn btn-success">Ajouter</button>
     </div>
</div>

<div class="form-group">
 <table id="table" class="table">
    <thead>
      <tr>
        <th>Référence</th>
        <th>Désignation</th>
        <th>Prix Unitaire</th>
		<th>Quantité</th>
		<th>Montant HT</th>
		<th>Montant TTC</th>
      </tr>
    </thead>
    <tbody>
      
    </tbody>
  </table>
 </div>
 
 </fieldset>
 
 
 <fieldset class="entete">
<legend class="entete">Pied</legend>
 <div class="form-group">
	<div>
		<label for="inputdateofbirth" class="col-md-2 control-label">Total HT : </label>
	</div>
	<div>
		<label for="inputdateofbirth" class="col-md-10 control-label" id="totalht"> 0 </label>
	</div>
</div>
<div class="form-group">
	<div>
		<label for="inputdateofbirth" class="col-md-2 control-label">TVA : </label>
	</div>
	<div>
		<label for="inputdateofbirth" class="col-md-10 control-label" id="totaltva"> 0 </label>
	</div>
</div>
<div class="form-group">
	<div>
		<label for="inputdateofbirth" class="col-md-2 control-label">PRECOMPTE : </label>
	</div>
	<div>
		<label for="inputdateofbirth" class="col-md-10 control-label" id="totalprecompte"> 0 </label>
	</div>
</div>
<div class="form-group">
	<div>
		<label for="inputdateofbirth" class="col-md-2 control-label">MARGE : </label>
	</div>
	<div>
		<label for="inputdateofbirth" class="col-md-10 control-label" id="totalmarge"> 0 </label>
	</div>
</div>
<div class="form-group">
	<div>
		<label for="inputdateofbirth" class="col-md-2 control-label">Total TTC : </label>
	</div>
	<div>
		<label for="inputdateofbirth" class="col-md-10 control-label" id="totalttc"> 0 </label>
	</div>
</div>
</fieldset>

<div class="form-group">
<div  class="col-md-3">
	<div class="col-md-6">
         <button type="button" class="btn btn-success">Annuler</button>
     </div>
	<div class="col-md-6">
            <button type="button" class="btn btn-success" id="valider">Valider</button>
     </div>
</div>
</div>
   
</div>
</div>

 
 
 
</body>
</html>