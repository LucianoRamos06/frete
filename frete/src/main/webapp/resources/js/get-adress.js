jQuery(function($){
    correios.init( 'a56BPnJPA7cpYTm41JwaO8chKunaCl16', 'CcTfbSLKNwARW7vUn8VuOczurZ3hD01L0VFNHq70cPYUdzYp' );
    $('#cep').correios( '#endereco', '#bairro', '#cidade', '#uf', '#loading' );
});

//Documentação: http://webmania.me/1XHNtS2
//https://github.com/webmaniabr/jQuery-CEP-Correios