<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Courses</title>
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
    <h2>Create Course</h2>
    <br><br>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <form  class="form-horizontal" action="/admin/courses/create" method="POST" id="courseForm" name="courseForm">
                    <fieldset class="Norm">
                        <legend>Course's Details</legend>
                        <div class="form-group">
                            <label for="firstName">Title</label>
                            <@spring.bind "courseForm.title"/>
                            <input type="text" class="form-control" id="title" name="title" placeholder="Java I" value="${courseForm.title!""}" required/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                        <div class="form-group">
                            <label for="semester">Semester</label>
                            <@spring.bind "courseForm.semester"/>
                            <input type="number" class="form-control" id="semester" name="semester" placeholder="4" value="${courseForm.semester!""}" required/>
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
            <h2>Search Course</h2>
            <br><br>
            <h4>(Leave every field blank get all courses)</h4>
            <form class="Search" class="form-horizontal" action="/admin/courses/search" method="GET" id="courseSearchForm" name="courseSearchForm">
                <fieldset class="Norm">
                    <div class="col-md-6">
                        <label for="email">Title</label>
                        <@spring.bind "courseSearchForm.title"/>
                        <input type="text" class="form-control" name="title" id="title" placeholder="Java II" value="${courseSearchForm.title!""}"/>
                        <#list spring.status.errorMessages as error>
                            <span class="errorRed">${error}</span>
                        </#list>
                    </div>
                    <div class="col-md-6">
                        <label for="filterInput">Filter</label>
                        <input type="text" class="form-control" name="filterInput" id="filterInput" placeholder="Java"/>
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
    <#if courseList??>
        <h3><u>Retrieved Courses</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table" class="table-hover">
                <thead>
                    <tr>
                        <th>Course ID</th>
                        <th>Course Title</th>
                        <th>Course Semester</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <#list courseList as course>
                    <span>
                        <tr>
                            <td>${course.courseID!"Could not retrieve value!"}</td>
                            <td>${course.title!"Could not retrieve value!"}</td>
                            <td>${course.semester!"Could not retrieve value!"}</td>
                            <form action="/admin/courses/edit/${course.courseID}" method="GET">
                            <td>
                                <button type="submit" class="btn btn-success">
                                    <span class="glyphicon glyphicon-cog"></span>
                                </button>
                            </td>
                            <td>
                                <button type="submit" class="btn btn-danger" formaction="/admin/courses/delete/${course.courseID}" formmethod="POST" onclick="return confirm('Are you sure?')">
                                    <span class="glyphicon glyphicon-remove"></span>
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
