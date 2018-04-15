/* bind the on-change event for the input element (triggered when a file
 is chosen) */
var headerLength = 0;
$(document).ready(function() {
    $("#upload-file").on("change", uploadFile);

    //After we have received the headers from the server
    var i = 0;
   $('.btn-add').click(function () {
       if(i<headerLength-1) {
           $('.criteria-percent:first-child').clone().appendTo('.select-box');
           i = i + 1;
       }
       else{
           $('.btn-add').hide();
       }
   });
    sendCriteria();
});

/**
 * Send grade distribution criteria via Ajax to the Spring Boot server.
 */
function sendCriteria() {
    
}

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {

    var filename = $('input[type=file]').val().split("\\").pop();
    if(filename != "")
        $("#file-name").text(filename);
    var data = $("#upload-file-form")[0];
    var res = $.ajax({
        url: "/upload",
        type: "POST",
        data: new FormData(data),
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        async: false,
        success: function (data) {
            // Handle upload success
            $("#upload-file-message").text("Upload Successful!");
        },
        error: function (ex) {
            // Handle upload error
            $("#upload-file-message").text("File not supported!");
            console.log("ERROR : ", e);
        }

    });

    //Process the response; extract the url
    var response = JSON.parse(res.responseText);
    viewSheet(response["url"]);
    var headers = response["headers"];
    $('.criteria').empty();
    $.each(headers, function(i,p){
       $('.criteria').append($('<option></option>').val(p).html(p));
    });
    headerLength = headers.length;

} // function uploadFile
/**
 *View uploaded grade sheet
 */
function viewSheet(viewUrl) {
    $("#view-btn").show();
    $("#view-btn").on("click", function () {
        $(this).attr("href", viewUrl);
    });
}
