let socket = null;
let map = null;
let events = [];
let markers = [];
let circles = [];

let settings = {"circleSize": 30};

function initIO() {
	document.getElementById("details_arrow").innerHTML = "&#187;";
    socket = io();
	
    socket.on('addPending', function (data) {
        $('#pending_number').html(+$('#pending_number').text() + 1);
		data = sanitizeData(data);

        var priority = 0;
		priority += data.people.stable + data.people.serious * 2 + data.people.critical * 3;
        plot(data, priority);
        addToLog("Zdarzenie " + data.eventType + " zaznaczone na mapie.", data.date, priority, 'pending');
    });
	
	socket.on('addInProgress', function (data) {
		$('#pending_number').html( + $('#pending_number').text() - 1);
		$('#in-progress_number').html( + $('#in-progress_number').text() + 1);
		data = sanitizeData(data);
		var priority = 0;
		priority += data.people.stable + data.people.serious * 2 + data.people.critical * 3;
		for (var i = 0; i < markers.length; i++)
		{
			if (markers[i].id === data.id)
				markers[i].setMap(null);
		}
		
        plot(data, priority).setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
		printEventInfo(data);
	});
	
	socket.on('addCompleted', function (data) {
		console.log('data');
		$('#in-progress_number').html( + $('#in-progress_number').text() - 1);
		$('#completed_number').html( + $('#completed_number').text() + 1);
		for (var i = 0; i < markers.length; i++)
		{
			if (markers[i].id === data.id)
				markers[i].setMap(null);
		}
		switchDetailsPanel('#log_content', null);
	});
	
	socket.on('calling', function (data) {
		console.log(data);
	});

	socket.on('predict', function (data) {
		if(data.centroids.length <= 1) {
			document.getElementById("no_threat").innerHTML = "The planet is safe!";
			document.getElementById("infoBackground").style.display = "block";
		} else {
			document.getElementById("no_threat").innerHTML = "Threats has been found!";
			document.getElementById("infoBackground").style.display = "block";
		}
	});

    $.ajax({
		method: "GET",
		url: "http://51.254.130.93:3070/event/all",
	}).done(function (arr) {
		for(var i = 0; i < arr.length; i++)
		{
			arr[i] = sanitizeData(arr[i]);
			if(arr[i].status === 'pending')
			{
				var priority = 0;
				var people = arr[i].people;
				priority += people.stable + people.serious * 2 + people.critical * 3;
			
				$('#pending_number').html(+$('#pending_number').text() + 1);
				plot(arr[i], priority);
				addToLog("Zdarzenie " + arr[i].eventType + " zaznaczone na mapie.", arr[i].date, priority, 'pending');
			}
			else if(arr[i].status === 'inProgress')
			{
				var priority = 0;
				var people = arr[i].people;
				priority += people.stable + people.serious * 2 + people.critical * 3;
				
				$('#in-progress_number').html(+$('#in-progress_number').text() + 1);
				plot(arr[i], priority).setIcon('http://maps.google.com/mapfiles/ms/icons/green-dot.png');
				addToLog("Zdarzenie " + arr[i].eventType + " zaznaczone na mapie.", arr[i].date, priority, 'in progress');
			}
			else
			{
				$('#completed_number').html(+$('#completed_number').text() + 1);
				addToLog("Zdarzenie " + arr[i].eventType + " zdjęte z mapy.", arr[i].date, priority, 'completed');
			}
		}
	});
}

window.onload = initIO;

const mapDiv = document.getElementById('map_wrapper');

let bisDetailsShowed = true;
$('#details_arrow').click(function () {
    if (bisDetailsShowed)
	{
        $('#map_wrapper').css("width", "99%");
        $('#details_wrapper').css("width", "1%");
        bisDetailsShowed = false;
        this.innerHTML = "&#171;";
        this.style.marginLeft = "-25px";
    }
	else
	{
        $('#map_wrapper').css("width", "70%");
        $('#details_wrapper').css("width", "30%");
        bisDetailsShowed = true;
        this.innerHTML = "&#187;";
        this.style.marginLeft = "10px";
    }
    setTimeout(function () {
        google.maps.event.trigger(map, "resize");
    }, 1000);
});

function plot(data, priority) {
    var marker = new google.maps.Marker({
        position: {
            lat: data.location.x,
            lng: data.location.y
        },
        map: map,
        animation: google.maps.Animation.DROP,
        title: data.username.name + " " + data.username.surname + ": " + data.eventType
    });
	
    marker.addListener('click', function() {
		switchDetailsPanel("#marker_content", null);
		printEventInfo(data);
		document.getElementById('accept_request_button').setAttribute('data-id', marker.id);
	});
    marker['id'] = data.id;
    markers.push(marker);
	events['priority'] = priority;
    events.push(data);
	return marker;
}

