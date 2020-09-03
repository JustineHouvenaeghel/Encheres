function passverif() {
	var msg;
	var pass = $("#mot_de_passe").val();
	var vpass = $("#mot_de_passe_2").val();
	console.log(pass, vpass);
	if(pass != vpass) {
		msg = "<p style='color:red'>Erreur : les mots de passe ne sont pas identiques</p>";
		document.getElementById("msg").innerHTML = msg;
		document.getElementById("valider").disabled = true;
	} else {
		document.getElementById("valider").disabled = false;
	}
}