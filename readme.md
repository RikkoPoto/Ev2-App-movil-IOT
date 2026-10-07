# Trabajo ev2 para Aplicaciones Moviles de IOT

### Adjunto tabla de sql (usuarios.sql) en el proyecto
## Filezilla Archivos, para Raspberry pi(backend)

```
    #RUTA DE RASPBERRY 

    /var/www/html/eva2
   ```

### buscar_usuario.php

```php
   <?php
    include 'cn.php';

    $usuario = $_GET['usuario'];
    $contrasena = $_GET['contrasena'];

    $consulta = "SELECT * FROM usuarios WHERE usuario= '$usuario' AND contrasena= '$contrasena'";
    $resultado = $c->query($consulta);

    $datos = array();

    if ($resultado) {
        while($fila = $resultado->fetch_array()){
            $datos[] = array_map('utf8_encode', $fila);
        }
        $resultado->close();
    }

    echo json_encode($datos);
    ?>
   ```

### cn.php
```php
    <?php
    $c=mysqli_connect("database-2.cjz0nbxwablh.us-east-1.rds.amazonaws.com","pi","a12348765","EVA2");

    ?>
   ```

### ingreso.php
```php
    <?php
    $usuario = $_GET["usuario"];
    $contrasena = $_GET["contrasena"];

    include("cn.php");

    // Se eliminó el campo 'id' de la consulta para que MySQL lo autoincremente solo
    $q = mysqli_query($c,"INSERT INTO usuarios (usuario, contrasena) VALUES ('$usuario', '$contrasena')");

    if($q){
        echo "Registro insertado exitoso";
    }
    else{
        echo "Fallo en el registro: " . mysqli_error($c);
    }
    ?>
   ```