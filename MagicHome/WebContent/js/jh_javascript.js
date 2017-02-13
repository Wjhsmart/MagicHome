function checkImg(obj) {
	var showDiv = document.getElementById("check_img");
	showDiv.style = "display: block; background-image: url(" + obj + "); background-size:500px 300px; " +
			"position: fixed; top: 100px; left: 300px; z-index: 100; width: 500px; height: 300px;" ;
}
function cancel() {
	var showDiv = document.getElementById("check_img");
	showDiv.style = "display: none;";
}

function affirmDelete(obj) {
	if (confirm("确认删除 " + obj + " ?")) {
		return true;
	} else {
		return false;
	}
}

