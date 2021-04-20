var modal = document.getElementById("fenetreModal");
var fermer = document.getElementById("close");
var isRdc = true;
var plan = document.getElementById('plan').innerHTML;
var etage = '<div id="etage">\n' +
    '    <img th:src="@{/images/isisetage.png}" src="images/isisetage.png" usemap="#image-map-etage" class="carte" alt="plan etage isis">\n' +
    '\n' +
    '    <map name="image-map-etage">\n' +
    '        <area target="" alt="salleB101" name="salleB101" href="" coords="9672,5926,9680,6494,9956,6474,10146,6460,10299,6426,10181,5864" shape="poly">\n' +
    '        <area target="" alt="salleB102" name="salleB102" href="" coords="10130,5274,9901,5324,9824,5341,9672,5333,9672,5791,9969,5785,10141,5758,10274,5732" shape="poly">\n' +
    '        <area target="" alt="toilettesHommeHaut" name="toilettesHommeHaut" href="" coords="10130,5274,10299,5223,10418,5570,10342,5596,10375,5689,10265,5714" shape="poly">\n' +
    '        <area target="" alt="toilettesFemmeHaut" name="toilettesFemmeHaut" href="" coords="10291,5231,10477,5163,10613,5604,10528,5664,10494,5562,10401,5596" shape="poly">\n' +
    '        <area target="" alt="salleB105" name="salleB105" href="" coords="10477,5163,11054,5002,11096,5477,10621,5621" shape="poly">\n' +
    '        <area target="" alt="salleB106" name="salleB106" href="" coords="11054,5011,11108,5480,11724,5358,11673,4901" shape="poly">\n' +
    '        <area target="" alt="salleB109" name="salleB109" href="" coords="11673,4892,12063,4841,12114,5299,11715,5358" shape="poly">\n' +
    '        <area target="" alt="salleB103" name="salleB103" href="" coords="10172,5867,10257,5850,10520,5782,10604,5740,10830,6287,10313,6433" shape="poly">\n' +
    '        <area target="" alt="salleB104" name="salleB104" href="" coords="10596,5757,10706,5714,10740,5774,10833,5799,10918,5782,10995,5748,11037,5672,11042,5638,11135,5612,11164,6172,10833,6282" shape="poly">\n' +
    '        <area target="" alt="salleB107" name="salleB107" href="" coords="11130,5604,11724,5486,11817,6053,11175,6194" shape="poly">\n' +
    '        <area target="" alt="salleB108" name="salleB108" href="" coords="11732,5486,11944,5452,11995,5511,12055,5553,12139,5570,12224,5528,12368,5994,11826,6053" shape="poly">\n' +
    '        <area target="" alt="salleB110" name="salleB110" href="" coords="12224,5528,12301,5418,12513,5400,12742,5986,12351,5994" shape="poly">\n' +
    '        <area target="" alt="connectedHealthLab" name="connectedHealthLab" href="" coords="12292,4841,13013,4807,13539,6045,13174,5994,12950,5996,12752,5982" shape="poly">\n' +
    '    </map>\n' +
    '</div>';
activerMap();

fermer.onclick = function() {
    modal.style.display = "none";
}
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


document.getElementById('btnEtage').onclick = function(){
    if (isRdc== true){
        document.getElementById("plan").innerHTML=etage;
        document.getElementById("btnEtage").value="Aller au rez-de-chaussée";
        isRdc= false
        activerMap()
    }else {
        document.getElementById("plan").innerHTML=plan;
        document.getElementById("btnEtage").value="Aller à l'étage";
        isRdc= true;
        activerMap()
    }

}


