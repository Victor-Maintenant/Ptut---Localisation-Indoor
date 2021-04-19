$('img[usemap]').mapster({
    fillColor: 'fcba03',
    stroke: true,
    selected: true
});

$('#mybtn').click(function(){
    $('img[usemap]').mapster({
        fillColor: '4287f5',
        selected: true
    });
});

window.addEventListener("load", afficheNbPersonnes);
function afficheNbPersonnes(){
    let data = personnesParSalle;
    console.log(data);
}