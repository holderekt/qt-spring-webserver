/*
	Get data from url
*/
function Get(yourUrl) {
	var Httpreq = new XMLHttpRequest();
	Httpreq.open("GET",yourUrl,false);
	Httpreq.send(null);
	return Httpreq.responseText;
}


/*
	Get json from url
*/
function GetJSONData(string) {
	var obj = JSON.parse(Get(string));
	obj = obj['data'];
	return obj;
}


/*
	Create table based on dataset type json
*/
function tableCreate(json_location, id) {
	var obj = GetJSONData(json_location);

	// Table creation

	var tbl = document.createElement('table');
	tbl.setAttribute('class', 'table table-striped');

	var tbdy = document.createElement('tbody');

	// Head creation with column name values

	var keys = Object.keys(obj);
	var length = obj[keys[0]].length;

	var thead = document.createElement("thead");
	thead.setAttribute("class", "thead-dark");
	var trtitle = document.createElement('tr');

	for(var key in keys){
	  var th = document.createElement('th');
	  th.appendChild(document.createTextNode(keys[key]))
	  trtitle.appendChild(th)
	}

	thead.appendChild(trtitle);
	tbl.appendChild(thead);

	// Body creation

	for (i=0; i<length; i++) {
		var tr = document.createElement('tr');
		for (var column in obj ) {
			var td = document.createElement('td');
			td.appendChild(document.createTextNode(obj[column][i]));
			tr.appendChild(td);
		}
		tbdy.appendChild(tr);
	}

	tbl.appendChild(tbdy);
	document.getElementById(id).appendChild(tbl);
}


/*
	Create table based on clusterdata type json
*/
function createClusterInformation(json_location, id) {

	var obj = GetJSONData(json_location);
	var par = document.getElementById(id);

	for(var cluster in obj){

		// Centroid table creation

		var tab = document.createElement("table");
		tab.setAttribute('class', 'table table-sm table-striped');

		// Centroid head

		var thead = document.createElement("thead");
		thead.setAttribute("class", "thead-dark");

		var tr = document.createElement("tr");
		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Centroid"));
		th.setAttribute("colspan", obj[cluster]['examples'][0]['l'].length);

		tr.appendChild(th);
		thead.appendChild(tr);
		tab.appendChild(thead);

		// Centroid body

		var tr = document.createElement("tr");

		for(var elem in obj[cluster]['centroid']){
			var td = document.createElement('td');
			td.appendChild(document.createTextNode(obj[cluster]['centroid'][elem]))
			tr.appendChild(td)
		}

		tab.appendChild(tr);
		par.appendChild(tab);


		// Examples table creation

		var tab = document.createElement("table");
		tab.setAttribute('class', 'table table-sm table-striped');

		// Examples head

		var thead = document.createElement("thead");
		thead.setAttribute("class", "thead-dark");
		var tr = document.createElement("tr");
		var th = document.createElement("th");

		th.appendChild(document.createTextNode("Examples"));
		th.setAttribute("colspan", obj[cluster]['examples'][0]['l'].length);
		tr.appendChild(th);

		var th = document.createElement("th");
		th.appendChild(document.createTextNode("Distance"));

		tr.appendChild(th);
		thead.appendChild(tr);
		tab.appendChild(thead);

		// Example body

		var tbody = document.createElement("tbody");

		for(var elem in obj[cluster]['examples']){

			var tr = document.createElement("tr");

			for(var column in obj[cluster]['examples'][elem]['l']){
				var td = document.createElement('td');
				td.appendChild(document.createTextNode(obj[cluster]['examples'][elem]['l'][column]))
				tr.appendChild(td);
			}

			// Distance column

			var td = document.createElement('td');
			td.setAttribute("class", "border-left");

			// Double value  approximation

			var num = parseFloat(obj[cluster]['examples'][elem]['r']).toFixed(4);
			td.appendChild(document.createTextNode(num))
			tr.appendChild(td)
			tbody.appendChild(tr);
		}

		// Average distance row

		var tr = document.createElement("tr");
		var td = document.createElement('td');
		td.appendChild(document.createTextNode("Average distance:  " + obj[cluster]['average_distance']));
		td.setAttribute("colspan", obj[cluster]['examples'][0]['l'].length + 1);
		td.setAttribute("class", "border-bottom small bold");
		td.setAttribute("style", "background-color: #ffffff")
		tr.appendChild(td);
		tbody.appendChild(tr);


		tab.appendChild(tbody);
		par.appendChild(tab);
		par.appendChild(document.createElement("br"));
		par.appendChild(document.createElement("br"));
	}
}


