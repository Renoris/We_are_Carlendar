<!doctype html>
<html lang="ko">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" type="text/css" href="/resources/core/main.css"/>
        <link rel="stylesheet" type="text/css" href="/resources/daygrid/main.css"/>
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <!-- Popper JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="/resources/core/main.js"></script>
        <script type="text/javascript" src="/resources/interaction/main.js"></script>
        <script type="text/javascript" src="/resources/daygrid/main.js"></script>
        <script type="text/javascript" src="/resources/core/locales/ko.js"></script>
        <script type="text/javascript">
            document.addEventListener('DOMContentLoaded', function () {
                var calendarEl = document.getElementById('calendar');
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    plugins: ['interaction', 'dayGrid'],
                    defaultView: 'dayGridMonth',
                    defaultDate: new Date(),
                    header: {left: 'prev,next today', center: 'title', right: ''},
                    locale: 'ko',
                    events: [
                    <#if calendarlist??>
                        <#list calendarlist as item>
                            <#if item??>
                                {
                                    id:'${item.id}',
                                    title: '${item.title}',
                                    start: '${item.startdate}',
                                    end: '${item.enddate}'
                                },
                            </#if>
                        </#list>
                    </#if>
                    ],
                    eventClick:function (event) {
                        console.log(event.event.id);
                        location.replace("/deleteCal?id="+event.event.id);
                    }
                });

                calendar.render();
            });
        </script>




        <title>My Calendar</title>
    </head>
    <body>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#viewCal">일정정리</button>
        <div id='calendar'></div>
        <div class="modal" id="viewCal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->

                    <div class="modal-body">
                        <#if calendarlist??>
                            <#list calendarlist as item>
                                <#if item??>
                                    <div class="form-group">
                                        <label for="update_${item.id}">${item.title}</label>
                                        <input type="button" class="btn btn-primary" value="수정" id="update_${item.id}"
                                               data-toggle="modal" data-target="#makeCal" onclick="pickedID(this)" >
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#makeCal" onclick="origin()">일정 작성</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                    </div>

                </div>
            </div>
        </div>
        <div class="modal" id="makeCal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <form method="POST" action="/mycalendar">
                            <div class="form-group">
                                <input type="hidden" name="id" value="" id="idUpdate">
                                <label for="calTitle">일정 제목:</label>
                                <input name="title" type="text" class="form-control" placeholder="일정의 제목을 적어주세요!" id="calTitle">
                            </div>
                            <div class="form-group">
                                <label for="calComment">일정 내용:</label>
                                <input name="content" type="text" class="form-control" placeholder="무슨 일정인지 적어주세요!" id="calContent">
                            </div>

                            <div class="form-group">
                                <label for="calStartDate">일정 시작 날짜</label>
                                <input name ="startdate" type="date" class="form-control" placeholder="날짜" id="calStartDate">
                            </div>
                            <div class="form-group">
                                <label for="calStartTime">일정 시작 시간</label>
                                <input name ="starttime" type="time" class="form-control" placeholder="시간" id="calStartTime">
                            </div>

                            <div class="form-group">
                                <label for="calEndDate">일정 끝 날짜</label>
                                <input name ="enddate" type="date" class="form-control" placeholder="날짜" id="calEndDate">
                            </div>
                            <div class="form-group">
                                <label for="calEndTime">일정 끝 시간</label>
                                <input name ="endtime" type="time" class="form-control" placeholder="시간" id="calEndTime">
                            </div>

                            <input type="submit" value="일정 작성" class="btn btn-primary btn-block">
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
        function pickedID(obj) {
            var buttonID = $(obj).attr('id');
            document.getElementById('idUpdate').value=buttonID.substring(7,8);
        }
        function origin(){
            document.getElementById('idUpdate').value=-1;
        }
    </script>
</html>