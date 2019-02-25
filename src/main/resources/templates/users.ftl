<#import "/spring.ftl" as spring/>
<html>

<head>
    <title>Users</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">
    <style>
        fieldset.Hor {
            float: left;
            width: 50%;
            padding: 20;
        }

        fieldset.Norm {
            width: 100%;
            padding: 20;
        }
    </style>

</head>

<body>
<#include "navbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
<h2>Create User</h2>
<br><br>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" action="/admin/users/create" method="POST" id="userForm" name="userForm">
                <fieldset class="Hor">
                    <legend>User's Personal Details</legend>
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <@spring.bind "userForm.firstName"/>
                        <input type="text" class="form-control" id="firstName" name="firstName" placeholder="John"
                               value="${userForm.firstName!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <@spring.bind "userForm.lastName"/>
                        <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Doe"
                               value="${userForm.lastName!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                </fieldset>
                <fieldset class="Hor">
                    <legend>User's Credentials</legend>
                    <div class="form-group">
                        <label for="type">Type</label>
                        <@spring.bind "userForm.type"/>
                        <select class="form-control" id="type" name="type">
                            <option value="Admin">Admin</option>
                            <option value="Teacher">Teacher</option>
                            <option value="Student">Student</option>
                        </select>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <@spring.bind "userForm.email"/>
                        <input type="email" class="form-control" id="email" name="email" placeholder="john@doe.com"
                               value="${userForm.email!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <@spring.bind "userForm.password"/>
                        <input type="password" class="form-control" id="password" name="password" placeholder="p4$$w0rd"
                               value="${userForm.password!""}" required/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                </fieldset>
                <div class="col-md-12 controls">
                    <span>
                        <button type="submit" id="btn-submit" class="btn btn-success btn-md">Create</button>
                        <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                    </span>
                </div>
            </form>
        </div>
    </div>
</div>
<hr/>
<div class="container-fluid">
    <div class="row">
        <h2>Search User</h2>
        <br><br>
        <h4>(Leave every field blank get all users)</h4>
        <form class="Search" class="form-horizontal" action="/admin/users/search" method="GET" id="userSearchForm"
              name="userSearchForm">
            <fieldset class="Norm">
                <div class="col-md-6">
                    <label for="email">Email</label>
                    <@spring.bind "userSearchForm.email"/>
                    <input type="email" class="form-control" name="email" id="email" placeholder="john@doe.com"
                           value="${userSearchForm.email!""}"/>
                    <#list spring.status.errorMessages as error>
                        <span class="errorRed">${error}</span>
                    </#list>
                </div>
                <div class="col-md-6">
                    <label for="filterInput">Filter</label>
                    <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="john"/>
                </div>
            </fieldset>
            <div class="col-md-12 controls">
                    <span>
                        <button type="submit" id="btn-submit" class="btn btn-primary">Search</button>
                        <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
                    </span>
            </div>
        </form>
    </div>
</div>
<h2>${searchNotFoundMessage!""}</h2>
<#if userList??>
    <h3><u>Retrieved Users</u></h3>
    <div class="table-responsive">
        <table id="resultsTable" class="table" class="table-hover">
            <thead>
            <tr>
                <th>User ID</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Type</th>
                <th>Edit</th>
                <th>Delete</th>
                <th>Courses</th>
            </tr>
            </thead>
            <tbody>
            <#list userList as user>
                <span>
                        <tr>
                            <td>${user.userID!"Could not retrieve value!"}</td>
                            <td>${user.email!"Could not retrieve value!"}</td>
                            <td>${user.firstName!"Could not retrieve value!"}</td>
                            <td>${user.lastName!"Could not retrieve value!"}</td>
                            <td>${user.type!"Could not retrieve value!"}</td>
                            <form action="/admin/users/edit/${user.userID}" method="GET">
                            <td>
                                <button type="submit" class="btn btn-success">
                                    <span class="glyphicon glyphicon-cog"></span>
                                </button>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-danger"
                                        formaction="/admin/users/delete/${user.userID}" formmethod="POST"
                                        onclick="return confirm('Are you sure?')">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-success"
                                        formaction="/admin/users/courses/${user.userID}" formmethod="GET">
                                    <span class="glyphicon glyphicon-plus"></span>
                                </button>
                            </td>
                            </form>
                        </tr>
                    </span>
            </#list>
            </tbody>
        </table>
    </div>
</#if>
<#include "footer.ftl">
</body>
</html>
