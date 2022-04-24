let users_table_body = document
    .getElementById("users_table_body");

let rolesHeader = document
    .getElementById("headerRoles");

let emailHeader = document
    .getElementById("headerEmail");


$(document).ready(function () {
    showUsers();
    showAuthUserInfo();
});


function showAuthUserInfo() {

    fetch('http://localhost:8080/api/admin/users/authUserInfo')

        .then((response) => {
            return response.json();
        })

        .then((data) => {

            let auth_roles = "";

            for (let i = 0, l = data
                .roles.length; i < l; i++) {

                auth_roles += (data.roles[i].name + " ")
                    .substring(5);
            }

            rolesHeader.innerHTML = auth_roles;
            emailHeader.innerHTML = data.email;
        });
}

function showUsers() {
    fetch("http://localhost:8080/api/admin/users")

        .then((response) => {
            return response.json();
        })

        .then((data) => {
            let temp = "";

            data.forEach((u) => {

                let roles = "";
                for (let i = 0, l = u.
                    roles.length; i < l; i++) {

                    roles += (u.roles[i].name + " ")
                        .substring(5);
                }


                temp += "<tr id = row" + u.id + ">";
                temp += "<td  id = id" + u.id + ">" + u.id + "</td>";

                temp += "<td id = username" + u.id + ">" + u.username + "</td>";
                temp += "<td id = email" + u.id + ">" + u.email + "</td>";

                temp += "<td id = age" + u.id + ">" + u.age + "</td>";
                temp += "<td id = roles" + u.id + ">" + roles + "</td>";


                temp += `<td><a 
data-id= '${u.id}'
class ='btn btn-info btn-sm text-white'
 data-toggle='modal' `;

                temp += "data-target= #editModal>";
                temp += "Edit</a>";
                temp += "</td>";

                temp += `<td><a 
data-id= '${u.id}'
class ='btn btn-danger btn-sm text-white' 
data-toggle='modal' `;

                temp += "data-target= #deleteModal>";
                temp += "Delete</a>";
                temp += "</td></tr>";
            })

            users_table_body.innerHTML = temp;
        });
}


function renderNewUser(data) {

    let newUserRoles = "";

    for (let i = 0, l = data.roles.length; i < l; i++) {

        newUserRoles += (data.roles[i].name + " ")
            .substring(5);
    }

    let userRow = `<tr id=row${data.id}>
<td>${data.id}</td>
<td>${data.email}</td>
<td>${data.username}</td>
<td>${data.age}</td>
<td>${newUserRoles}</td>

<td><a 
data-id= '${data.id}'
class ='btn btn-info btn-sm text-white' 
data-toggle='modal'
 data-target= #editModal>Помиловать</a></td>
 
<td><a 
data-id= '${data.id}'
class ='btn btn-danger btn-sm text-white' 
data-toggle='modal' 
data-target= #deleteModal>Покарать</a></td>
</tr>`;

    console.log("Пришло из контроллера в js: " + data.id + data.email);

    $("#users_table_body").append(userRow);
}

function updateUserInTable(data) {

    let roles = "";
    for (let i = 0, l = data.roles.length; i < l; i++) {

        roles += (data.roles[i].name + " ")
            .substring(5);
    }

    $('#id' + data.id).text(data.id);
    $('#username' + data.id).text(data.username);

    $('#email' + data.id).text(data.email);
    $('#age' + data.id).text(data.age);

    $('#roles' + data.id).text(roles);
}


$(document).ready(function () {

    $("#editModal")
        .on('show.bs.modal', function (event) {

            let userId = $(event.relatedTarget)
            .data('id');

        fetch("http://localhost:8080/api/admin/users/read/" + userId)

            .then((response) => {
                return response.json();
            })

            .then((data) => {

                $("#editId").val(data.id);
                $("#editEmail").val(data.email);
                $("#usernameEdit").val(data.username);
                $("#ageEdit").val(data.age);
            });
    });

    $("#submitEdit")
        .on('click', function (event) {

        let user = {

            'id': $('#editId').val(),
            'email': $('#editEmail').val(),
            'password': $('#passwordEdit').val(),
            'username': $('#usernameEdit').val(),
            'age': $('#ageEdit').val(),
            'rolesSelectedFromForm': $("#rolesEdit").val().toString()
        };


        const requestOptions = {

            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(user)};

        fetch('http://localhost:8080/api/admin/users/edit', requestOptions)

            .then((response) => {
                return response.json();})

            .then((data) => {
                $("#editModal").modal('hide');
                updateUserInTable(data);});
    });


    $("#deleteModal")
        .on('show.bs.modal', function (event) {

            let userId = $(event.relatedTarget).data('id');

            fetch("http://localhost:8080/api/admin/users/read/" + userId)

                .then((response) => {
                return response.json();
            })

                .then((data) => {
                $("#idDelete").val(data.id);
                $("#deleteEmail").val(data.email);
                $("#usernameDelete").val(data.username);
                $("#ageDelete").val(data.age);
            });

        $("#submitDelete")
            .on('click', function (event) {

                fetch('http://localhost:8080/api/admin/users/delete/' +
                    $("#idDelete").val(), {method: 'DELETE'})
                .then((response) => {
                    $("#deleteModal").modal('hide');
                    $("#row" + $("#idDelete").val()).remove();})
        });
    });
})


$("#submitNew").on('click', function (event) {

    let user = {

        'email': $('#emailNew').val(),
        'password': $('#passwordNew').val(),
        'username': $('#usernameNew').val(),
        'age': $('#ageNew').val(),
        'rolesSelectedFromForm': $("#rolesNew").val().toString()};

    const requestOptions = {

        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(user)
    };

    fetch('http://localhost:8080/api/admin/users/create', requestOptions)

        .then((response) => {
            return response.json();})

        .then((data) => {
            $("#users_Table").active();
            renderNewUser(data);});
});


const password = document
    .getElementById("passwordNew");

const confirm_password = document
    .getElementById("confirm_passwordNew");

function validatePassword() {

    if (password.value !== confirm_password.value) {

        confirm_password
            .setCustomValidity("Введенные Проклятья не туда");
    } else {
        confirm_password
            .setCustomValidity('');
    }
}

password.onchange = validatePassword;
confirm_password.onkeyup = validatePassword;