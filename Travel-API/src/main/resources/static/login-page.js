const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");
const loginErrorMsg = document.getElementById("login-error-msg");

loginButton.addEventListener("click", (e) => {
	e.preventDefault();
	const username = loginForm.username.value;
	const password = loginForm.password.value;



	if (username != null && password != null) {

		const http = new XMLHttpRequest();
		var params = 'username=' + username + '&password=' + password;

		http.open('GET', 'http://localhost:8081/airports?' + params, true);

		http.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
		http.onreadystatechange = function() {//Call a function when the state changes.
			if (http.readyState == 4 && http.status == 200) {
				const obj = JSON.parse(http.responseText);
				document.getElementById("login").style.display = "none";
				document.getElementById("res").style.display = "block";
				document.getElementById("search").style.display = "block";

				let text = "<table border='1' cellspace='0'<th><tr><td>code</td><td>name</td><td>description</td></tr></th>"

				for (let x in obj) {
					text += "<tr><td>" + obj[x].code + "</td><td>" + obj[x].name + "</td><td>" + obj[x].description + " </td></tr>";

				}
				text += "</table>"

				document.getElementById("res").innerHTML = text;

			} else if (http.readyState != 200) {
				loginErrorMsg.style.opacity = 1;
			}

		}
		http.send(params);
	} else {
		document.getElementById("login").style.display = "block";
		document.getElementById("res").style.display = "none";
		document.getElementById("search").style.display = "none";

	}
	/** 
		if (username === "user" && password === "web_dev") {
			alert("You have successfully logged in.");
			location.reload();
		} else {
			loginErrorMsg.style.opacity = 1;
		}*/
})



