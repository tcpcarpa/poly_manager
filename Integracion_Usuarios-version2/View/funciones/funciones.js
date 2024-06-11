//SESIONSTORAGE:
// user / nom / sitelist / sitesize / siteID / siteName / site / userdef

let page = window.location.pathname.split('/').slice(-1).toString();

function logout() {
    window.location.href = "index.html";
}

async function createButtons() {
    const buttonsContainer = document.getElementById("buttons-container");
    let listaRecuperada = JSON.parse(sessionStorage.getItem("sitesList"));

    listaRecuperada.forEach(function (site) {
        let button = document.createElement("button");
        button.textContent = site.name
        let siteID = site.id;
        button.classList.add("button-black-general");
        button.addEventListener("click", function () {
            postSite(site);
        });
        buttonsContainer.append(button);
    });
}

function toggleNombreCompleto() {
    let checkBox = document.getElementById("mostrarNombreCompleto");
    let nombreCompletoContainer = document.getElementById("fullnanombreCompleto");
    let nombreApellidoContainer = document.getElementById("nombreApellidoContainer");

    if (checkBox.checked) {
        nombreCompletoContainer.style.display = "block";
        nombreApellidoContainer.style.display = "none";
        document.getElementById("firstName").value = "0";
        document.getElementById("lastName").value = "0";
    } else {
        nombreCompletoContainer.style.display = "none";
        nombreApellidoContainer.style.display = "block";
        document.getElementById("fullName").value = "0";
    }
}

async function config() {
    let fullName = "0";
    let userId = document.getElementById("userId").value || document.getElementById("userId").placeholder || "0";
    let cardId = document.getElementById("cardId").value || document.getElementById("cardId").placeholder || "0";
    let firstName = document.getElementById("firstName").value || document.getElementById("firstName").placeholder || "0";
    let lastName = document.getElementById("lastName").value || document.getElementById("lastName").placeholder || "0";
    if (!firstName && !lastName) {
        fullName = document.getElementById("fullName").value || document.getElementById("fullName").placeholder || "0";
    }
    let departmentName = document.getElementById("departmentName").value || document.getElementById("departmentName").placeholder || "0";
    let title = document.getElementById("title").value || document.getElementById("title").placeholder || "0";
    let effectiveLimitGroup = document.getElementById("effectiveLimitGroup").value || document.getElementById("effectiveLimitGroup").placeholder || "0";
    let dateFormat = document.getElementById("dateFormat").value || document.getElementById("dateFormat").placeholder;
    let futureInactiveData = document.getElementById("futureInactiveData").value || document.getElementById("futureInactiveData").placeholder || "0";
    let card2 = document.getElementById("card2").value || document.getElementById("card2").placeholder || "0";
    let mail = document.getElementById("mail").value || document.getElementById("mail").placeholder || "0";

    let numberFormat;
    if (document.getElementById("hexadecimal").checked) {
        numberFormat = document.getElementById("hexadecimal").value;
    } else {
        numberFormat = document.getElementById("decimal").value;
    }
    let valores = [userId, cardId, firstName, lastName, fullName, departmentName, title, effectiveLimitGroup, futureInactiveData, card2, mail];
    let ceros = valores.filter(valor => valor === "0").length;

    let valoresDiferentesDeCero = new Set(valores.filter(valor => valor !== "0"));

    if (valores.length - ceros !== valoresDiferentesDeCero.size) {
        alert("¡Hay valores duplicados en el formulario!");
    } else {
        const userdef = {
            userId: userId,
            cardId: cardId,
            firstName: firstName,
            lastName: lastName,
            fullName: fullName,
            departmentName: departmentName,
            title: title,
            effectiveLimitGroup: effectiveLimitGroup,
            futureInactiveData: futureInactiveData,
            cardId2: card2,
            email: mail,
            numberFormat: numberFormat,
            dateFormat: dateFormat
        };
        sessionStorage.setItem("userdef", JSON.stringify(userdef));
        window.location.href = "configFinalEnd.html";
    }
}

function validar(input) {
    if (input.value.length > 1 && input.value.charAt(0) === "0") {
        input.value = input.value.slice(1);
    }
    if (parseInt(input.value) < 0 || parseInt(input.value) > 12 || isNaN(parseInt(input.value))) {
        input.value = "0";
    }
}

let selectedFileType = null;

function openFilePopup(fileType) {
    document.getElementById("file-popup").style.display = "block";
    document.getElementById("file-choose").style.display = "none";
    selectedFileType = fileType;
}

function closeFilePopup() {
    document.getElementById("file-popup").style.display = "none";
    document.getElementById("file-popup").style.display = "block";
    selectedFileType = null;
}


let rutaCarpeta1 = "";
let rutaCarpeta2 = "";

let siteAll = sessionStorage.getItem("site");
let arrayRespuesta = siteAll.split(',');

let pathLimitGrup, pathUsuarios;
arrayRespuesta.forEach(item => {
    if (item.includes('path_limitGrup')) {
        pathLimitGrup = item.split('=')[1].trim();
    }
    if (item.includes('path_usuarios')) {
        pathUsuarios = item.split('=')[1].trim();
    }
});

function abrirSelectorDeCarpeta1(inputId1) {
    document.getElementById(inputId1).click();
}
function abrirSelectorDeCarpeta2(inputId2) {
    document.getElementById(inputId2).click();
}

