let userTable = document
    .getElementById("userTable");

let rolesForHeader = document
    .getElementById("headerRoles");

let emailForHeader = document
    .getElementById("headerEmail");


fetch('http://localhost:8080/api/user/userInfo')

    .then((response) => {return response.json();
    })

    .then((data) => {console.log(data);

        let roles = "";

        for (let i = 0, l = data.roles.length; i < l; i++) {

            roles += (data.roles[i].name + " ")
                .substring(5);
        }

        var temp = "";

        temp += "<tr>";
        temp += "<td>" + data.id + "</td>";
        temp += "<td>" + data.username + "</td>";
        temp += "<td>" + data.email + "</td>";
        temp += "<td>" + data.age + "</td>";
        temp += "<td>" + roles + "</td></tr>";

        userTable
            .innerHTML = temp;

        emailForHeader
            .innerHTML = data.email;

        rolesForHeader
            .innerHTML = roles;

    });