<html>
<head>
    <link rel="stylesheet" type="text/css" href="/images/core/main.css"/>
    <link rel="stylesheet" type="text/css" href="/images/daygrid/main.css"/>

    <link rel="stylesheet" type="text/css" href="/images/bj/bj.css"/>
    <title>freemarker test</title>
</head>
<body>
<h1>hello world</h1>
<h1>${user.id}</h1>
<div class="box">
    <div id='calendar'></div>
</div>




</body>
<script type="text/javascript" src="/images/core/main.js"></script>
<script type="text/javascript" src="/images/interaction/main.js"></script>
<script type="text/javascript" src="/images/daygrid/main.js"></script>
<script type="text/javascript">
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            plugins: ['interaction', 'dayGrid'],
            defaultView: 'dayGridMonth',
            defaultDate: new Date(),
            header: {left: 'prev,next today', center: 'title', right: ''},
            events:[
                {
                    title:'test',
                    start:'2020-01-28',
                    end:'2020-01-29'
                }
            ],
        });
        calendar.render();
    });
</script>
</html>