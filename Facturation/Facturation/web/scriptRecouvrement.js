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
            url: lien + "getFactureCO?CO_No=" + co_no + "&datedeb=0datefin=0&CT_Num=" + $("#client").val(),
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                alert(data);
                $(".facture").remove();
                d = data.data;
                for (var mi = 0; mi < d.length; mi++) {
                    nbarticle++;
                    classe = "";
                    if (nbreglement % 2 === 0)
                        classe = "info";
                    $("#tableFacture").append("<tr class= 'facture'" + classe + "' id='reglement_" + d[mi].RG_Piece + "'><td>" + d[mi].RG_Date + "</td>\n\
                                        <td>" + d[mi].RG_Libelle + "</td><td>" + d[mi].RG_Montant + "</td><td>" + d[mi].RG_Montant + "</td>\n\
                                         <td>" + d[mi].CA_No + "</td><td>" + d[mi].CO_NoCaissier + "</td>");
//                            .on('click', '#modif_art_' + num, function () {
//                    }).on('click', '#suppr_art_' + num, function () {
//                    });
                }

            }
        });
    }
    function rechercher() {
        $.ajax({
            url: lien + 'getReglementClient?client=' + $("#client").val(),
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                $(".reglement").remove();
                d = data.data;
                for (var mi = 0; mi < d.length; mi++) {
                    nbarticle++;
                    classe = "";
                    if (nbreglement % 2 === 0)
                        classe = "info";
                    $("#tableRecouvrement").append("<tr class= 'reglement'" + classe + "' id='reglement_" + d[mi].RG_Piece + "'><td>" + d[mi].RG_Date + "</td>\n\
                                        <td>" + d[mi].RG_Libelle + "</td><td>" + d[mi].RG_Montant + "</td><td>" + d[mi].RG_Montant + "</td>\n\
                                         <td>" + d[mi].CA_No + "</td><td>" + d[mi].CO_NoCaissier + "</td>");
//                            .on('click', '#modif_art_' + num, function () {
//                    }).on('click', '#suppr_art_' + num, function () {
//                    });
                }

            }
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