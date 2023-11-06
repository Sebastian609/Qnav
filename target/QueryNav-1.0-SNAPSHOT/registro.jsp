<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">
        <title>Registro</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
        <script src="js/Registro.js"></script>
        
    </head>

    <body>

  

        <header class="hero"> 
            
            <nav class="nav__hero"> 
                <div class="container nav__container"> 
                    <div class="logo">
                        <h2 class="logo__name">QueryNav<span class="point">.</span></h2>
                    </div>
                </div>
            </nav>

        </header>

        <div class="services">

            <div class="card-form">

                <form >

                    <h2 class="card__title">Registro</h2>
                    <input type="text" name="nombre" id="nombre" placeholder="Nombres" required class="dato">
                    <div class="apellidos-registro"> 
                        <input type="text" name="apellidoPaterno" id="apellidoPaterno" placeholder="Apellido paterno" required class="dato-alter">
                        <input type="text" name="apellidoMaterno" id="apellidoMaterno" placeholder="Apellido" required class="dato-alter">
    
                    </div>
                    
                    
                    <input type="text" name="dni" id="dni" placeholder="DNI" class="dato" required>
                    <input type="email" name="correo" id="correo" placeholder="Correo" class="dato" required>
                    <input type="password" name="contrasena" id="contrasena" placeholder="Contraseña" required class="dato">
                    <input type="password" name="contrasena" id="contrasena" placeholder="Confirmar contraseña" required class="dato">
                    <input type="submit" value="Iniciar Sesión" class="button">
    
                </form>
            </div>

            


        </div>
    </body>

    </html>