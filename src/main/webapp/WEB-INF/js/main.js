var inProgress = [false, -2, ['zappos', 'tjmaxx', 'macys'], 'blah', 0];
var ajaxInterval;
var itemID;
var vendorName;
var selectedItems = new Array();

$(document).ready(function () {
    itemID = getURLParameter('itemID');
    vendorName = getURLParameter('vendor');
    inProgress[3] = vendorName;
    ajaxInterval = setInterval( ajaxCalls, 1000 );
    getItemData( itemID );
    


});

// CREDIT: http://stackoverflow.com/questions/1403888/get-url-parameter-with-javascript-or-jquery
var getURLParameter = function(name) {
    return decodeURI(
        (RegExp(name + '=' + '(.+?)(&|$)').exec(location.search)||[,null])[1]
    );
}

var getWedgie = function () {
        var url = "http://localhost:8080/widget/wedgies/create?ids=" + selectedItems[0] + ',' + selectedItems[1] + ',' + selectedItems[2]; 
        $.ajax({
                type: "GET",
                url: url,
                //data: null,
                dataType: "json",
                success: function (data) {
                                alert(data);
                        }
        });
}

var getItemData = function ( itemID ) {
        
        var url = "http://localhost:8080/widget/itemId/" + itemID; 
        $.ajax({
                type: "GET",
                url: url,
                //data: null,
                dataType: "json",
                success: function (data) {
                                insertItem( data );
                                ajaxInterval = setInterval( ajaxCalls, 1000 );
                        }
        });
}

var ajaxCalls = function () {
    console.log(inProgress[0]);
    if ( !inProgress[0] ){
        if ( inProgress[1] == -2 ){
            inProgress[0] = true;
            inProgress[1] = ( inProgress[2][0] == inProgress[3] )?0:-1;
            getAjaxData( inProgress[3] );
        }
        else if ( inProgress[1] < 2 ){
            inProgress[0] = true;
            inProgress[1] += 1;
            var vendor = inProgress[2][inProgress[1]];
            
            if ( vendor != inProgress[3] ){
                getAjaxData(vendor);
            }
            else{
                if ( inProgress[1] != 0 ){
                    inProgress[0] = false;
                    return;
                }
                else{
                    getAjaxData(vendor);
                }
            }
        }
        else{
            clearInterval(ajaxInterval);
        }
    }
}       

var getAjaxData = function ( vendor ) {
        
    console.log(inProgress[0] + ' --- ' + inProgress[1] + ' --- ' + vendor + ' --- ' + inProgress[3] + ' --- ' + inProgress[4]);
    
    var url = "http://localhost:8080/widget/vendor/" + vendor + "/itemId/" + itemID; // + "?callback=data";
    $.ajax({
        type: "GET",
        url: url,
        //data: null,
        dataType: "json",
        success: function (data) {
            //alert( data[0].imageUrl );
            inProgress[4] += 1;
            insertVendor( vendor, data );
            inProgress[0] = false;
        }
    });
}

var insertItem = function ( data ) {

                var vendorSection = $('.item');
                innerHTML = 
                        '<div class="row-fluid scan">' +
                                '<div class="span4">' + 
                                        '<img src="' + data.imageUrl + '" />' +
                                '</div>' +
                                '<div class="span8">' +
                                        '<h1>' +
                                                'Sexy outfit' +
                                                '<span>' +
                                                        'Here are a few things to go with it... ' +
                                                '</span>' +
                                        '</h1>' +
                                        '<div class="action-call row-fluid">' + 
                                                '<div class="span12">' + 
                                                        'Want other opinions? Create a poll and ask friends!' + 
                                                '</div>' + 
                                        '</div>' + 
                                        '<div class="action-call row-fluid">' +
                                                '<div class="select-random span6 text-center"><a href="#">Select random items for me</a></div>' +
                                                '<div class="select-user span6 text-center"><a href="#">Let me choose 3 items</a></div>' +
                                        '</div>' + 
                                '</div>' +
                        '</div>';
                
                vendorSection.append(innerHTML);
                
                $(".select-user, .select-random").on("click", function() { 
                    $(".submit-buttons").show();
                });
                
        }      
        
var insertVendor = function ( vendorName, data ) {

    var vendorSection = $('.vendor' + inProgress[4]);
    innerHTML = 
    //'<section class="container-fluid ' + vendorName + '"> ' +
        '<div class="row-fluid">' + 
            '<h2 class="span12">' + 
                vendorName +
            '</h2>' +
        '</div>' + 
        '<div class="row-fluid">';
            
    $.each( data, function ( i, val ) {
        if ( i < 4 ){
            innerHTML +=
            '<div class="span3 result">' +
                '<h3>Brand Name' +
                    '<span>' +
                        val.productName +
                    '</span>' +
                    '<img class="compare" data-compareURL="' + val.itemId + '" src="http://dribbble.s3.amazonaws.com/users/220205/screenshots/783935/checkbox.jpg" width="100"/>' +
                '</h3>' +
                '<div class="price">' +
                    val.price + 
                    '<span>' +
                        val.salePrice +
                    '</span>' +
                '</div>' +
                '<div class="clearfix"></div>' +
                '<img class="high-res" src="' + val.highresImageUrl + '" />' +
            '</div>';
        }
    });
                
    innerHTML += '</div>';
    
    vendorSection.append(innerHTML);

    $('.vendor' + inProgress[4] + ' .high-res').each( function() {
        $(this).on("click", function() {
    	        //$(this).css('top', '-260px');

          	$(this).animate({
	            	top: "-=1000"
	          	}, 1000, function() {
	            	// Animation complete.
	          	});
	        });      
        });
     
    $('.vendor' + inProgress[4] + ' .compare'). each( function ( ) {
        $(this).on("click", function( e ) {
                e.preventDefault();
                console.log(selectedItems.length);
                var len = isNaN(selectedItems.length)?0:selectedItems.length;
            	if ( len < 3 )
            	{
                    	var itemID = $(this).attr("data-compareURL");
                    	selectedItems[ len ] = itemID;
                    	console.log( 'length = ' + len + ' **** ' + itemID + ' *** ' + selectedItems[ len ]);
                    	if ( len == 2 )
                    	{
                    	        getWedgie();
                    	}
            	}
            	else
            	{
            	        console.log ( selectedItems[0] + ' -+- ' + selectedItems[1] + ' -+- ' + selectedItems[2] );
            	        
            	}
    	});
    });   
}
