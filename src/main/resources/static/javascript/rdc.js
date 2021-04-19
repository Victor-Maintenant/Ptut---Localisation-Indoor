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

const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

if (urlParams.has('salle') == true){ 
    // select all New England states
    $('img').mapster('set',true,urlParams.get('salle'), {fillColor: '0000ff'});
}
