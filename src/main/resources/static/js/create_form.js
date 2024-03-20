
const submitBtn = document.getElementById("submitBtn");

document.addEventListener("DOMContentLoaded", function() {
    const selectCategory = document.getElementById("select-category");
    const uniOption = document.querySelector(".uni-option");

    selectCategory.addEventListener("change", function() {
        if (selectCategory.value === "uni") {
            uniOption.classList.remove("visually-hidden");
        } else {
            uniOption.classList.add("visually-hidden");
        }
    });
});


submitBtn.addEventListener('click', () => {
    const createForm = document.getElementById("create_form");
    fetch()
});