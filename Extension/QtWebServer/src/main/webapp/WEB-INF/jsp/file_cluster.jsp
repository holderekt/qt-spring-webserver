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
                Cluster from file
              </span>
      </nav>

      <!-- Main Contaier -->

      <div class="container vertical-center" >
            <div class="col-sm">    </div>
        
            <!-- Central Block -->

            <div class="col-md col-md-whiteblock border shadow" >
                <form style="padding:5px;" action="fileresult" method="POST">
                      <h1 class="h3 mb-3 font-weight-normal" align="left">Load cluster</h1>
                      <hr size="2px"> </hr>
                      <div class="form-group">
                              <h1 class="h6 mb-3 font-weight-normal" align="left" >File name:</h1>
                              <input type="text" class="form-control" id="filename" name="filename" placeholder="Enter file name" required>
                              </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Load</button>
                </form>
                <p class="mt-5 mb-3 text-muted">Ivan Diliso - 2019</p>
            </div>
      
            <div class="col-sm"></div>
            
     </div>
  </body>
</html>