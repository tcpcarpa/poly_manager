let intUrl = "http://localhost:8080/IntegracionBack-1.0-SNAPSHOT/api/v1/";

async function login() {
    try {
        let usuario = $("#username").val();
        let contrasena = $("#password").val();

        const datosAutenticacion = {
            username: usuario,
            password: contrasena
        };
        sessionStorage.clear();
        sessionStorage.setItem("user", JSON.stringify(datosAutenticacion));
        let nomUsu = usuario.split("@");
        let nom = nomUsu[0].toLocaleUpperCase();
        sessionStorage.setItem("nom", nom);

        const response = await $.ajax({
            url: intUrl + "user/login",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(datosAutenticacion),
        });
        if (response.success) {
            let listaSites = response.data.request;
            sessionStorage.setItem("sitesList", JSON.stringify(listaSites));
            sessionStorage.setItem("sitesSize", JSON.stringify(listaSites.length));
            window.location.href = "sites.html";
        } else {
            alert("Error: " + response.message);
        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + error);
    }
}

async function postSite(site) {
    try {
        const response = await $.ajax({
            url: intUrl + "site/site",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(site)
        });
        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "siteInfo.html";
        } else {
            alert("Error: " + response.message);
        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + error);
    }
}

async function postUserdef() {
    let siteID = sessionStorage.getItem("siteID");
    try {
        const response = await $.ajax({
            url: intUrl + "site/userDef",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(siteID)
        });
        if (response.success) {
            let respuserdef = response.data.request
            sessionStorage.setItem("userdef", JSON.stringify(respuserdef));
            window.location.href = "config.html";
        } else {
            alert("Error: " + response.message);
        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + error);
    }
}

async function sendConfig() {
    let siteID = sessionStorage.getItem("siteID");
    let userDefinition = JSON.parse(sessionStorage.getItem("userdef"));
    console.log(userDefinition)
    try {
        const response = await $.ajax({
            url: intUrl + "config/sendconfig/" + siteID,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(userDefinition),
        });

        if (response.success) {
            let respconfig = response.data.request;
            sessionStorage.setItem("userdef", JSON.stringify(respconfig));
            window.location.href = "dos.html";
        } else {
            alert("Error: " + response.message);
        }
    } catch (error) {
        alert("Error de conexión con el servidor: " + JSON.stringify(error));
    }
}

async function uploadFile() {
    try {
        let fileInput = document.getElementById("file-input");
        let siteID = JSON.parse(sessionStorage.getItem("siteID"));

        let selectedFile = fileInput.files[0];
        sessionStorage.setItem("file", selectedFile.name);

        const formData = new FormData();
        formData.append("file", selectedFile);
        showLoadingPopup();
        const response = await $.ajax({
            type: "POST",
            url: intUrl + "file/csv/" + siteID,
            data: formData,
            processData: false,
            contentType: false,
        });
        hideLoadingPopup();

        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "gruplimit.html"
        } else {
            alert("Error: " + (response ? response.message : "Unknown error"));
        }
    } catch (error) {
        hideLoadingPopup();
        alert("Error de conexión con el servidor: " + error);
    }
}

async function uploadLimit() {
    try {
        let fileInput = document.getElementById("file-input");
        let siteID = JSON.parse(sessionStorage.getItem("siteID"));

        let selectedFile = fileInput.files[0];
        sessionStorage.setItem("limit", selectedFile.name);

        const formData = new FormData();
        formData.append("limit", selectedFile);
        showLoadingPopup();
        const response = await $.ajax({
            type: "POST",
            url: intUrl + "limit/limit/" + siteID,
            data: formData,
            processData: false,
            contentType: false,
        });
        hideLoadingPopup();
        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "preImport.html"
        } else {
            alert("Error: " + (response ? response.message : "Unknown error"));
        }
    } catch (error) {
        hideLoadingPopup();
        alert("Error de conexión con el servidor: " + error);
    }
}

async function guardarCarpeta() {
    let siteID = sessionStorage.getItem("siteID");
    let pathU = sessionStorage.getItem("pathU");
    let pathL = sessionStorage.getItem("pathL");
    if (pathU !== "" && pathL !== "") {
        let paths = {
            pathU: pathU,
            pathL: pathL
        }
        try {
            showLoadingPopup();
            const response = await $.ajax({
                url: intUrl + "path/path/" + siteID,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(paths)
            });
            hideLoadingPopup();
            if (response.success) {
                let site = response.data.request;
                sessionStorage.setItem("site", JSON.stringify(site));
                window.location.href = "updatacion.html";
            } else {
                alert("Error: " + (response ? response.message : "Unknown error"));
            }
        } catch (error) {
            hideLoadingPopup();
            alert("Error de conexión con el servidor: " + error);
        }
    }
}
async function postUpdate(data) {
    let siteID = sessionStorage.getItem("siteID");
    try {
        showLoadingPopup();
        const response = await $.ajax({
            url: intUrl + "auto/tiempo/" + siteID,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(data)
        });
        hideLoadingPopup();
        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "finalbye.html";
        } else {
            alert("Error: " + (response ? response.message : "Unknown error"));
        }
    } catch (error) {
        hideLoadingPopup();
        alert("Error de conexión con el servidor: " + error);
    }
}

async function preImport() {
    const siteID = sessionStorage.getItem("siteID");
    try {
        showLoadingPopup();
        const response = await $.ajax({
            url: intUrl + "client/preImport",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(siteID)
        });
        hideLoadingPopup();
        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "import.html";
        } else {
            console.log("ERROR");
        }
    } catch (error) {
        hideLoadingPopup();
        console.error("Error", error.statusText);
    }
}

async function importJson() {
    try {
        const user = sessionStorage.getItem("user");
        const siteID = sessionStorage.getItem("siteID");
        showLoadingPopup();
        const response = await $.ajax({
            url: intUrl + "import/import/" + siteID,
            type: "POST",
            contentType: "application/json",
            data: user
        });
        hideLoadingPopup();
        if (response.success) {
            let site = response.data.request;
            sessionStorage.setItem("site", JSON.stringify(site));
            window.location.href = "finalbye.html";
        } else {
            console.log("ERROR");
        }
    } catch (error) {
        hideLoadingPopup();
        console.error("Error", error.statusText);
    }
}