function activerMap() {

    $('img[usemap]').mapster({
        fillColor: "ffffff",
        stroke: true,
        strokeColor: '3320FF',
        selected: true,
        mapKey: 'name',
        listKey: 'name',
        onClick: function (e) {
            modal.style.display = "block";
            $('#txtModal').html(xref[e.key]);
            image.mapster('set_options', {
                areas: [{
                    key: "dip",
                    toolTip: newToolTip
                }]
            });
        },
        showToolTip: true,
        toolTipClose: ["tooltip-click", "area-click"],
        areas: [
            {
                key: "grandAuditorium",
                fillColor: 'fcba03',
            },{
                key: "petitAuditorium",
                fillColor: 'ff0000',
            },
            {
                key: "salleB007",
                fillColor: '2e856e',
            },
            {
                key: "salleB009",
                fillColor: '2e856e',
            },{
                key: "salleB011",
                fillColor: '2e856e',
            },{
                key: "salleB013",
            },{
                key: "salleB014",
            },{
                key: "salleB015",
                fillColor: '2e856e',
            },{
                key: "salleB016",
            },{
                key: "salleB003",
            },{
                key: "localServeur",
            },{
                key: "salleB005",
            },{
                key: "toilettesFemme",
                fillColor: '2e856e',
            },{
                key: "toilettesHomme",
                fillColor: '2e856e',
            }, {
                key: "salleB008",
                fillColor: '2e856e',
            },
            {
                key: "salleB010",
                fillColor: '2e856e',
            },
            {
                key: "salleB012",
                fillColor: '2e856e',
            },
            {
                key: "salleB018",
                fillColor: '2e856e',
            },
            {
                key: "salleB019",
                fillColor: '2e856e',
            },
            {
                key: "salleB017",
                fillColor: '2e856e',
            },
            {
                key: "foyer",
                fillColor: 'fcba03',
            },
            {
                key: "cafeteria",
                fillColor: 'fcba03',
            },
            {
                key: "accueilA003",
                fillColor: 'fcba03',
            },
            {
                key: "bureauA005",
                fillColor: '2e856e',
            },{
                key: "bureauA009",
                fillColor: '2e856e',
            },{
                key: "bureauA011",
                fillColor: '2e856e',
            },{
                key: "bureauA013",
                fillColor: '2e856e',
            },{
                key: "bureauA015",
                fillColor: '2e856e',
            },{
                key: "bureauA016",
                fillColor: '2e856e',
            },{
                key: "bureauA017",
                fillColor: '2e856e',
            },{
                key: "bureauA001",
                fillColor: '2e856e',
            },{
                key: "bureauA002",
                fillColor: 'fcba03',
            },{
                key: "bureauA004",
                fillColor: 'fcba03',
            },{
                key: "bureauA006",
                fillColor: '2e856e',
            },{
                key: "bureauA007",
                fillColor: '2e856e',
            },{
                key: "bureauA008",
                fillColor: '2e856e',
            },{
                key: "bureauA010",
                fillColor: '2e856e',
            },{
                key: "bureauA012",
                fillColor: '2e856e',
            },{
                key: "bureauA014",
                fillColor: '2e856e',
            },{
                key: "salleB101",
                fillColor: 'ff0000',
            },{
                key: "salleB102",
                fillColor: 'fcba03',
            },{
                key: "toilettesHommeHaut",
                fillColor: '2e856e',
            },{
                key: "toilettesFemmeHaut",
                fillColor: '2e856e',
            },{
                key: "salleB105",
                fillColor: '2e856e',
            },{
                key: "salleB106",
                fillColor: '2e856e',
            },{
                key: "salleB109",
                fillColor: '2e856e',
            },{
                key: "salleB103",
                fillColor: 'fcba03',
            },{
                key: "salleB104",
                fillColor: 'fcba03',
            },{
                key: "salleB107",
                fillColor: '2e856e',
            },{
                key: "salleB108",
                fillColor: '2e856e',
            },{
                key: "salleB110",
                fillColor: '2e856e',
            },{
                key: "connectedHealthLab",
                fillColor: '2e856e',
            },
        ]

    });

    $('#mybtn').click(function(){
        $('img[usemap]').mapster({
            fillColor: '4287f5',
            selected: true
        });
    });
    var xref={
        grandAuditorium: "Grand Auditorium" + "</br>Places assises : 250 sièges " + "</br>Capacité maximale autorisée : 100 personnes",
        petitAuditorium: "Petit Auditorium"  + "</br>Places assises : 50 sièges " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB007: "Salle B007" + "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB009: "Salle B009" + "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB011: "Salle B011" + "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB013: "Local technique" + "<br/>Accès interdit sauf peronnel autorisé",
        salleB014: "Local d'entretien" + "<br/>Accès interdit sauf peronnel autorisé",
        salleB015: "Douches" + "</br>Nombre de cabines de douche : 2 douches " + "</br>Capacité maximale autorisée : 3 personnes",
        salleB016: "Local des Poubelles" + "<br/>Accès interdit sauf peronnel autorisé",
        salleB017: "Salle B017" +"</br> capacité maximale autorisée : 3 personnes",
        salleB019: "Salle B019"+ "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes"+"</br>Capacité maximale de la salle de sport du BDE Osiris : 6 personnes",
        salleB003: "Local" + "<br/>Accès interdit sauf peronnel autorisé",
        localServeur: "Local des serveurs" + "<br/>Accès interdit sauf peronnel autorisé",
        salleB005: "Local technique" + "<br/>Accès interdit sauf peronnel autorisé",
        toilettesHomme: "Toilettes Homme" + "</br>Capacité maximale autorisée : 4 personnes",
        toilettesFemme: "Toilettes Femme" + "</br>Capacité maximale autorisée : 4 personnes",
        salleB008: "Bureau de la Junior Entreprise" + "</br>Places assises : 12 chaises " + "</br>Capacité maximale autorisée : 12 personnes",
        salleB010: "Salle B010" + "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB012: "Salle B012" + "</br>Places assises : 25 chaises " + "</br>Capacité maximale autorisée : 25 personnes",
        salleB018: "Bureau de l'association ISF : Ingénieur sans frontière" + "</br>Capacité maximale autorisée : 3 personnes",
        foyer: "Foyer avec le local du BDE (Bureau des étudiants). Comprend un babyfoot, 3 canapés, 1 pouf, 1 télé." + "</br>Capacité maximale autorisée : 20 personnes",
        cafeteria: "Cafétéria avec un micro-onde, un barbecue, des tables et des chaises pour se restaurer." + "</br>Capacité maximale autorisée : 20 personnes",
        accueilA003: "Accueil" + "</br>Capacité maximale autorisée : 2 personnes",
        bureauA005: "Bureau A005 : salle de réunion" + "</br>Capacité maximale autorisée : 10 personnes",
        bureauA009: "Bureau A009" + "</br>Capacité maximale autorisée : 2 personnes",
        bureauA011: "Bureau M. Singer" + "</br>Capacité maximale autorisée : 2 personnes",
        bureauA013: "Bureau A013" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA015: "Bureau A015" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA016: "Bureau A016 : salle de réunion" + "</br>Capacité maximale autorisée : 15 personnes",
        bureauA001: "Bureau A001" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA002: "Bureau A002 : bureau de Mme Delhon" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA004: "Bureau A004 : bureau de Mme Savary" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA006: "Bureau A006" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA007: "Bureau A007" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA008: "Bureau A008" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA010: "Bureau A010" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA012: "Bureau A012" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA014: "Bureau A014" + "</br>Capacité maximale autorisée : 1 personne",
        bureauA017: "Bureau A017 : salle de réunion" + "</br>Capacité maximale autorisée : 10 personnes",
        salleB101: "Salle B101" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB102: "Salle B102" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        toilettesHommeHaut: "Toilettes homme" + "</br>Capacité maximale autorisée : 4 personnes",
        toilettesFemmeHaut: "Toilettes Femme" + "</br>Capacité maximale autorisée : 4 personnes",
        salleB105: "Salle B105" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB106: "Salle B106" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB109: "Salle B109" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB103: "Salle B103" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB104: "Salle B104" + "</br>Places assises : 20 chaises " + "</br>Capacité maximale autorisée : 10 personnes",
        salleB107: "Salle B107" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB108: "Salle B108" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        salleB110: "Salle B110" + "</br>Places assises : 15 chaises " + "</br>Capacité maximale autorisée : 15 personnes",
        connectedHealthLab: "CHL : Connected Health Lab contient une chambre d'hôpital, un bureau de médecin généraliste, une chambre standard, une pharmacie" + "</br>Capacité maximale autorisée : 5 personnes",
    }
}
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

if (urlParams.has('salle') === true){ 
    console.log(urlParams.get('salle'))
    // select all New England states
    $('img').mapster('set',true,urlParams.get('salle'));
}