$(".details_button_border").click(function () {
	let elem = null;
	
	if ($(this).index() == 0)
		elem = "#log_content";
    else
		elem = "#settings_content";

    switchDetailsPanel(elem, this);
});

function printEventInfo(event) {
	const rows = $("#marker_table").children(0);
    $('#event-type').text(event.eventType);
    $('#reported-by').text(event.username.name + " " + event.username.surname);
    $('#people-involved').text(event.people.stable + event.people.serious + event.people.critical);
    $('#phone-number').text(event.number);
    $('#priority').text(event.priority);

	const button = document.getElementById("accept_request_button");
	if(event.status === "pending")
	{
		button.innerHTML = "Accept request";
		button.setAttribute("onclick", "button_Pending();");
		event.status = "in progress";
	}
	else if (event.status === "inProgress")
	{
		button.innerHTML = "Close request";
		button.setAttribute("onclick", "button_InProgress();");
		event.status = "completed";
	}
	else {
		button.setAttribute("onclick", "return false;");
		button.style.display = "none";
	}
}

function getStreetName(lat, lng) {
	const inData = {latlng: lat+", "+lng, sensor: false};
	$.ajax({
		method: "GET",
		data: inData,
		url: "http://maps.googleapis.com/maps/api/geocode/json",
	}).done(function (msg) {
		if(msg.status == "OK")
			return msg.results[0];
		else return null;
	});
}

function switchDetailsPanel(panel, button) {
	const divs = document.getElementById("details_buttons").children;
	
    for (let i = 0; i < divs.length; i++)
		$(divs[i]).removeClass("detail_selected");
		
    $(button).addClass('detail_selected');
    const panels = $(".details_main_panel");
	for (let i = 0; i < panels.length; i++)
		$(panels[i]).removeClass("panel_active");
		
    $(panel).addClass('panel_active');
}

function addToLog(name, date, priority, status) {
    let color = "";
	
    if (priority <= 8)
		color = "#9cff9c"; // green
    else if (priority > 8 && priority < 13)
		color = "#ffff6f"; // yellow
    else
		color = "red"; // red
		
	if (status === 'in progress')
		color = 'lightblue';
	
	if (status === 'completed')
		color = 'lightpurple';
	
    $('#log_table').children(0).html($('#log_table').children(0).html() + "<tr style='background: " + color + ";'><td>" + name + "</td><td>" + date + "</td><td>" + status + "</td></tr>");
}

function initMap() {
    var myLatLng = {lat: 52.224, lng: 20.993};

    // Create a map object and specify the DOM element for display.
    map = new google.maps.Map(mapDiv, {
        center: myLatLng,
        scrollwheel: false,
        zoom: 15,
        zoomControl: true,
        scaleControl: true
    });

    // Create a marker and set its position.
    var marker = new google.maps.Marker({
        map: map,
        position: myLatLng,
        title: 'Hello World!'
    });
}

function sanitizeData(data) {
	data.location = JSON.parse(data.location);
	data.username = JSON.parse(data.username);
	data.people = JSON.parse(data.people);
	
	return data;
}

// Settings
var foo = true;
$('#show_priorities_button').click(function () {
    let i = 0;
	for(i; i < circles.length; i++)
		circles[i].setMap(null);
		
	console.log(circles);
	if (!this.checked)
	{
		circles = [];
		return;
	}

    for (i=0; i < markers.length; i++)
	{
        // Add the circle for this city to the map.
		var cityCircle = new google.maps.Circle({
			strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            map: map,
            center: markers[i].position,
            radius: events[i].priority * settings.circleSize
        });
        circles.push(cityCircle);
    }
	
	console.log(circles);
});

function button_Pending (elem) {
    const id = elem.getAttribute("data-id");
    socket.emit('addInProgress', id);
};

function button_InProgress(elem) {
	const id = elem.getAttribute("data-id");
	socket.emit('addCompleted', id);
}

$('#predict_button').click(function () {
	// Get data
	$.ajax({
		method: "GET",
		url: "http://51.254.130.93:3070/event/all",
	}).done(function (arr) {
		let coordsArray = [];
		for(let i = 0; i < arr.length; i++)
		{
			const coords = arr[i].location.parse(arr[i].location);
			coordsArray.push(coords);
		}
		socket.emit('predict', coordsArray);
	});

})

$('#infoWrapperClose').click(function () {
	document.getElementById("infoBackground").style.display = "none";
})

function circleChange(elem) {
	settings.circleSize = elem.value;
}