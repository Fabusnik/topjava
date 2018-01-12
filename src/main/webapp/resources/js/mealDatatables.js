var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: $("#filter").serialize(),
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

// $(function () {
//     $('#startDate').datepicker("show");
//     $('#startTime').timepicker();
//     $('#endDate').datepicker({
//         locale: 'ru'
//     });
//     $('#endTime').timepicker();
// });



$(function () {

    $(function () {
        $('#startDate').datepicker("show");
        $('#startTime').timepicker();
        $('#endDate').datepicker({
            locale: 'ru'
        });
        $('#endTime').timepicker();
    });

    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
                // "render": function (date, type, row) {
                //     if (type === "display") {
                //         var data = new Date(date);
                //         var option = {hour: 'numeric', minute: 'numeric'};
                //         return data.toLocaleDateString() + " " + data.toLocaleTimeString('en-GB',option);
                //     }
                //     return date;
                // }
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false,
                "render": renderEditBtn
            },
            {
                "defaultContent": "Delete",
                "orderable": false,
                "render": renderDeleteBtn
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (data.exceed) {
                $(row).addClass("exceeded");
            } else {
                $(row).addClass("normal");
            }
        },
        "initComplete": makeEditable
    });
});