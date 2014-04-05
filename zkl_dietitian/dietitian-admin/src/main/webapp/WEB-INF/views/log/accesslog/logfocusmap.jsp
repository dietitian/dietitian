<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网络访问量关注情况</title>
<script src="${pageContext.request.contextPath}/static/bootstrap/js/chart.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/static/datePicker/WdatePicker.js"></script>

<link href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<script type="text/javascript">
 function getmap(type){
	var startTime = $("#"+type+"start").val();
	var endTime = $("#"+type+"end").val();
	var las =null;
	var Ins =null;
	var lasT =null;
	var InsT =null;
	var num =null;
	var i = 0;
	$.get("${pageContext.request.contextPath}/accesslog/getmaplist/?type="+type+"&startTime=" + startTime+"&endTime="+endTime, function(data) {
		
			las = data.las;
			Ins = data.Ins;
			lasT = data.lasT;
			InsT = data.InsT;
			num = data.num;
			//alert(num);
			//alert(las);
			var strla = new Array();
			var strIn = new Array();
			var strlaT = new Array();
			var strInT = new Array();
			strla = las.split(",");
			strIn = Ins.split(",");
			strlaT = lasT.split(",");
			strInT = InsT.split(",");
			//alert(strla[0]+" "+strIn[0]);
		    setmap(type,strla,strIn,strlaT,strInT);
	});
	
}
function setmap(type,StringLabels,intdatesets,StringLabelsT,intdatesetsT){
	var lineChartData = {
			labels : StringLabels,
			datasets : [
				{
					fillColor : "rgba(20,20,20,0.5)",
					strokeColor : "rgba(20,20,20,1)",
					pointColor : "rgba(20,20,20,1)",
					pointStrokeColor : "#fff",
					data : intdatesets
				},
				{
					fillColor : "rgba(151,187,205,0.5)",
					strokeColor : "rgba(151,187,205,1)",
					pointColor : "rgba(151,187,205,1)",
					pointStrokeColor : "#fff",
					data : intdatesetsT
				}
			]
			
		}
		var myLine = new Chart(document.getElementById(type).getContext("2d")).Line(lineChartData);

	
} 

</script>

</head>
<body>
<div class="container-fluid">

	<div id="legend">
          <legend><font color="blue">访问日志&gt;&gt;统计分析</font></legend>
        </div>
	<div class="row-fluid">
		<div class="span12">
			<div class="tabbable" id="tabs-422732">
				<ul class="nav nav-tabs">
					<li class="active">
						<a href="#panel-438847" data-toggle="tab">天</a>
					</li>
					<li>
						<a href="#panel-214523" data-toggle="tab">月</a>
					</li>
					<li>
						<a href="#panel-214542" data-toggle="tab">年</a>
					</li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-438847">
						<div class="row-fluid">
							<div class="span12">
								<form class="form-search">
									开始时间：
									  <input type="text" class="Wdate" id="daystart" style="height: 30px"
										onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'daystart\',{M:-12})||$dp.$DV(\'2040-1-1\',{M:-12})}'})"/>
									结束时间：<input type="text" class="Wdate" id="dayend" style="height: 30px"
										onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dayend\',{M:12});}',maxDate:'2040-1-1'})"/>
										
								
								
									<!-- 开始时间：<input class="form_day" type="text"  id="daystart" data-date-format="yyyy-mm-dd" readonly="readonly" />
									结束时间：<input class="form_day" type="text"	id="dayend" data-date-format="yyyy-mm-dd"  readonly="readonly"  /> --> 
								
									 <button class="button button-rounded" type="button" onClick="return getmap('day')">
				                         <i class="icon-search"></i> 查找
				                     </button>   
								</form>
								<!-- <script type="text/javascript">
								$('.form_day').datetimepicker({
							        language:  'zh-CN',
							        yeartart: 1,
							        todayBtn:  1,
									autoclose: 1,
									todayHighlight: 1,
									startView: 2,
									minView: 2,
									forceParse: 0
							    });
								</script> -->
							
								<canvas id="day" height="450" width="1000"></canvas>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel-214523">
							<div class="row-fluid">
							<div class="span12">
							<form class="form-search">
								开始时间：<input type="text" class="Wdate" id="monthstart" style="height: 30px"
										onFocus="WdatePicker({dateFmt:'yyyy-MM',maxDate:'#F{$dp.$D(\'monthstart\',{M:-1})||$dp.$DV(\'2040-1-1\',{M:-1})}'})"/>
								结束时间：<input type="text" class="Wdate" id="monthend" style="height: 30px"
										onFocus="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'monthend\',{M:1});}',maxDate:'2040-1-1'})"/>
									<!-- 开始时间：<input class="form_month" type="text"  id="monthstart" data-date-format="yyyy-mm-dd" readonly="readonly" />
									结束时间：<input class="form_month" type="text"	id="monthend" data-date-format="yyyy-mm-dd"  readonly="readonly"  />  -->
									 
									 <button class="button button-rounded" type="button" onClick="return getmap('month')">
				                         <i class="icon-search"></i> 查找
				                     </button>   
							</form>
							<!-- <script type="text/javascript">
								$('.form_month').datetimepicker({
							        language:  'zh-CN',
							        yeartart: 1,
							        todayBtn:  1,
									autoclose: 1,
									todayHighlight: 1,
									startView: 2,
									minView: 2,
									forceParse: 0
							    });
							</script>	 -->
								<canvas id="month" height="450" width="1000"></canvas>
							</div>
						</div>
					</div>
					<div class="tab-pane" id="panel-214542">
							<div class="row-fluid">
							<div class="span12">
								<form class="form-search">
										开始时间：<input type="text" class="Wdate" id="yearstart" style="height: 30px"
												onFocus="WdatePicker({dateFmt:'yyyy',maxDate:'#F{$dp.$D(\'yearstart\',{M:-1})||$dp.$DV(\'2040-1-1\',{M:-1})}'})"/>
										结束时间：<input type="text" class="Wdate" id="yearend" style="height: 30px"
										onFocus="WdatePicker({dateFmt:'yyyy',minDate:'#F{$dp.$D(\'yearend\',{M:1});}',maxDate:'2040-1-1'})"/>
										<!-- 开始时间：<input class="form_year" type="text"  id="yearstart" data-date-format="yyyy-mm-dd" readonly="readonly" />
										结束时间：<input class="form_year" type="text"	id="yearend" data-date-format="yyyy-mm-dd"  readonly="readonly"  />  -->
									
										  <button class="button button-rounded" type="button" onClick="return getmap('year')">
				                               <i class="icon-search"></i> 查找
				                          </button> 
										
								</form>
								<!-- <script type="text/javascript">
									$('.form_year').datetimepicker({
								        language:  'zh-CN',
								        yeartart: 1,
								        todayBtn:  1,
										autoclose: 1,
										todayHighlight: 1,
										startView: 2,
										minView: 2,
										forceParse: 0
								    });
								</script> -->
								<canvas id="year" height="450" width="1000"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>