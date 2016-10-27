function executeScript() {
	$('#btnExecute').click( function() {
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
			}
		});
		
	});
};

function downloadFiles() {
	var folderName = $('#folderName').val();
	$('#btnDownload').click( function() {
		$.ajax({
			url: contextPath + 'MainController?download=all',
			method: 'POST',
			data :	folderName,
		    success: function(result){
				$('#message').html(result);
			}
		});
		
	});
};