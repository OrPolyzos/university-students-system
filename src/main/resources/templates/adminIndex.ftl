<!DOCTYPE>
<html>
<head>
    <title>Admin Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <!---- Our Custom CSS ----->
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<#include "navbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
<#if user??>
    <h1>Welcome to the Admin Panel of the University Student System dear ${user.firstName!"admin"}!</h1>
    <#if courseList??>
        <h3><u>All available Courses</u></h3>
        <div class="table-responsive">
            <table id="resultsTable" class="table">
                <thead>
                <tr>
                    <th>Course ID</th>
                    <th>Course Title DateTime</th>
                    <th>Course Semester</th>
                </tr>
                </thead>
                <tbody>
                <#list courseList as course>
                    <span>
                                <tr>
                                    <td>${course.courseID!}</td>
                                    <td>${course.title!"Could not retrieve title!"}</td>
                                    <td>${course.semester!"Could not retrieve semester!"}</td>
                                </tr>
                            </span>
                </#list>
                </tbody>
            </table>
        </div>
    <#else>
        <h3><u>No Courses found at all!</u></h3>
    </#if>
</#if>
<#include "footer.ftl">
</body>
</html>