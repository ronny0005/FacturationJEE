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
    var modification=false;
    var position=0;
    var co_no="";
    var datedeb=$.datepicker.formatDate('yy-mm-dd', new Date());
    var datefin=$.datepicker.formatDate('yy-mm-dd', new Date());
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
            listefacture(datedeb,datefin);
          });
        }
    });

    function listefacture(datedeb,datefin){
        $.ajax({
            url: lien + "getFactureCO?CO_No=" + co_no + "&datedeb=" + datedeb + "&datefin=" + datefin + "&CT_Num=0",
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                $.each(data, function() {
                    for(i=0;i<this.length;i++){
                        classe = "";
                        if (i % 2 == 0)
                            classe = "info";
                        rechargeTableau(classe,this[i].DO_Piece,this[i].DO_Ref,this[i].DO_Date,this[i].CT_Num);
                    }
                });
            }
        });
    }
    
    function rechargeTableau(classe,DO_Piece,reference,DO_Date,CT_Num,Collaborateur){
        $( "#table" ).append( "<tr class='facture "+classe+"' id='article_"+DO_Piece+"'><td>"+DO_Piece+"</td>\n\
                                <td>"+reference+"</td><td>"+DO_Date+"</td><td>"+CT_Num+"</td>\n\
<td>"+Collaborateur+"</td><td id='suppr_art_"+DO_Piece+"'>X</td></tr>").on('click', '#suppr_art_'+DO_Piece, function () {                              
        $( "#dialog-confirm" ).dialog({
            resizable: false,
            height: "auto",
            width: 400,
            modal: true,
            buttons: {
                "Oui": function() {
                    $( this ).dialog( "close" );
                    //supprElementTableau(DO_Piece);

                },
                "Non": function() {
                  $( this ).dialog( "close" );
                }
            }
        });
        });
        }
        
    $('#nouveau').click(function(){
        window.location.replace("FacturationServlet?login="+$_GET("login")+"&mdp="+$_GET("login"));
    });
     $("#dialog-confirm").hide();   
});