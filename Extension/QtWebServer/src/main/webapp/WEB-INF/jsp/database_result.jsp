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

    <title>QT Clustering Tool - Database</title>


  </head>
  <body>

      <!-- Navigation Bar -->

      <nav class="navbar navbar-dark bg-dark fixed-top">
          <a class="navbar-brand" href="index">QT Clustering Tool</a>

           <span class="navbar-text" style="color:#01BDD9;">
              Cluster from database / Cluster result
            </span>
        </nav>

    <!-- Main Container -->

    <div class="container">

        <!-- Navigation Menu -->

        <div class="row justify-content-md-center" style="padding-bottom: 20px">
              <div class="col-6" style="text-align:center" >

                  <div class="btn-group" role="group" >

                        <a href="databaseresult" class="btn btn-secondary">Clustering result</a>
                        <a href="saveresult" class="btn btn-secondary">Save data</a>

                  </div>
              </div>
        </div>
        <div class="row">

            <!-- Dataset table -->

            <div class="col align-top">
                <div class="col-md col-md-whiteblock border shadow">
                    <h1 class="h3 mb-3 font-weight-normal" align="left">Dataset <b>${msg}</b></h1>
                    <p id="tableview"></p>
                  </div>
            </div>

            <!-- Cluster Table -->

            <div class="col align-top">
                <div class="col-md col-md-whiteblock border shadow">
                  <h1 class="h3 mb-3 font-weight-normal" align="left">Cluster informations</h1>
                  <p id="clusterview"></p>
                </div>
            </div>

        </div>
      </div>

  </body>

  <!-- JS Table loading script -->

  <script src="/resources/js/dataset_loader.js"></script>
  <script>
      createClusterInformation("clusterdata","clusterview");
      tableCreate("dataset", "tableview");
  </script>

</html>