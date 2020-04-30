<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="/resources/css/page_configuration.css">

    <title>QT Clustering Tool</title>

  </head>
  <body>

      <!-- Navigation Bar -->

      <nav class="navbar navbar-dark bg-dark fixed-top">
          <a class="navbar-brand" href="index">QT Clustering Tool</a>

          <span class="navbar-text" style="color:#01BDD9;">
            Cluster from file / File result
          </span>
        </nav>

    <!-- Main Container -->

    <div class="container vertical-center">
      <div class="col-sm">
      </div>

      <!-- Central Block  -->

      <div class="col-md col-md-whiteblock border shadow">
        <h1 class="h3 mb-3 font-weight-normal">Cluster result : <b>Centroids</b></h1>
        <p id="fileview"></p>
      </div>

      <div class="col-sm">
      </div>
    </div>
  </body>

  <!-- JS Table loading script -->

  <script src="/resources/js/dataset_loader.js"></script>
  <script>
      tableCreate("fileclusterinfo", "fileview");
  </script>

</html>