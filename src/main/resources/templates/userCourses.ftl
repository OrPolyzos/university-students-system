<#import "/spring.ftl" as spring/>
<html>

<head>
    <title>User Courses</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/../utilities.js"></script>
    <link rel="stylesheet" href="/../styles.css">
    <style>
        fieldset.Hor {
            float: left;
            width: 50%;
            padding: 20;
        }

        fieldset.Norm {
            padding: 20;
            width: 100%;
        }
    </style>

</head>

<body>
<#include "navbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
<div>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <h2 align="center">Add Courses to Users</h2>
                <form class="form-horizontal" action="/admin/users/courses/add" method="POST" id="userCourseForm"
                      name="userCourseForm">
                    <@spring.bind "userCourseForm.userID"/>
                    <input type="hidden" name="userID" value="${userCourseForm.userID!""}"/>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label for="partID">Course ID</label>
                            <@spring.bind "userCourseForm.courseID"/>
                            <input class="form-control" type="number" name="courseID" id="courseID" placeholder="1"
                                   value="${userCourseForm.courseID!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>
                    <fieldset class="Hor">
                        <div class="form-group">
                            <label for="room">Room</label>
                            <@spring.bind "userCourseForm.room"/>
                            <input class="form-control" type="text" name="room" id="room" placeholder="A 101"
                                   value="${userCourseForm.room!""}"/>
                            <#list spring.status.errorMessages as error>
                                <span class="errorRed">${error}</span>
                            </#list>
                        </div>
                    </fieldset>
                    <div class="col-sm-12 controls">
                        <span>
                            <button type="submit" id="btn-submit" class="btn btn-success btn-md">Add</button>
                            <button type="reset" id="btn-clear" class="btn btn-danger btn-md">Clear</button>
                        </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<#if userCourseList??>
    <h3 class="text-center">
        <u>Retrieved Courses of this User</u></h3>
    <div class="table-responsive">
        <table class="table" class="table-hover">
            <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Title</th>
                <th>Course Semester</th>
                <th>Room</th>
                <th>Delete Course</th>
            </tr>
            </thead>
            <tbody>
            <#list userCourseList as userCourse>
                <span>
                <tr>
                    <td>${userCourse.courseID}</td>
                    <td>${userCourse.course.title}</td>
                    <td>${userCourse.course.semester}</td>
                    <td>${userCourse.room}</td>
                    <td>
                        <form action="/admin/users/courses/delete/${userCourse.userID}/${userCourse.course.courseID}"
                              method="POST">
                        <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure?')">
                            <span class="glyphicon glyphicon-remove"></span>
                        </button>
                        </form>
                    </td>
                </tr>
                </span>
            </#list>
            </tbody>
        </table>
    </div>
<#else>
    <h2>No courses added to this user yet!</h2>
</#if>
<hr/>
<hr/>
<#if wholeCoursesList??>
    <h3 class="text-center"><u>All available courses</u></h3>
    <br><br>
    <div class="container-fluid">
        <div class="row">
            <form class="form-horizontal">
                <fieldset class="Norm">
                    <div class="col-md-6 col-md-offset-3">
                        <label for="filterInput">Filter</label>
                        <input type="text" class="form-control" name="filterInput" id="filterInput"
                               placeholder="abc-123..."/>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <br><br>
    <div class="table-responsive">
        <table id="resultsTable" class="table" class="table-hover">
            <thead>
            <tr>
                <th>Course ID</th>
                <th>Course Title</th>
                <th>Course Semester</th>
            </tr>
            </thead>
            <tbody>
            <#list wholeCoursesList as course>
                <span>
                <tr>
                    <td>${course.courseID}</td>
                    <td>${course.title}</td>
                    <td>${course.semester}</td>
                </tr>
                </span>
            </#list>
            </tbody>
        </table>
    </div>
<#else>
    <h2>There are no courses at all!</h2>
</#if>
<#include "footer.ftl">
</body>
</html>

