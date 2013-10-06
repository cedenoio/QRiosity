// function animate(){
// 	$('.animate').animate({
// 		top: "-=1300"
// 	}, 1000, function() {
// 		//
// 	});
// }

// $('.result img').click(function() {
// 	$('.result img').removeClass('animate').css('top','-260px');
// 	$(this).addClass('animate');
// 	animate();
// });

(function(){
 
	console.log('Ran');
	var animate = true;
	console.log("animate set:", animate);

	$('.result img').click(function() {
		if(animate === true){
			animate = false
			$(this).animate({
			    left: "-=1300"
			  }, 1000, function() {
			    animate
		 	});
		}
	});

})()