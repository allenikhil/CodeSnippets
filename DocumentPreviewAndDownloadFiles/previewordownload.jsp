

// For File preview and download don't remove 
	function downloadFile(filePath) {
		$.ajax({
					type : "POST",
					url : "checkingFileExitance.html?CSRFToken="+$("input[name=CSRFToken]").val(),
					data : {
						filePath : filePath
					},
					cache : true,
					success : function(response) {
						if (response == '0') {
							document.messageDashboardBean.hdnFilePath.value = filePath;
							document.messageDashboardBean.method = 'GET';
							document.messageDashboardBean.action = "downloadFile.html";
							window.open('','preview','scrollbars=yes,menubar=yes,height=500,width=1024,resizable=yes,toolbar=no,location=no,status=yes,top=80,left=80');
							document.messageDashboardBean.target = 'preview';
							document.messageDashboardBean.submit();
							document.messageDashboardBean.target = '';
						} else {
							alert("File does not exist!");
						}
					}
				});
	}
	

<td align="center"><a onclick="downloadFile('${tenderMessagesLogList.senderFilePath}')" style="cursor: pointer;">${tenderMessagesLogList.fileName}</a></td> 