document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: ['interaction', 'dayGrid'],
        defaultView: 'dayGridMonth',
        defaultDate: new Date(),
        header: {left: 'prev,next today', center: 'title', right: ''},
        locale: 'ko',
        dateClick(info) {

        }


    });
    calendar.render();
});