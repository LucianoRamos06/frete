var markerArray = [];
var primefacesMap;
var iconBase = '../../resources/imagem/';
var icons = {
    markerTaxistaLivre: {
        icon: iconBase + 'markerTaxistaLivre.png'
    },
    markerTaxistaOcupado: {
        icon: iconBase + 'markerTaxistaOcupado.png'
    },
    markerTaxistaAtendendo: {
        icon: iconBase + 'markerTaxistaAtendendo.png'
    }
};
function limparMap() {
    if (markerArray !== null) {
        for (var i in markerArray) {
            markerArray[i].setMap(null);
        }
    }
}
function addInfoWindow(marker, message, map) {

    var infoWindow = new google.maps.InfoWindow({
        content: message
    });

    google.maps.event.addListener(marker, 'click', function () {
        infoWindow.open(map, marker);
    });
}
function addMarker(taxistaJson) {
    primefacesMap = PF('gmap').getMap();
    for (var i in taxistaJson.taxistas) {
        if (taxistaJson.taxistas[i] !== null) {
            if (taxistaJson.taxistas[i].statusLogin === 'L' ||
                    taxistaJson.taxistas[i].statusLogin === 'I' ||
                    taxistaJson.taxistas[i].statusLogin === 'A') {
                var icone;

                if (taxistaJson.taxistas[i].statusLogin === 'L') {
                    icone = icons['markerTaxistaLivre'].icon;
                }
                if (taxistaJson.taxistas[i].statusLogin === 'I') {
                    icone = icons['markerTaxistaOcupado'].icon;
                }
                if (taxistaJson.taxistas[i].statusLogin === 'A') {
                    icone = icons['markerTaxistaAtendendo'].icon;
                }
                markerArray[i] = new google.maps.Marker(
                        {
                            icon: icone,
                            position: new google.maps.LatLng(taxistaJson.taxistas[i].latitude,
                                    taxistaJson.taxistas[i].longitude),
                            title: taxistaJson.taxistas[i].nome + " " + taxistaJson.taxistas[i].sobrenome,
                            map: primefacesMap
                        });

                var contentString;
                var diaMesAnoHora = formatarData(taxistaJson.taxistas[i].ultimaLocalizacao);
                if (taxistaJson.taxistas[i].area === null) {
                    contentString = taxistaJson.taxistas[i].nome + " " + taxistaJson.taxistas[i].sobrenome + '<br>' +
                            diaMesAnoHora + '<br>';
                    if (taxistaJson.taxistas[i].carro === null) {
                        contentString += '<p>' + "Sem Carro" + '</p>';
                    } else {
                        contentString += taxistaJson.taxistas[i].carro.nome + " - " + taxistaJson.taxistas[i].carro.placa + '<br>';
                    }
                } else {
                    contentString =
                            'Area: ' + taxistaJson.taxistas[i].area.nome + ' | ' +
                            'Posicao: ' + taxistaJson.taxistas[i].posicaoNaFila + '<br>' +
                            diaMesAnoHora + '<br>' +
                            taxistaJson.taxistas[i].nome + " " + taxistaJson.taxistas[i].sobrenome + '<br>';
                    if (taxistaJson.taxistas[i].carro === null) {
                        contentString += '<p>' + "Sem Carro" + '</p>';
                    } else {
                        contentString += taxistaJson.taxistas[i].carro.nome + " - " + taxistaJson.taxistas[i].carro.placa + '<br>';
                    }
                }
                addInfoWindow(markerArray[i], contentString, primefacesMap);
            }
        }
    }
}

function filtrar() {
    limparMap();
    var pessoasSTR = document.getElementById("formTaxistasJson:ihTaxistasJson").value;
    var pessoasJson = JSON.parse(pessoasSTR);
    addMarker(pessoasJson);
    google.maps.event.addDomListener(window, 'load', filtrar);
}

function formatarData(dataEmMilesegundos) {
    var d = new Date(dataEmMilesegundos);
    var dia = d.getDate();
    var mes = d.getMonth() + 1;
    var ano = d.getFullYear();
    var minutos = ' ';
    if (d.getMinutes() < 10) {
        minutos = '0' + d.getMinutes();
    } else {
        minutos = d.getMinutes() + '';
    }
    var segundos = ' ';
    if (d.getSeconds() < 10) {
        segundos = '0' + d.getSeconds();
    } else {
        segundos = d.getSeconds() + '';
    }
    var hora = d.getHours() + ':' + minutos + ':' + segundos;
    var diaMesAnoHora = dia + '/' + mes + '/' + ano + ' ' + hora;
    return diaMesAnoHora;
}
