
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       
        <link rel="stylesheet" href="style/normalize.css">
        <link rel="stylesheet" href="style/style.css">

        <title>JSP Page</title>
    </head>
    <!--<body>-->
        <header class="hero"> <!--hay que delimitar cada parte de la página-->
            <!--vamos a dividir "hero" diferentes contenedores-->
            <nav class="nav__hero"> <!--barra de navgacion en "hero"-->
                <div class="container nav__container"> <!--contenedor dentro del navegador-->
                    <div class="logo">
                        <h2 class="logo__name">QueryNav<span class="point">.</span></h2>
                    </div>
                    <div class="links">
                        <a class="btn btn-success btn-lg" href="Controlador?accion=listar">Listar Persona</a>
            <a class="btn btn-success btn-lg" href="vistas/pasajero.jsp">Pasajero</a>
                        <a href="#" class="link--active">Soy Parte del Equipo</a>
                        
                    </div>
                </div>
            </nav>
    
        </header>
        <section class="services">
            <div class="container-login">
            
            

                <div class="login">
                    <form action="Verificador" class="login-form">
                        <h2 class="card__title">Iniciar Sesión</h2>
                        <label for="tipo_usuario">Selecciona tu tipo de usuario:</label>
                        <select name="tipo_usuario" id="tipo_usuario" class="dato">
                            <option value="pasajero">Pasajero</option>
                            <option value="transportista">Transportista</option>
                            <option value="empresa_transportista">Empresa de Transporte</option>
                            <option value="administrador">Administrador</option>
                        </select>
            
                        <input type="email" name="correo" id="correo" placeholder="Correo" required class="dato">
            
                        <input type="password" name="contrasena" id="contrasena" placeholder="Contraseña" required class="dato">
            
                        <input type="submit" value="Iniciar Sesión" class="button" >
                    </form>
                </div>
            </body>
            </html>
           
            
            
            
            
    
            </div>
        </section>
    </body>
</html>
