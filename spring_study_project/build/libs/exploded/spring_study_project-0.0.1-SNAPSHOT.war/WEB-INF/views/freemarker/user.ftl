<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/resources/core/main.css"/>
        <link rel="stylesheet" type="text/css" href="/resources/daygrid/main.css"/>
        <title>freemarker test</title>
    </head>
    <body>
        <h1>hello world</h1>
        <h1>${user.id}</h1>
        <div class="box">
            <div id='calendar'></div>
        </div>
    </body>
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
                dateClick(info) {
                    alert(info.date)
                }
            });
            calendar.render();
        });
    </script>
</html>