
/* global tempo */

var segundos = new Number();
var segundos = 11;
function contador() {

    if ((segundos - 1) >= 0) {
        segundos = segundos - 1;
        tempo.innerText = segundos;
        setTimeout('contador();', 1000);
    }
}