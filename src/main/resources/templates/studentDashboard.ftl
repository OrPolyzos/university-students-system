<!DOCTYPE html>
<html>
<head>
    <title>Student Dashboard</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/styles.css">
    <link rel="stylesheet" href="/p5styles.css">
    <!---- Trying to make some cool stuff with p5.js ----->
    <script async src=https://CDN.JSDelivr.net/g/p5.js(p5.min.js+addons/p5.dom.js+addons/p5.sound.js)></script>
    <script defer src=sketch.js></script>
    <script defer src=particle.js></script>
</head>
<body>
<#include "studentNavbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
        <#if user??>
            <h1>Welcome to Piraeus Unversity dear ${user.firstName!"student"}!</h1>
            <#if user.userCourses??>
                <h3><u>Retrieved Courses</u></h3>
                <div class="table-responsive">
                    <table id="resultsTable" class="table">
                        <thead>
                        <tr>
                            <th>Course Id</th>
                            <th>Title</th>
                            <th>Semester</th>
                            <th>Grade</th>
                            <th>Room</th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list user.userCourses as userCourse>
                            <span>
                                    <tr>
                                        <td>${userCourse.course.courseID!"Could not retrieve value!"}</td>
                                        <td>${userCourse.course.title!"Could not retrieve value!"}</td>
                                        <td>${userCourse.course.semester!"Could not retrieve value!"}</td>
                                        <td>${userCourse.grade!"-"}</td>
                                        <td>${userCourse.room!"Could not retrieve value!"}</td>
                                    </tr>
                                </span>
                            </#list>
                        </tbody>
                    </table>
                </div>
            <hr/>
            </#if>
            <#include "footer.ftl">
        </#if>
</body>
</html>