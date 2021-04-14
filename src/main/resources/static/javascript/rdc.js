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