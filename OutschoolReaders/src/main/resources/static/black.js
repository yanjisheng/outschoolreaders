/**
 * @author yanjisheng
 */
function toggle(){
	if(localStorage.getItem("black")==null || localStorage.getItem("black")!="black"){
		localStorage.setItem("black", "black");
		document.body.className="black";
	}else{
		localStorage.setItem("black", "white");
		document.body.className="";
	}
	localStorage.removeItem("hideToggle");
}
function hideme(){
	document.getElementById("style-changer").style.display="none";
	localStorage.setItem("hideToggle", "yes");
}
if(localStorage.getItem("black")=="black"){
	document.body.className="black";
}
if(localStorage.getItem("hideToggle")=="yes"){
	var path = window.location.pathname;
	if(path=="/" || path=="/reader/main.html" || path=="/manager/login.html"){
		
	}else{
		document.getElementById("style-changer").style.display="none";
	}	
}