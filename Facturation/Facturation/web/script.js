jQuery(function($){ 
    var lien="http://192.168.1.14:8083/api/";
    var cat_tarif =0;
    var ltotalttc = Array();
    var ltotalht = Array();
    var ltotaltva = Array();
    var ltotalprecompte = Array();
    var ltotalmarge = Array();
    var ldesignation = Array();
    var lreference = Array();
    var lqte = Array();
    var lprix = Array();
    var nbarticle=0;
    var souche ="";
    var affaire="";
    var vehicule="";
    var depot="";
    var co_no="";
    var modification=false;
    var position=0;
    var entete="";
    var totalttc=0;
    
    function liste_article(ndepot){
        $( "#designation" ).replaceWith( '<select class="form-control" id="designation" name="designation" placeholder="Désignation" />');
        $( "#designation" ).change(function() {
                        $( "#designation option:selected" ).each(function() {
                             $.ajax({
                                url: lien+"getPrixClient?AR_Ref="+$("#designation").val()+"&N_CatTarif="+$("#cat_tarif").val()+"&N_CatCompta="+$("#cat_compta").val(),
                                method: 'GET',
                                dataType: 'json',
                                success: function(data) {
                                  $.each(data, function() {
                                        $.ajax({
                                        url: lien+'isStock?AR_Ref='+$("#designation").val()+'&DE_No='+depot,
                                        method: 'GET',
                                        dataType: 'json',
                                        success: function(data) {
                                            $.each(data, function() {
                                                $("#quantite_stock").val(this.AS_QteSto);
                                            });
                                        }
                                        });
                                        $("#reference").val(this.AR_Ref);
                                        $("#prix").val(this.AR_PrixAch);
                                        $("#taxe1").val(this.taxe1);
                                        $("#taxe2").val(this.taxe2);
                                        $("#taxe3").val(this.taxe3);
                                          
                                  });
                                }
                              });
                        });
                  }).trigger( "change" );
            $.ajax({
            url: lien+'getAllArticleDispoByArRef?DE_No='+ndepot,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                  for(i=0;i<this.length;i++){
                      var option = $('<option />');
                        if(i==0)
                            option.attr('selected','selected');
                        option.attr('value', this[i].AR_Ref).text(this[i].AR_Design);
                        $('#designation').append(option);
                  }
              });
            }
          });
          }

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
    $.ajax({
        url: lien+"connect?NomUser="+$_GET("login")+"&Password="+$_GET("mdp"),
        method: 'GET',
        dataType: 'json',
        success: function(data) {
        $.each(data, function() {
            affaire = this.affaire;
            $.ajax({
                url: lien+'affaire',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                  $.each(data, function() {
                    for(i=0;i<this.length;i++){
                        if(affaire==this[i].CA_Num)
                            $('#affaire').val(this[i].CA_Intitule);
                        $('#affaire').prop('disabled', true);
                    }
                  });
                }
            });
              souche = this.DO_Souche;
            $.ajax({
            url: lien+'souche',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                for(i=0;i<this.length;i++){
                    if(souche==this[i].JO_Num){
                        $('#souche').val(this[i].s_Intitule);
                    }
                    $('#souche').prop('disabled', true);
                }
              });
            }
            });
              depot = this.DE_No;
              co_no= this.CO_No;
            $.ajax({
                url: lien+'depot',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                  $.each(data, function() {
                    for(i=0;i<this.length;i++){
                        if(depot==this[i].DE_No){
                            $('#collaborateur').val(this[i].DE_Intitule);
                            liste_article(this[i].DE_No);
                        }
                        $('#collaborateur').prop('disabled', true);
                    }
                  });
                }
            });
            vehicule=this.vehicule;
              $.ajax({
            url: lien+'depot',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                for(i=0;i<this.length;i++){
                    if(depot==this[i].DE_No){
                        $('#depot').val(this[i].DE_Intitule);
                        liste_article(this[i].DE_No);
                    }
                    $('#depot').prop('disabled', true);
                    
                }
              });
            }
            });
          });
        }
    });

    $( "#dialog-confirm" ).hide();
    
    $('.col-md-2').on('keydown', '#quantite', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});

    $("#reference").prop('disabled', true);
    function premierArticle(valeur){
        $(".comboclient").prop('disabled', valeur);
    }
    
    function formulaireRempli(){
        if($(".comboclient").val().length!=0 && $("#designation option:selected").length!=0
            && $("#quantite").val().length!=0)
        return true;
        else 
        return false;
        
    }
          function verifEntete(){
              if(formulaireRempli()){
                  premierArticle(true);
                  return true;
                }
                return false;
          }
          
          function ajoutLigne(entete,AR_Ref,DL_Ligne,DL_Qte,DL_Remise,vehicule,cr){
            $.ajax({
                url: "http://localhost:8080/Facturation/AjoutLigneServlet?DO_Piece=" + entete + "&AR_Ref=" +AR_Ref + "&DL_Ligne=" +DL_Ligne+"&DL_Qte=" + DL_Qte+"&DL_Remise="+DL_Remise+"&vehicule="+vehicule+"&cr="+cr,
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    if(data=="0")
                        ajoutLigne(entete,AR_Ref,DL_Ligne,DL_Qte,DL_Remise,vehicule,cr);
                }
            });
          }
          function ajoutEnteteLigne (){
              
            var cmpt_row=0;
            $.ajax({
                url: "http://localhost:8080/Facturation/AjoutEnteteServlet?CO_No="+co_no+"&CT_Num="+$("#client").val()+"&ref=ref&N_Reglement=1&Latitude=0&Longitude=0&date=" + $.datepicker.formatDate('yy-mm-dd', new Date()),
                method: 'GET',
                dataType: 'html',
                success: function(data) {
                    entete = data;
                    $('#table tr').each(function() {
                        if(cmpt_row>0){  
                            var ligne = cmpt_row*10000;
                            ajoutLigne(entete,$(this).find("td").eq(0).html(),ligne,$(this).find("td").eq(3).html(),0,"","");
                        }
                        cmpt_row=cmpt_row+1;
                    });
                    
                    reglement(entete,"reglt_"+entete);
                    $('.article').remove();
                }
            });
          }
	$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
        
        $('#valider').click(function(){
            if(nbarticle>0){
                $(".valideReglement").dialog({
                resizable: false,
                height: "auto",
                width: 1000,
                modal: true,
                buttons: {
                    "Valider": function() {
                        totalttc=0;
                        for(i=0;i<nbarticle;i++)
                            totalttc=totalttc+ltotalttc[i];
                        montant=0
                        if($('#credit').is(':checked'))
                            montant=$("#mtt_avance").val();
                        if(totalttc>=montant){
                        premierArticle(false);
                        ajoutEnteteLigne();
                        vide();
                        $( this ).dialog( "close" );
                        }else {alert("L'avance est superieur au total")};
                        
                    },
                    "Annuler": function() {
                      $( this ).dialog( "close" );
                    }
                }
            });
            }
        });
    function reglement(DO_Piece,ref){
        montant=0
        if($('#credit').is(':checked'))
            montant=$("#mtt_avance").val();
        
        $.ajax({
            url: lien+"regleDocentete?DO_Piece="+DO_Piece+"&ref="+ref+"&avance="+montant,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
            }
        });
    }
        $('#connexion').click(function(){
            $.ajax({
            url: lien+"connect?NomUser="+$('#login').val()+"&Password="+$('mdp').val(),
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                if(this.id_parametre!=0)
                    window.location.replace("/Facturation/MenuServlet?login="+$('#login').val()+"&mdp="+$('#mdp').val());
            }
            });
        });
        $('#listefac').click(function(){
            window.location.replace("/Facturation/ListeFacturation?login="+$_GET("login")+"&mdp="+$_GET("mdp"));
        });
        
    
	$('#ajouter').click(function(){
            if(modification==false){
                if(verifEntete())
                    AjoutArticle();
                else 
                    alert("le champ client ou quantite est vide");
            }else{
                var qte =$("#quantite").val();
                var prix = $("#prix").val();
                lprix[position-1]=$("#prix").val();
                lqte[position-1]=$("#quantite").val();
                var taxe1 = Math.round((prix * $("#taxe1").val())/100);
                var taxe2 = Math.round((prix * $("#taxe2").val())/100);
                var taxe3 = Math.round((prix * $("#taxe3").val())/100);
                ltotaltva.push(taxe1);
                ltotalprecompte.push(taxe2);
                ltotalmarge.push(taxe3);
                ltotalttc[position-1]=Math.round(prix*qte)+taxe1+taxe2+taxe3;
                ltotalht[position-1]=Math.round(prix*qte);
                ltotaltva[position-1]=taxe1;
                ltotalprecompte[position-1]=taxe2;
                ltotalmarge[position-1]=taxe3;
                afficheMontant();
                $('.article').remove();
                recharge();
                $("#quantite").val("");
                modification=false;
            }
        });
        
        $('#credit').click(function(){
            if($('#credit').is(':checked')){
                $('#comptant').prop('checked', false);
                $('#mtt_avance').prop('disabled', false);
            }
        });
        
        $('#comptant').click(function(){
            if($('#comptant').is(':checked')){
                $('#credit').prop('checked', false);
                $('#mtt_avance').prop('disabled', true);
            }
        });
        
        $('#annuler').click(function(){
            vide();
            $('.article').remove();
            premierArticle(false);
        });
	
	function AjoutArticle(){
            $.ajax({
            url: lien+'isStock?AR_Ref='+$("#designation").val()+'&DE_No='+depot,
            method: 'GET',
            dataType: 'json',
            success: function(data) {
                $.each(data, function() {
               if(this.AS_QteSto>0){
                   
                var ref = $("#designation").val();
		var design = $("#designation option:selected").text();
		var client = $("#client").val();
		var prix = $("#prix").val();
		var num =Math.floor((Math.random() * 10) + 1); 
		var qte = $("#quantite").val();
                var taxe1 = Math.round((prix * $("#taxe1").val())/100);
                var taxe2 = Math.round((prix * $("#taxe2").val())/100);
                var taxe3 = Math.round((prix * $("#taxe3").val())/100);
                var montantht=Math.round(prix*qte);
                var montantttc=montantht+taxe1+taxe2+taxe3;
                lreference.push(ref);
                ldesignation.push(design);
                lqte.push(qte);
                lprix.push(prix);
                ltotalttc.push(montantttc);
                ltotalht.push(montantht);
                ltotaltva.push(taxe1);
                ltotalprecompte.push(taxe2);
                ltotalmarge.push(taxe3);
    
                nbarticle++;
                afficheMontant();
                var classe="";
                if(nbarticle%2==0)
                    classe="info";
                $("#quantite").val("");
                rechargeTableau(ref,design,classe,nbarticle,qte,prix,montantttc,montantht);
        }else {
            alert("stock indisponible");
        }
    });
    }
    });
        }

