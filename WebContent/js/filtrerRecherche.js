function activerDesactiver() {
	
	if(document.getElementById('achat').checked) {
		document.getElementById('encheres_ouvertes').disabled = '';
		document.getElementById('mes_encheres_en_cours').disabled = '';
		document.getElementById('mes_encheres_en_remportees').disabled = '';
		document.getElementById('mes_ventes_en_cours').disabled = 'disabled';
		document.getElementById('ventes_non_débutees').disabled = 'disabled';
		document.getElementById('ventes_terminees').disabled = 'disabled';
		
	} else if(document.getElementById('ventes').checked) {
		document.getElementById('encheres_ouvertes').disabled = 'disabled';
		document.getElementById('mes_encheres_en_cours').disabled = 'disabled';
		document.getElementById('mes_encheres_en_remportees').disabled = 'disabled';
		document.getElementById('mes_ventes_en_cours').disabled = '';
		document.getElementById('ventes_non_débutees').disabled = '';
		document.getElementById('ventes_terminees').disabled = '';
		
	} else {
		document.getElementById('encheres_ouvertes').disabled = 'disabled';
		document.getElementById('mes_encheres_en_cours').disabled = 'disabled';
		document.getElementById('mes_encheres_en_remportees').disabled = 'disabled';
		document.getElementById('mes_ventes_en_cours').disabled = 'disabled';
		document.getElementById('ventes_non_débutees').disabled = 'disabled';
		document.getElementById('ventes_terminees').disabled = 'disabled';
		
	}
}