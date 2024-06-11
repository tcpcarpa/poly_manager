let intUrl = "http://localhost:8080/IntegracionBack-1.0-SNAPSHOT/api/v1/";

let page = window.location.pathname.split('/').slice(-1).toString();

// ---------------------------------------------------------------------------------------------------------------------
//LOGOUT

function logout() {
    window.location.href = "index.html";
}

// ---------------------------------------------------------------------------------------------------------------------
// LOGIN()

async function login() {
    try {
        let usuario = $("#username").val();
        let contrasena = $("#password").val();

        const datosAutenticacion = {
            username: usuario,
            password: contrasena
        };

        let nomUsu = usuario.split("@");
        const nom = nomUsu[0].toLocaleUpperCase();
        sessionStorage.setItem("nom", nom);

        const response = await $.ajax({
            url: intUrl + "user/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(datosAutenticacion),
        });

        if (response.success) {
            const listaSites = response.data.request;
            console.log(listaSites);
            sessionStorage.setItem("lista", JSON.stringify(listaSites));
            window.location.href = "sites.html";
        } else {
            alert("Error: " + response.message);
        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + error);
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// LISTSITES

async function createButtons() {
    const buttonsContainer = document.getElementById("buttons-container");
    const nombreUsuarioElement = document.getElementById("nombreUsuario");

    let lista = JSON.parse(sessionStorage.getItem("lista"));
    const nom = sessionStorage.getItem("nom");
    nombreUsuarioElement.textContent = nom;
    if (lista && lista.length > 0) {
        for (const element of lista) {
            let button = document.createElement("button");
            button.textContent = "Site ID: " + element;
            console.log(element);

            button.classList.add("button-black-general")
            button.addEventListener("click", async function () {
                await onButtonClick(element);
            });
            buttonsContainer.appendChild(button);
        }
    } else {
        buttonsContainer.textContent = "No hay elementos en la lista.";
    }
}

async function onButtonClick(siteId) {
    sessionStorage.setItem("siteId", JSON.stringify(siteId));
    await postID();
}

// ---------------------------------------------------------------------------------------------------------------------
// POST ID

async function postID() {
    const siteId = sessionStorage.getItem("siteId");
    console.log(siteId);

    try {
        const response = await $.ajax({
            url: intUrl + "site/siteID",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(siteId),
        });

        if (response.success) {
            window.location.href = "selection.html";
        } else {
            alert("Error: " + response.message);

        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + error);
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// POST FILE ()

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

    return true;
}

// ---------------------------------------------------------------------------------------------------------------------

async function uploadFile() {
    try {
        let fileInput = document.getElementById("file-input");
        let selectedFile = fileInput.files[0];
        sessionStorage.setItem("file", selectedFile.name);


        if (!validateFileExtension(selectedFileType, selectedFile)) {
            return;
        }

        const formData = new FormData();
        formData.append("file", selectedFile);

        const response = await $.ajax({
            type: "POST",
            url: intUrl + "file/csv",
            data: formData,
            processData: false,
            contentType: false,
        });

        window.location.href = "import.html";

        if (response.success) {
            window.location.href = "import.html";
            const noVal = response.data.request;
            sessionStorage.setItem("noVal", noVal);

        } else {
            console.error("Error" + response.message);
        }
    } catch (error) {
        console.error("Error" + error);
    } finally {
        closeFilePopup();
    }
}

// ---------------------------------------------------------------------------------------------------------------------
// POST LIMIT

async function uploadLimit() {
    try {
        let fileInput = document.getElementById("file-input");
        let selectedFile = fileInput.files[0];
        sessionStorage.setItem("limit", selectedFile.name);

        const formData = new FormData();
        formData.append("limit", selectedFile);

        const response = await $.ajax({
            type: "POST",
            url: intUrl + "file/limit",
            data: formData,
            processData: false,
            contentType: false,
        });

        if (response.success) {
            const limit = response.data.request;
            sessionStorage.setItem("limit", limit);

        } else {
            console.error("Error" + response.message);
        }
    } catch (error) {
        console.error("Error" + error);
    } finally {
        closeFilePopup();
    }
}


// ---------------------------------------------------------------------------------------------------------------------
// IMPORT FILE

async function importJson() {
    try {
        const nom = sessionStorage.getItem("nom");
        let contrasena = $("#password").val();

        const datosAutenticacion = {
            username: nom.toLowerCase() + "@polytex.es",
            password: contrasena
        };

        const response = await $.ajax({
            url: intUrl + "client/import",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(datosAutenticacion),
        });

        if (response.success) {
            const val = response.data.request;
            sessionStorage.setItem("val", val);
            window.location.href = "finalbye.html";
        } else {
            console.log("ERROR");
        }
    } catch (error) {
        console.error("Error", error.statusText);
    }
}