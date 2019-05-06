function chamar(int, input, form) {
    int = int.replace(",", "");
    int = int.replace(".", "");
    if (int.length === 4) {
        if (int.substring(0, 1) === "0") {
            int = int.substring(1);
        }
    }

    var c = "[name='" + form + ":" + input + "']"

    var valor = formatReal(int);
    document.querySelector(c).value = valor;
}

function formatReal(int)
{
    var tmp = int + '';
    var neg = false;
    if (tmp.indexOf("-") === 0)
    {
        neg = true;
        tmp = tmp.replace("-", "");
    }

    if (tmp.length === 1)
        tmp = "0" + tmp;

    tmp = tmp.replace(/([0-9]{2})$/g, ",$1");
    if (tmp.length > 6)
        tmp = tmp.replace(/([0-9]{3}),([0-9]{2}$)/g, ".$1,$2");

    if (tmp.length > 9)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2,$3");

    if (tmp.length > 12)
        tmp = tmp.replace(/([0-9]{3}).([0-9]{3}).([0-9]{3}),([0-9]{2}$)/g, ".$1.$2.$3,$4");

    if (tmp.indexOf(".") === 0)
        tmp = tmp.replace(".", "");
    if (tmp.indexOf(",") === 0)
        tmp = tmp.replace(",", "0,");
    return (neg ? '-' + tmp : tmp);
}