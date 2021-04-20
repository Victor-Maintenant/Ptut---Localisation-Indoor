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

if (urlParams.has('salle') === true){ 
    console.log(urlParams.get('salle'))
    // select all New England states
    $('img').mapster('set',true,urlParams.get('salle'));
}
