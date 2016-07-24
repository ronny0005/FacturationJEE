jQuery(function ($) {
    var lien = "http://192.168.1.14:8083/api/";
    var cat_tarif = 0;
    var totalttc = 0;
    var totalht = 0;
    var totaltva = 0;
    var totalprecompte = 0;
    var totalmarge = 0;
    var nbarticle = 0;
    var nbreglement = 0;
    var souche ="";
    var affaire="";
    var vehicule="";
    var depot="";
    var co_no="";
    var Val_RG_Piece="";
    var Mtt_RG_Piece="";
    
    function verifEntete() {
        if ($("#client option:selected").val() != "" && $("#depot").val() != "") {
            return true;
        }
        return false;
    }
    
    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });

    $('#valider').click(function () {

        ajoutEnteteLigne();
    });

    $('#ajouter').click(function () {

        if (verifEntete()) {
            AjoutArticle();
        }
    });
    $('#recherche').click(function () {
        rechercher();
        rechercherFacture();
    });

    $('#validerRec').click(function () {
        validerRec();
    });
    
    
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
            souche = this.DO_Souche;
            depot = this.DE_No;
            co_no= this.CO_No;
            vehicule=this.vehicule;
          });
        }
    });

    function rechercherFacture() {
        $.ajax({
            url: lien + "getFactureCO?CO_No=" + co_no + "&datedeb=0&datefin=0&CT_Num=" + $("#client").val(),
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                $.each(data, function() {
                    $(".facture").remove();
                    for(i=0;i<this.length;i++){
                        classe = "";
                        if (nbreglement % 2 == 0)
                            classe = "info";
                        tableaufacture(classe,this[i].DO_Date,this[i].DO_Piece,this[i].DO_Ref,this[i].avance,this[i].ttc,this[i].CA_No,this[i].CO_NoCaissier);
                    }
                });
            }
        });
    }
    
    function tableaufacture(classe,DO_Date,DO_Piece,DO_Ref,avance,ttc){
        $("#tableFacture").append("<tr class= 'facture'" + classe + "' id='facture_" +DO_Piece+ "'><td>"+DO_Date+"</td><td>" + DO_Piece + "</td>\n\
<td>" + DO_Ref + "</td><td>" + avance + "</td><td>" + ttc + "</td></tr>").on('click', '#facture_'+DO_Piece, function () {
        });
    }
    
    
    $( "#tableFacture" ).hide();
    function rechercher() {
        $.ajax({
            url: lien + 'getReglementClient?client=' + $("#client").val(),
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                $(".reglement").remove();
                $.each(data, function() {
                    classe = "";
                    for(i=0;i<this.length;i++){
                        if (nbreglement % 2 === 0)
                            classe = "info";
                        tableauRecouvrement(classe,this[i].RG_Piece,this[i].RG_Date,this[i].RG_Libelle,this[i].RG_Montant,this[i].CA_No,this[i].CO_NoCaissier);
                    }
                });
            }
        });
    }


    function tableauRecouvrement(classe,RG_Piece,RG_Date,RG_Libelle,RG_Montant,CA_No,CO_NoCaissier){
        $("#tableRecouvrement").append("<tr class= 'reglement'" + classe + "' id='reglement_" + RG_Piece + "'><td>" + RG_Date + "</td>\n\
                                        <td>" + RG_Libelle + "</td><td>" + RG_Montant + "</td><td>" + RG_Montant + "</td>\n\
                                         <td>" + CA_No + "</td><td>" + CO_NoCaissier + "</td>").on('click', '#reglement_'+RG_Piece, function () {
            RG_Piece=RG_Piece;
            $("#tableFacture").dialog({
                resizable: false,
                height: "auto",
                width: 400,
                modal: true,
                buttons: {
                    "Oui": function() {
                        $( this ).dialog( "close" );
                        supprElementTableau(nbarticle);
                    },
                    "Non": function() {
                      $( this ).dialog( "close" );
                    }
                }
            });
        });
    }
    
    $(".multiselect").multiselect({height: 100});
    $("#dateRec").datepicker({dateFormat: "yy-mm-dd", altFormat: "yy-mm-dd"});
        
    function vide() {
        $(".article").remove();
        $("#date").val('');
        $("#client").val('');
        $("#designation").val('');
        $("#qte").val('');
    }

    function validerRec() {
        $.ajax({
            url: lien + 'addReglement?client=' + $("#client").val()+"&avance="+ $("#montantRec").val()+"&libelle="+$("#libelleRec").val()+"&CO_No=19&CA_No=1&date="+$("#dateRec").val(),
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                rechercher();
                rechercherFacture();
            }
        });
    }


    $.ajax({
        url: lien + 'clients?op=DLA',
        method: 'GET',
        dataType: 'json',
        success: function (data) {
            $.each(data, function () {
                for (i = 0; i < this.length; i++) {
                    var option = $('<option />');
                    if (i == 0)
                        option.attr('selected', 'selected');

                    option.attr('value', this[i].CT_Num).text(this[i].CT_Intitule);
                    $('#collaborateur').append(option);
                }
            });
        }
    });

    $("#collaborateur").combobox();



});