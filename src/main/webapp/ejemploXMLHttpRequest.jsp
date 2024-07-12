<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <label>Seleccione usuario: </label>
    <select id="usuario">
        <option value="" selected disabled>Seleccione...</option>
    </select>

    <script>
        function loadUsuarios() {
            let req = new XMLHttpRequest();
            let select = document.getElementById("usuario");

            req.onreadystatechange = function() {
                if (req.readyState == XMLHttpRequest.DONE) { // XMLHttpRequest.DONE == 4
                    if (req.status == 200) {
                        let respuesta = JSON.parse(req.responseText); //json
                        for(let key in respuesta){
                            if (respuesta.hasOwnProperty(key)) {
                                //Crear elementos del select
                                let option = document.createElement("option");
                                option.setAttribute("value", respuesta[key].id);
                                option.text = respuesta[key].nombre;

                                select.appendChild(option);
                            }
                        }
                    }
                    else if (req.status == 400) {
                        console.log('There was an error 400');
                    }
                    else {
                        console.log('something else other than 200 was returned');
                    }
                }
            };
            req.open("GET", "ejemploxml", true);
            req.send(null);
        }
    </script>
    <script>
        document.addEventListener("DOMContentLoaded", () =>{
            loadUsuarios();
        });
    </script>
</body>
</html>
