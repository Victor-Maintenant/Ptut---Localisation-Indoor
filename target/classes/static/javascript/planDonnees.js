const queryString = window.location.search; 
const urlParams = new URLSearchParams(queryString);

$('img[usemap]').mapster({
    mapKey : 'alt',
    fillColor: 'fcba03',
    stroke: true,
    selected: false
});

$('#mybtn').click(function(){
    $('img[usemap]').mapster({
        fillColor: '4287f5',
        selected: true
    });
});

if (urlParams.has('salle') === true){ 
    console.log(urlParams.get('salle'))
    // select all New England states
    $('img').mapster('set',true,urlParams.get('salle'));
}

console.log(personnesSalle);
console.log(salles);
console.log(maxPer);
for(let i = 0; i < salles.length; i++){
    if(personnesSalle[i] <= maxPer[i]*0.6){
        $('img').mapster('set',true,salles[i],{fillColor:'00ff00'});
    }
    else if(personnesSalle[i] <= maxPer[i]*0.8){
        $('img').mapster('set',true,salles[i],{fillColor:'ffff00'});
    }
    else{
        $('img').mapster('set',true,salles[i],{fillColor:'ff0000'});
    }
}
