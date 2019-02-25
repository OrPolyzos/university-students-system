<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Edit User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" type="text/css" media="screen" href="/../styles.css">
    <script type="text/javascript" src="../utilities.js"></script>

</head>
<body>
<#include "navbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" action="/admin/courses/editCourse" method="POST" id="courseForm"
                  name="courseForm">
                <h4>Course's Details</h4>
                <@spring.bind "courseForm.courseID" />
                <input type="hidden" id="courseID" name="courseID" value="${courseForm.courseID!""}">
                <!-- <@spring.bind "courseForm.courseID" /> -->
                <#--bind this field with the registration form fields-->
                <div class="form-group">
                    <@spring.bind "courseForm.title" />
                    <label for="title">Title</label>
                    <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="glyphicon glyphicon-pencil"></i>
                                    </span>
                        <input type="text" class="form-control" id="title" name="title" placeholder="Java II"
                               value="${courseForm.title!""}">
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorRed">${error}</span>
                    </#list>
                </div>
                <div class="form-group">
                    <@spring.bind "courseForm.semester"/>
                    <label for="afm">Semester</label>
                    <div class="input-group">
                            <span class="input-group-addon">
                                <i class="glyphicon glyphicon-file"></i>
                            </span>
                        <input type="number" class="form-control" min="1" max="8" name="semester" id="semester"
                               placeholder="4" value="${courseForm.semester!""}"/>
                    </div>
                    <#list spring.status.errorMessages as error>
                        <span class="errorRed">${error}</span>
                    </#list>
                </div>
                <button type="submit" class="btn btn-success">Save</button>
                <button type="reset" id="btn-clear" class="btn btn-danger">Clear</button>
            </form>
        </div>
    </div>
</div>
<#include  "footer.ftl">
</body>
</html>
