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
        <link rel="stylesheet" type="text/css" href="/resources/bj/main.css"/>
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
            var isinsert=0;

            function closeRestMakeCal(){ //그냥 닫기
                $("#makeCalRest").hide();
            }

            function openRestMakeCalInsert(){ //일정 작성시 id값 -1로하면서 모달보이기//
                document.getElementById("idUpdateRest").value=-1;
                closeRestViewCal();
                isinsert=1;
                $("#makeCalRest").show();
            }

            function openRestMakeCalUpdate(obj){//일정 수정시 id값 삽입하면서 모달보이기//버튼에 삽입
                var buttonID = $(obj).attr('id');
                var splitarray = buttonID.split("_");
                document.getElementById("idUpdateRest").value=splitarray[1];
                closeRestViewCal();
                isinsert=0;
                $("#makeCalRest").show();
            }


            var datajson;
            function makeCalRest(){ //일정 작성/수정 버튼 클릭시 이벤트
                var id=document.getElementById("idUpdateRest").value;
                var title=document.getElementById("calTitleRest").value;
                var content=document.getElementById("calContentRest").value;
                var startdate=document.getElementById("calStartDateRest").value;
                var starttime=document.getElementById('calStartTimeRest').value;
                var enddate=document.getElementById('calEndDateRest').value;
                var endtime=document.getElementById('calEndTimeRest').value;
                datajson={
                    'id':id,
                    'title':title,
                    'content':content,
                    'startdate':startdate,
                    'starttime':starttime,
                    'enddate':enddate,
                    'endtime':endtime
                }
                var promise2=$.ajax({
                    type:"POST",
                    url:"/CalRestSave",
                    dataType:"json",
                    data:datajson,
                    traditional:true
                })

                promise2.done(insertSuccess);
                promise2.fail(failInsertUpdate());
            }
            function failInsertUpdate(){ //일정 작성/수정 실패시 메소드
                alert("실패했습니다.");
            }

            function closeRestViewCal(){ //일정 리스트 닫기 메소드
                var modalBody =document.getElementById("restViewModalBody");
                while(modalBody.hasChildNodes()){
                    modalBody.removeChild(modalBody.firstChild);
                }
                $("#viewCalRest").hide();
            }

            function calDeleteRest(obj){
                var buttonID = $(obj).attr('id');
                var splitarray = buttonID.split("_");
                datajson={
                    'id':splitarray[1]
                }
                var promise=$.ajax({
                    type:"POST",
                    url:"/CalRestDelete",
                    dataType:"json",
                    data:datajson
                })
                promise.done(successDelete);
                promise.fail(restFail());
            }
            function successDelete(data){
                alert("성공했습니다")
                var event=calendar.getEventById(data.result);
                event.remove();
                $("#viewCalRest").hide();
            }


            function openRestViewCal(){ //일정리스트 open/reopen 시 메소드
                var promise=$.ajax({
                    type:"GET",
                    url:"/CalRestView",
                    dataType:"json",
                })
                promise.done(viewSuccess);
                promise.fail(restFail);
            }

            function viewSuccess(data){
                var arr=data.lists;
                console.log(data);
                console.log(data.lists);
                var modalBody =$("#restViewModalBody");
                arr.forEach(function(item){
                    console.log(item);
                    var field='<div class="form-group">'+
                        '<label for="update_'+item.id+'">'+item.title+'</label>'+
                        '<input type="button" class="btn btn-primary" value="수정" id="update_'+item.id+'"' +
                        'onclick="openRestMakeCalUpdate(this)" >' +
                        '<input type="button" class="btn btn-primary" value="제거" id="delete_'+item.id+'"' +
                        'onclick="calDeleteRest(this)" >' +
                        '</div>';

                    $(modalBody).append(field);
                })
                $("#viewCalRest").show();
            }

            function restFail(){
                alert("실패하였습니다.")
            }
            function insertSuccess(data){//일정 작성/수정 성공시 메소드
                alert(data.message);
                closeRestMakeCal();
                openRestViewCal();
                if(isinsert){
                    calendar.addEvent(
                        {
                            'id':data.result
                            ,'title':datajson.title
                            , 'start':datajson.startdate
                            , 'end':datajson.enddate});
                }else{
                    var event=calendar.getEventById(data.result);
                    event.setStart(datajson.startdate);
                    event.setEnd(datajson.enddate);
                    event.setProp('title',datajson.title);
                }
            }

        </script>
        <script type="text/javascript" src="/resources/bj/calbj.js"></script>
        <script type="text/javascript">
            var calendar;
            document.addEventListener('DOMContentLoaded', function () {
                var calendarEl = document.getElementById('calendar');
                calendar = new FullCalendar.Calendar(calendarEl, {
                    plugins: ['interaction', 'dayGrid'],
                    defaultView: 'dayGridMonth',
                    defaultDate: new Date(),
                    header: {left: 'prev,next today', center: 'title', right: ''},
                    locale: 'ko',
                    selectable: 'true',
                    events: [
                    <#if calendarlist??>
                        <#list calendarlist as item>
                            <#if item??>
                                {
                                    id:'${item.id}',
                                    title: '${item.title}',
                                    start: '${item.startdate}',
                                    end: '${item.enddate}',
                                },
                            </#if>
                        </#list>
                    </#if>
                        <#if othercallist??>
                            <#list othercallist as item>
                                <#if item??>
                                    {
                                        id: '-1',
                                        title: '${item.title}',
                                        start: '${item.startdate}',
                                        end: '${item.enddate}',
                                        backgroundColor : "#AAFFA3",
                                        textColor : "#484848"
                                    },
                                </#if>
                            </#list>
                        </#if>
                    ],
                    eventClick:function (event) {
                        var eventid=event.event.id;
                    },
                    dateClick: function(info) {
                        openRestViewCal();
                    },

                });
                calendar.render();
            });
        </script>



        <title>My Calendar</title>
    </head>
    <body>

        <div class="frame">
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#viewCal">일정정리</button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#allowList">일정공유</button>
        <button type="button" class="btn btn-dark" onclick="logout()">로그아웃</button>
        <div id='calendar'></div>
        </div>


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
                                        <input type="button" class="btn btn-primary" value="제거" id="delete_${item.id}"
                                               onclick="calDelete(this)" >
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
        <div class="modal" id="viewCalRest">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->

                    <div class="modal-body" id="restViewModalBody">
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" onclick="openRestMakeCalInsert()">일정 작성</button>
                        <button type="button" class="btn btn-danger" onclick="closeRestViewCal()">닫기</button>
                    </div>

                </div>
            </div>
        </div>
        <div class="modal" id="makeCalRest">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <input type="hidden" name="id" value="" id="idUpdateRest">
                                <label for="calTitle">일정 제목:</label>
                                <input name="title" type="text" class="form-control" placeholder="일정의 제목을 적어주세요!" id="calTitleRest">
                            </div>
                            <div class="form-group">
                                <label for="calComment">일정 내용:</label>
                                <input name="content" type="text" class="form-control" placeholder="무슨 일정인지 적어주세요!" id="calContentRest">
                            </div>

                            <div class="form-group">
                                <label for="calStartDate">일정 시작 날짜</label>
                                <input name ="startdate" type="date" class="form-control" placeholder="날짜" id="calStartDateRest">
                            </div>
                            <div class="form-group">
                                <label for="calStartTime">일정 시작 시간</label>
                                <input name ="starttime" type="time" class="form-control" placeholder="시간" id="calStartTimeRest">
                            </div>

                            <div class="form-group">
                                <label for="calEndDate">일정 끝 날짜</label>
                                <input name ="enddate" type="date" class="form-control" placeholder="날짜" id="calEndDateRest">
                            </div>
                            <div class="form-group">
                                <label for="calEndTime">일정 끝 시간</label>
                                <input name ="endtime" type="time" class="form-control" placeholder="시간" id="calEndTimeRest">
                            </div>

                            <button type="button" value="일정 작성" class="btn btn-primary btn-block" onclick="makeCalRest()"></button>
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" onclick="closeRestMakeCal()">Close</button>
                    </div>

                </div>
            </div>
        </div>
        <div class="modal" id="allowList">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->

                    <div class="modal-body">
                        <#if waitallowlist??>
                            <#list waitallowlist as item>
                                <#if item??>
                                    <div class="form-group">
                                        <label for="${item.username}">${item.username}</label>
                                        <input type="button" class="btn btn-primary" value="허락" id="${item.username}" onclick="allow(this)">
                                        <input type="button" class="btn btn-primary" value="거절" id="${item.username}" onclick="kick(this)">
                                    </div>
                                </#if>
                            </#list>
                        </#if>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#makeAllow">공유 요청</button>
                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                    </div>

                </div>
            </div>
        </div>
        <div class="modal" id="makeAllow">
            <div class="modal-dialog">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        일정보기
                    </div>
                    <!-- Modal body -->

                    <div class="modal-body">
                        <form method="POST" action="/allow">
                            <div class="form-group">
                                <label for="allowId">요청ID</label>
                                <input name ="name" type="text" class="form-control" placeholder="Id를 입력해주세요" id="allowId">
                            </div>
                            <input type="submit" value="공유 요청" class="btn btn-primary btn-block">
                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">닫기</button>
                    </div>

                </div>
            </div>
        </div>

    </body>


</html>