document.getElementById('fileInput1').addEventListener('change', function (event) {
    let files1 = event.target.files;
    if (files1.length > 0) {
        let folderPath1;
        if (files1[0].webkitRelativePath) {
            folderPath1 = files1[0].webkitRelativePath.split(files1[0].name)[0];
        } else {
            document.getElementById('fileInput1').value = "No hay Ruta seleccionada" ;
            return;
        }
        rutaCarpeta1 = folderPath1;
        document.getElementById('rutaCarpeta1Label').textContent = "Ruta de la carpeta seleccionada: " + rutaCarpeta1.toUpperCase();
        sessionStorage.setItem("pathU", rutaCarpeta1);
    } else {
        document.getElementById('rutaCarpeta1Label').textContent = "No hay carpeta seleccionada";
    }
});

if (pathUsuarios) {
    document.getElementById('rutaCarpeta1Label').textContent = "Ruta de la carpeta seleccionada: " + pathUsuarios.toUpperCase();
} else {
    document.getElementById('rutaCarpeta1Label').textContent = "No hay carpeta seleccionada";
}

document.getElementById('fileInput2').addEventListener('change', function (event) {
    let files2 = event.target.files;
    if (files2.length > 0) {
        let folderPath2;
        if (files2[0].webkitRelativePath) {
            folderPath2 =files2[0].webkitRelativePath.split(files2[0].name)[0];
        } else {
            document.getElementById('fileInput2').value = "No hay Ruta seleccionada";
            return;
        }
        rutaCarpeta2 = folderPath2;
        document.getElementById('rutaCarpeta2Label').textContent = "Ruta de la carpeta seleccionada: " + rutaCarpeta2.toUpperCase();
        sessionStorage.setItem("pathL", rutaCarpeta2);
    } else {
        document.getElementById('rutaCarpeta2Label').textContent = "No hay carpeta seleccionada";
    }
});

if (pathLimitGrup) {
    document.getElementById('rutaCarpeta2Label').textContent = "Ruta de la carpeta seleccionada: " + pathLimitGrup.toUpperCase();
} else {
    document.getElementById('rutaCarpeta2Label').textContent = "No hay carpeta seleccionada";
}

// ---------------------------------------------------------------------------------------------------------------------

function showLoadingPopup() {
    let loadingPopup = document.createElement("div");
    loadingPopup.id = "loading-popup";
    loadingPopup.innerHTML = `
        <div class="loading-spinner"></div>
        <p class="text-bold" style="padding-top: 20px">Realizando Transacción...</p>
    `;
    document.body.appendChild(loadingPopup);

    loadingPopup.style.position = "fixed";
    loadingPopup.style.top = "50%";
    loadingPopup.style.left = "50%";
    loadingPopup.style.transform = "translate(-50%, -50%)";
    loadingPopup.style.background = "#0f5691";
    loadingPopup.style.padding = "30px";
    loadingPopup.style.borderRadius = "5px";
    loadingPopup.style.boxShadow = "0 0 20px rgba(0, 0, 0, 0.5)";
    loadingPopup.style.textAlign = "center";

    let spinner = loadingPopup.querySelector(".loading-spinner");
    spinner.style.border = "8px solid #f3f3f3";
    spinner.style.borderTop = "8px solid #FF6602";
    spinner.style.borderRadius = "50%";
    spinner.style.width = "50px";
    spinner.style.height = "50px";
    spinner.style.animation = "spin 1s linear infinite";
    spinner.style.margin = "0 auto";

    let styles = document.createElement("style");
    styles.innerHTML = `
        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    `;
    document.head.appendChild(styles);
}

function hideLoadingPopup() {
    let loadingPopup = document.getElementById("loading-popup");

    if (loadingPopup) {
        loadingPopup.parentNode.removeChild(loadingPopup);
    }
}

// ---------------------------------------------------------------------------------------------------------------------
/*

function getUrlParameters() {
    return window.location.search;
}

function getUrlParameter(sParam) {
    let sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
}
    function printTime() {
        $('#localTimeNow').html(getLocalTimeNow());
    }

    function getLocalTimeNow() {
        let now = new Date();
        let hours = now.getHours();
        let minutes = now.getMinutes();
        let seconds = now.getSeconds();
        let ampm = hours >= 12 ? 'PM' : 'AM';

        hours = hours % 12;
        hours = hours ? hours : 12;
        minutes = minutes < 10 ? '0' + minutes : minutes;
        seconds = seconds < 10 ? '0' + seconds : seconds;
        let strHora = hours + ':' + minutes + ':' + seconds + ' ' + ampm;

        let day = now.getDate();
        let month = now.getMonth() + 1;
        let year = now.getFullYear();
        let prefixDay;

        switch (day) {
            case 1:
            case 21:
            case 31:
                prefixDay = 'st';
                break;
            case 2:
            case 22:
                prefixDay = 'nd';
                break;
            case 3:
            case 23:
                prefixDay = 'rd';
                break;
            default:
                prefixDay = 'th';
                break;
        }

        return strHora + ', ' + day + prefixDay + ' of ' + month + ' ' + year;
    }



    function validateFileExtension(selectedFileType, selectedFile) {
    let allowedExtensions;

    if (selectedFileType === "CSV") {
        allowedExtensions = [".csv"];
    } else if (selectedFileType === "SQL") {
        allowedExtensions = [".sql"];
    }
    let fileExtension = selectedFile.name.split(".").pop().toLowerCase();
    if (allowedExtensions.indexOf("." + fileExtension) === -1) {
        alert("Selecciona un archivo correcto (" + allowedExtensions.join(", ") + ").");
        return false;
    }
}

 */