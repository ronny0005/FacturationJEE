jQuery(function($){
    var lien="http://192.168.1.14:8083/api/";
    var cat_tarif =0;
    var totalttc=0;
    var totalht=0;
    var totaltva=0;
    var totalprecompte=0;
    var totalmarge=0;
    var nbarticle=0;
    $('.col-md-2').on('keydown', '#quantite', function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13,110,190])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});

    $("#reference").prop('disabled', true);
    function premierArticle(valeur){
        $(".comboclient").prop('disabled', valeur);
        $(".combodepot").prop('disabled', valeur);
    }
    
    function formulaireRempli(){
        if($(".combodepot").val().length!=0 && $(".comboclient").val().length!=0 && $("#designation option:selected").length!=0
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
          function ajoutEnteteLigne (){
              var entete="";
            var cmpt_row=0;
            $.ajax({
                url: lien+"addDocentete?CO_No="+$("#depot").val()+"&CT_Num="+$("#client").val()+"&ref=ref&N_Reglement=1&Latitude=0&Longitude=0&date=" + $.datepicker.formatDate('yy-mm-dd', new Date()),
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    $.each(data, function() {
                    entete = this.DO_Piece;
                    $('#table tr').each(function() {
                        if(cmpt_row>0){  
                            var ligne = cmpt_row*10000;
                            $.ajax({
                                url: lien+"addDocligne?DO_Piece=" + entete + "&AR_Ref=" + $(this).find("td").eq(0).html() + "&DL_Ligne=" +ligne+"&DL_Qte=" + $(this).find("td").eq(3).html()+"&DL_Remise=0&vehicule=&cr=",
                                method: 'GET',
                                dataType: 'json',
                                success: function(data) {

                                }
                            }); 
                        }
                        cmpt_row=cmpt_row+1;
                    });
                    $('.article').remove();
                });
            }
            });
          }
	$('.message a').click(function(){
	   $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
        
        $('#valider').click(function(){
            if(nbarticle>0){
                premierArticle(false);
                ajoutEnteteLigne();
                vide();
            }
        });
        
	$('#ajouter').click(function(){
          
           if(verifEntete()){
               AjoutArticle();
           }
    });
	
	function AjoutArticle(){
            $.ajax({
            url: lien+'isStock?AR_Ref='+$("#designation").val()+'&DE_No='+$("#depot").val(),
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
                totalmarge=totalmarge+taxe3;
                totalprecompte=totalprecompte+taxe2;
                totaltva=totaltva+taxe1;
                totalttc=totalttc+montantttc;
                totalht=totalht+montantht;
                $("#totalttc").html(totalttc);
                $("#totalht").html(totalht);
                $("#totaltva").html(totaltva);
                $("#totalprecompte").html(totalprecompte);
                $("#totalmarge").html(totalmarge);
                nbarticle++;
                classe="";
                if(nbarticle%2==0)
                    classe="info";
                $("#quantite").val("");
                $( "#table" ).append( "<tr class='article "+classe+"' id='article_"+num+"'><td>"+ref+"</td>\n\
                                        <td>"+design+"</td><td>"+prix+"</td><td>"+qte+"</td>\n\
<td>"+montantht+"</td><td>"+montantttc+"</td>").on('click', '#modif_art_'+num, function () {
//<div id='modif_art_"+num+"'>modifier</div><div id='suppr_art_"+num+"'>supprimer</div></div>" 
                              
                $( "#dialog-confirm" ).dialog({
				  resizable: false,
				  height: "auto",
				  width: 400,
				  modal: true,
				  buttons: {
					"Delete all items": function() {
					  $("#article_"+num).remove();
					},
					Cancel: function() {
					  $( this ).dialog( "close" );
					}
				  }
				  });
        }).on('click', '#suppr_art_'+num, function () {
        });
        }else {
            alert("stock indisponible");
        }
    });
    }
    });
        }
	function vide(){
            $(".comboclient").val("");
            $(".combodepot").val("");
            $("#client").val("");
            $("#reference").val("");
            $("#qte").val("");
            $("#prix").val("");
            $("#totalttc").html("0");
            $("#totalht").html("0");
            $("#totaltva").html("0");
            $("#totalprecompte").html("0");
            $("#totalmarge").html("0");
	}
          
         
        $.ajax({
            url: lien+'clients?op=YDE',
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
          
          $.ajax({
            url: lien+'affaire',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                  for(i=0;i<this.length;i++){
                      var option = $('<option />');
                        option.attr('value', this[i].CA_Num).text(this[i].CA_Intitule);
                        $('#affaire').append(option);
                  }
              });
            }
          });
          
          $.ajax({
            url: lien+'souche',
            method: 'GET',
            dataType: 'json',
            success: function(data) {
              $.each(data, function() {
                  for(i=0;i<this.length;i++){
                      var option = $('<option />');
                        option.attr('value', this[i].JO_Num).text(this[i].s_Intitule);
                        $('#souche').append(option);
                  }
              });
            }
          });
 
    $( "#collaborateur" ).combobox();
    $( "#souche" ).combobox();
    $( "#affaire" ).combobox();
     
                                  
  
});