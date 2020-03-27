<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.min.css">

    <!-- Custom CSS -->
    <link rel="stylesheet" href="/resources/css/page_configuration.css">

    <title>QT Clustering Tool - Database</title>
  </head>

  <body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="index">QT Clustering Tool</a>

        <span class="navbar-text" style="color:#01BDD9;">
          Cluster from database
        </span>
    </nav>

    <!-- Main Container -->

    <div class="container vertical-center">
      <div class="col-sm"></div>
      
      <!-- Central Block -->
      
      <div class="col-md col-md-whiteblock border shadow">
        <form action="databaseresult" method="POST">
          <div class="from-group">
            <h1 class="h3 mb-3 font-weight-normal" align="left">Cluster data</h1>
            <hr size="2px"> </hr>
          </div>
          <div class="form-group">
            <h1 class="h6 mb-3 font-weight-normal" align="left">Radius:</h1>
            <input type="number"  step="0.01" min="0" max="100" class="form-control" id="radius" name="radius" placeholder="Enter radius" required>
          </div>
          <div class="form-group">
            <h1 class="h6 mb-3 font-weight-normal" align="left" >Tablename:</h1>
            <input type="text" class="form-control" id="tablename" name="tablename" placeholder="Enter table name" required>
          </div>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Calculate</button>
          <p class="mt-5 mb-3 text-muted">Ivan Diliso - 2019</p>
        </form>
      </div>
      
      <div class="col-sm"></div>
    </div>
  </body>
</html>