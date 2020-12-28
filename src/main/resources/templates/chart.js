    /*<![CDATA[*/

    var habits = [
        {name: "TV", data: 5},
        {name: "Phone", data: 8},
        {name: "Procrastinating", data: 7},
    ];



    var seriesData = {
        list: []
    };

    for (var i in habits) {
        var habit = habits[i];
        seriesData.list.push({
            "name" : habit.name,
            "data" : habit.data
        });
    }

    $(function(){
    Highcharts.chart('container', {
        chart: {
            type: 'line'
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        xAxis: {
             type: 'datetime'
        },
        yAxis: {
            min: 0,
            max: 10,
            title: {
                text: 'Habit Severity'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y}/10</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },

        legend: {
        layout: 'vertical',
        align: 'right',
        verticalAlign: 'middle'

      },

        series: [seriesData]

        });
    });
    /*]]>*/