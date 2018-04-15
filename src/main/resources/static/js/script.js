/* bind the on-change event for the input element (triggered when a file
 is chosen) */
$(document).ready(function() {
    $("#upload-file").on("change", uploadFile);

});

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

    viewSheet(res.responseText);

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
