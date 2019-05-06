var map;
var directionsService = new google.maps.DirectionsService();
var info = new google.maps.InfoWindow({maxWidth: 200});

var marker = new google.maps.Marker({
    title: 'Google Belo Horizonte',
//    icon: 'marker.png',
    position: new google.maps.LatLng('-19.92965', '-43.94078')
});

function initialize() {
    var options = {
        zoom: 15,
        center: marker.position,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    console.log($('#map_content')[0])

    map = new google.maps.Map($('#map_content')[0], options);

    marker.setMap(map);

    google.maps.event.addListener(marker, 'click', function () {
        info.setContent('Avenida Bias Fortes, 382 - Lourdes, Belo Horizonte - MG, 30170-010, Brasil');
        info.open(map, marker);
    });
}

$(document).ready(function () {
    initialize();
});

$(document).ready(function () {

    var directionsDisplay = new google.maps.DirectionsRenderer();

    $('#form_route').submit(function () {
        info.close();
        marker.setMap(null);

        var request = {
            origin: "66010-080",
            destination: "88070-740",
            travelMode: google.maps.DirectionsTravelMode.DRIVING
        };

        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                console.log(response);
                directionsDisplay.setDirections(response);
                directionsDisplay.setMap(map);
            }
        });

        return false;
    });
});