function recharge(){
    var classe ="";
    for(i=0;i<nbarticle;i++){
        if((i+1)%2==0){
            classe="info";
        }
        rechargeTableau(lreference[i],ldesignation[i],classe,(i+1),lqte[i],lprix[i],ltotalttc[i],ltotalht[i]);
    }
}
        function rechargeTableau(ref,design,classe,valeur,qte,prix,montantttc,montantht){
            $( "#table" ).append( "<tr class='article "+classe+"' id='article_"+valeur+"'><td>"+ref+"</td>\n\
                                        <td>"+design+"</td><td>"+prix+"</td><td>"+qte+"</td>\n\
<td>"+montantht+"</td><td>"+montantttc+"</td><td id='modif_art_"+valeur+"'>M</td><td id='suppr_art_"+valeur+"'>X</td></tr>").on('click', '#suppr_art_'+valeur, function () {                              
    $( "#dialog-confirm" ).dialog({
				  resizable: false,
				  height: "auto",
				  width: 400,
				  modal: true,
				  buttons: {
					"Oui": function() {
                                            $( this ).dialog( "close" );
                                            supprElementTableau(valeur);
                                            
					},
					"Non": function() {
					  $( this ).dialog( "close" );
					}
				  }
				  });
        }).on('click', '#modif_art_'+valeur, function () {
            $('#prix').val(prix);
            $('#reference').val(ref);
            $('#designation').val(ref);
            $('#quantite').val(qte);
            position=valeur;
            modification=true;
        });
        }
        
        function supprElementTableau(num){
            for(i=num-1;i<nbarticle;i++){
                if(i!=nbarticle-1){
                    lreference[i]=lreference[i+1];
                    ldesignation[i]=ldesignation[i+1];
                    ltotalttc[i]=ltotalttc[i+1];
                    ltotalht[i]=ltotalht[i+1];
                    ltotaltva[i]=ltotaltva[i+1];
                    ltotalprecompte[i]=ltotalprecompte[i+1];
                    ltotalmarge[i]=ltotalmarge[i+1];
                    lprix[i]=lprix[i+1];
                    lqte[i]=lqte[i+1];
                }
            }
            lreference.pop();
            ldesignation.pop();
            ltotalttc.pop();
            ltotalht.pop();
            ltotaltva.pop();
            ltotalprecompte.pop();
            ltotalmarge.pop();
            lprix.pop();
            lqte.pop();
            nbarticle--;
            afficheMontant();
            $('.article').remove();
            recharge();
        }
        
        function afficheMontant(){
            var montantttc=0;
            var montantht=0;
            var montanttva=0;
            var montantprecompte=0;
            var montantmarge=0;
            for(i=0;i<nbarticle;i++){
                montantttc=montantttc+ltotalttc[i];
                montantht=montantht+ltotalht[i];
                montanttva=montanttva+ltotaltva[i];
                montantprecompte=montantprecompte+ltotalprecompte[i];
                montantmarge=montantmarge+ltotalmarge[i];
            }
            $("#totalttc").html(montantttc);
            $("#totalht").html(montantht);
            $("#totaltva").html(montanttva);
            $("#totalprecompte").html(montantprecompte);
            $("#totalmarge").html(montantmarge);
        }
        
	function vide(){
            $(".comboclient").val("");
            $("#reference").val("");
            $("#qte").val("");
            $("#prix").val("");
            $("#totalttc").html("0");
            $("#totalht").html("0");
            $("#totaltva").html("0");
            $("#totalprecompte").html("0");
            $("#totalmarge").html("0");
            nbarticle=0;
	}
          
         
        $.ajax({
            url: lien+'clients?op=DLA',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                  for(i=0;i<this.length;i++){
                      var option = $('<option />');
                      if(i==0)
                          option.attr('selected','selected');
                      
                        option.attr('value', this[i].CT_Num).text(this[i].CT_Intitule);
                        $('#collaborateur').append(option);
                  }
              });
            }
          });
    $( "#collaborateur" ).combobox();
    $('#quantite_stock').prop('disabled', true);
    $('#n_doc').prop('disabled', true);
    
    $('#mtt_avance').prop('disabled', true);
    $( ".valideReglement" ).hide();
     
});