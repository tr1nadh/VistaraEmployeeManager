function onLoadFunction(isEmpListEmpty, nextPageValue, shouldDisablePgntion) {
    if (isEmpListEmpty) {
        document.getElementById("next-item").classList.add("disabled");
    }
    if (nextPageValue == 3) {
        document.getElementById("prev-link").href = "view";
    }
    if (nextPageValue <= 2 || nextPageValue == -1) {
        document.getElementById("first-item").classList.add("disabled");
        document.getElementById("prev-item").classList.add("disabled");
    }
    if (nextPageValue == -1) {
        document.getElementById("next-item").classList.add("disabled");
    }
}

function confirmDelete(id) {
    let text = "Are you sure, you want to delete?";
    if (confirm(text) == true) {
        window.location.assign("deleteEmployee?id=" + id);
    }
}