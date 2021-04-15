

document.getElementById("PSoignant").addEventListener("click",ajouterOptionsPS)
function ajouterOptionsPS(){
    let texteHTML = '<option class="options" value="médecin urgentiste">Médecin urgentiste</option>' +
    '<option class="options" value="anesthésiste">Anesthésiste</option>' +
    '<option class="options" value="cardiologue">Cardiologue</option>' +
    '<option class="options" value="chirurgien">Chirurgien</option>' +
    '<option class="options" value="infirmier">Infirmier</option>' +
    '<option class="options" value="neurologue">Neurologue</option>';
    document.getElementById("profession").innerHTML=texteHTML;
}

document.getElementById("PTechnique").addEventListener("click",ajouterOptionsPT)
function ajouterOptionsPT(){
    let texteHTML = '<option class="options" value="agent d\'entretien">Agent d\'entretien</option>' +
    '<option class="options" value="ingénieur hospitalier">Ingénieur biomédical</option>'+
    '<option class="options" value="Chef de projet">Chef de projet</option>' +
    '<option class="options" value="Responsable Qualité">Responsable qualité</option>' +
    '<option class="options" value="Technicien supérieur">Technicien supérieur</option>';
    document.getElementById("profession").innerHTML=texteHTML;
}

document.getElementById("PAdministratif").addEventListener("click",ajouterOptionsPA)
function ajouterOptionsPA(){
    let texteHTML = '<option class="options" value="Chargé d\'accueil">Chargé d\'accueil</option>' +
    '<option class="class" value="directeur d\'hôpital">Directeur d\'hôpital</option>' +
    '<option class="class" value="secrétaire médical">Secrétaire médical</option>';
    document.getElementById("profession").innerHTML=texteHTML;
}

let options = document.getElementsByClassName("options");
for(let o of options){
    o.addEventListener("change",changerCouleurs);
    console.log(o);
}

function changerCouleurs(event){
    console.log(event.target);
    event.target.style.background = "black";
    event.target.style.color="blue";
}


