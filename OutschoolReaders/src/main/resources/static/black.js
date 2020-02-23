/**
 * @author yanjisheng
 */
if(document.body.clientWidth >= 600){
	document.getElementById("style-changer").innerHTML = '<span onclick="toggle()"><img id="toggleImg" title="切换日间/夜间模式"></span> '
			+'<span onclick="hideme()">&nbsp;×&nbsp;</span>';
	document.getElementById("style-changer").className="pc";
}else{
	document.getElementById("style-changer").innerHTML = '<span onclick="toggle()"><img id="toggleImg">切换日间/夜间模式</span> '
		+'<span onclick="hideme()">&nbsp;×&nbsp;</span>';
	document.getElementById("style-changer").className="phone";
}
function toggle(){
	if(localStorage.getItem("black")==null || localStorage.getItem("black")!="black"){
		localStorage.setItem("black", "black");
		document.body.className="black";
		document.getElementById("toggleImg").setAttribute("src","/img/day.svg");
	}else{
		localStorage.setItem("black", "white");
		document.body.className="";
		document.getElementById("toggleImg").setAttribute("src","/img/night.svg");
	}
	localStorage.removeItem("hideToggle");
}
function hideme(){
	document.getElementById("style-changer").style.display="none";
	localStorage.setItem("hideToggle", "yes");
}
if(localStorage.getItem("black")=="black"){
	document.body.className="black";
	document.getElementById("toggleImg").setAttribute("src","/img/day.svg");
}else{
	document.getElementById("toggleImg").setAttribute("src","/img/night.svg");
}
if(localStorage.getItem("hideToggle")=="yes"){
	var path = window.location.pathname;
	if(path=="/" || path=="/reader/main.html" || path=="/manager/login.html"){
		
	}else{
		document.getElementById("style-changer").style.display="none";
	}	
}