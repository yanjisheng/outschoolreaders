/**
 * @author yanjisheng
 */
var $center = $(".center");
var widthStr = $center.css("width");
var width = widthStr.substring(0, widthStr.length-2);
setInterval(function(){
	if(width > document.body.clientWidth){
		$center.css("width", document.body.clientWidth);
	}else{
		$center.css("width", width);
	}
}, 200);