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
<#include "teacherNavbar.ftl">
<h1 class="errorRed">${errorMessage!""}</h1>
        <#if user??>
            <h1>This is the students list for the course ${course.title!"teacher"}!</h1>
            <#if studentCourses??>
                <h3><u>Retrieved Students</u></h3>
                <div class="table-responsive">
                    <table id="resultsTable" class="table">
                        <thead>
                        <tr>
                            <th>Student Id</th>
                            <th>Student First Name</th>
                            <th>Student Last Name</th>
                            <th>Grade</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list studentCourses as studentCourse>
                            <span>
                                    <tr>
                                        <td>${studentCourse.user.userID!"Could not retrieve value!"}</td>
                                        <td>${studentCourse.user.firstName!"Could not retrieve value!"}</td>
                                        <td>${studentCourse.user.lastName!"Could not retrieve value!"}</td>
                                        <form action="/teacher/courses/${course.courseID}/students/${studentCourse.user.userID}"
                                              method="POST">
                                            <td>
                                                <input type="number" id="grade" name="grade" placeholder="123456789" value="${studentCourse.grade!"-"}"/>
                                            </td>
                                            <td><button type="submit" class="btn btn-success">Save</button></td>
                                        </form>
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