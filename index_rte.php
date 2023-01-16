<?php
// Établir une connexion à la base de données
$host = "127.0.0.1";
$user = "Admin";
$password = "ii594AqcthJj6DL7";
$dbname = "eleko";
$conn = mysqli_connect($host, $user, $password, $dbname);

// Vérifier la connexion
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

// Requête pour sélectionner toutes les données de la table "datas"
$sql = "SELECT DISTINCT * FROM datas_rte";
$result = mysqli_query($conn, $sql);

// Initialiser un tableau pour stocker les données
$data = array();
$values = array();

$num_rows = mysqli_num_rows($result);

$j =0;
// Si la requête retourne des résultats
if($num_rows > 0) {
    // Parcourir chaque ligne de résultat
    while($row = mysqli_fetch_assoc($result)) {
        // Ajouter un signal à la liste des données
        for ($i = 0; $i < 24; $i++) {
            $values[] = array('pas' => $i, 'hvalue' => ($row['hours' . strval($i) . '_data']));
        }
        $data[$j] = array( 'signals' => array (array(
            'GenerationFichier' => $row['date_gen'],
            'jour' => $row['jour'],
            'dvalue' => $row['dvalue'],
            'message' => $row['message_data'],
            'values' => $values
        )));
        $values = array();
        $j++;
    }
}


// Encoder les données en JSON
$json = json_encode($data);

// Afficher les données en format JSON
echo $json;

// Fermer la connexion
mysqli_close($conn);
?>