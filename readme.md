### Trabajo ev2 para Aplicaciones Moviles de IOT

## En Filezilla los codigos integrados para compatibilizar con el raspberry pi(backend)

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