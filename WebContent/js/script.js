function executeScript() {
	$('#btnExecute').click( function() {
		$('#btnExecute').prop('disabled', true);
		$('#results').html('<div class="loader" style="margin-top: 50px; margin: auto;"></div>');
		var projectName = $('#txtProjectName').val();
		var qaName = $('#txtQAName').val();
		var ip = $('#txtIP').val();
		var formData = new FormData();
		formData.append('file', $('#fileScript')[0].files[0]); 
		formData.append('projectName', projectName);
		$.ajax({
			url: contextPath + 'MainController?action=execute&projectName=' + projectName + '&qaName=' + qaName + '&ipaddress=' + ip,
			method: 'POST',
			data :	formData,
			async: true,
			contentType: false,
		    processData: false,
		    success: function(result){
				$('#results').html(result);
//				$('#pdfContent').append('<object id="pdf" data="' 
//											+ Config.server + "MainController.json?pdfPath=" + this.pathToPdfFile 
//											+ '" type="application/pdf" width="600" height="800"></object>');
//				$('#pdfContent').html(response);
				$('#btnExecute').prop('disabled', false);
			}
		});
	});
};

//function downloadFiles() {
//	$('#btnDownload').click( function() {
//		window.alert("download");
//		var folderName = $('#folderName').val();
//		$.ajax({
//			url: contextPath + 'MainController?download=all',
//			method: 'GET',
//			data :	{ folderName	:	foldername},
//		    success: function(result){
//				$('#message').html(result);
//			}
//		});
//		
//	});
//};