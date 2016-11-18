<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div style="width: 108px;">
	<input type="hidden" id="folderName" value="${ folderName }">
	<a href="ResultController?download=all&folderName=${ folderName }" class="btn btn-default" id="btnDownload" onClick="setTimeout('window.location.reload()',300);" >Download All</a>
</div>
<div id="message">
	
</div>
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
  <div class="panel panel-success">
    <div class="panel-heading" role="tab" id="headingOne" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Generated Results
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
		<div class="panel-body" id="resultContent">
			<%-- <c:forEach var="resultFile" items="${ resultFiles }">
				<c:if test="${ resultFile.name != 'Chrome' && resultFile.name != 'Firefox'  }">
					<c:choose>
						<c:when test="${ fn:containsIgnoreCase( resultFile.absolutePath , 'Chrome')  }">
							Chrome<br/>
							${ resultFile.name }<br/>
						</c:when>
						<c:when test="${ fn:containsIgnoreCase( resultFile.absolutePath , 'Firefox')  }">
							Firefox<br/>
							${ resultFile.name }<br/>
						</c:when>
						<c:otherwise>
							${ resultFile.name }<br/>
						</c:otherwise>
					</c:choose>
				</c:if>
			</c:forEach> --%>
			<h5><strong>Files</strong></h5>
				<c:forEach var="resultFile" items="${ resultFiles }">
					<c:if test="${ not fn:containsIgnoreCase( resultFile.absolutePath , 'Chrome') && not fn:containsIgnoreCase( resultFile.absolutePath , 'Firefox')  }">
						<span></span><img alt="..." src="imgs/pdf.png" class="img-responsive"  style="float: left; margin-left: 50px; margin-right:15px;">${ resultFile.name }<br/><br/>
					</c:if>
				</c:forEach>
				
			
			<h5><strong>Chrome Expected Results</strong></h5>
				<c:forEach var="resultChrome" items="${ resultFiles }">
				<c:if test="${ fn:containsIgnoreCase( resultChrome.absolutePath , 'Chrome Expected Results')}">
					<c:choose>
						<c:when test="${ resultChrome.name != 'Chrome Expected Results' }">
							<img alt="..." src="imgs/png.png" class="img-responsive" style="float: left; margin-left: 50px; margin-right:15px;">${ resultChrome.name }<br/></br/>
						</c:when>
					</c:choose>
				</c:if>
				</c:forEach>
			
			
			<h5><strong>Chrome Screenshots</strong></h5>
				<c:forEach var="resultChrome" items="${ resultFiles }">
				<c:if test="${ fn:containsIgnoreCase( resultChrome.absolutePath , 'Chrome Screenshots')}">
					<c:choose>
						<c:when test="${ resultChrome.name != 'Chrome Screenshots' }">
							<img alt="..." src="imgs/png.png" class="img-responsive" style="float: left; margin-left: 50px; margin-right:15px;">${ resultChrome.name }<br/></br/>
						</c:when>
					</c:choose>
				</c:if>
				</c:forEach>
			
			<h5><strong>Firefox Expected Results</strong></h5>
			<c:forEach var="resultFirefox" items="${ resultFiles }">
				<c:if test="${ fn:containsIgnoreCase( resultFirefox.absolutePath , 'Firefox Expected Results')}">
					<c:choose>
						<c:when test="${ resultFirefox.name != 'Firefox Expected Results' }">
							<img alt="..." src="imgs/png.png" class="img-responsive"  style="float: left;margin-left: 50px; margin-right:15px;">${ resultFirefox.name }<br/><br/>
						</c:when>
					</c:choose>
				</c:if>
				</c:forEach>
			
			
			<h5><strong>Firefox Screenshots</strong></h5>
				<c:forEach var="resultFirefox" items="${ resultFiles }">
				<c:if test="${ fn:containsIgnoreCase( resultFirefox.absolutePath , 'Firefox Screenshots')}">
					<c:choose>
						<c:when test="${ resultFirefox.name != 'Firefox Screenshots' }">
							<img alt="..." src="imgs/png.png" class="img-responsive"  style="float: left;margin-left: 50px; margin-right:15px;">${ resultFirefox.name }<br/><br/>
						</c:when>
					</c:choose>
				</c:if>
				</c:forEach>
      </div>
    </div>
  </div>
  <!-- <div class="panel panel-success">
    <div class="panel-heading" role="tab" id="headingTwo" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Screenshots
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
      </div>
    </div>
  </div> -->
</div>
<script type="text/javascript">
$( document ).ready(function() {
	downloadFiles();
	
});
</script>