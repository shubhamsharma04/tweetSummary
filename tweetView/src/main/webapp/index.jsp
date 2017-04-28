<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
.highcharts-tooltip span {
        width:200px;
        white-space:normal !important;
    }
</style>
<title>Awesome HashTag Analysis Home Page</title>
 <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no' />
  <link rel="stylesheet" type="text/css" href="assets/lib/bootstrap/dist/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="assets/css/keen-dashboards.css" />
  <script type="text/javascript" src="assets/lib/jquery/dist/jquery.js" ></script>
  <script src="http://code.highcharts.com/highcharts.js"></script>
  <script>
  $(function () {
	  $.ajax({url: "graph0", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col1',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 hashtags  - Festival/Holiday'
						},
						xAxis : {
							title : {
								text : 'hashtag'
							},
							categories : tweet_list
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Hashtag Count'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return  this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Most Frequent hashtags',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph1", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col2',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Most Retweeted Tweets - Festival/Holiday'
						},
						xAxis : {
							title : {
								text : 'tweet'
							},
							categories : [ 'Tweet1', 'Tweet2',
									'Tweet3', 'Tweet4', 'Tweet5' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : 'No of Retweets'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Retweet Count',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph2", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col3',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 hashtags - Simple Promotion'
						},
						xAxis : {
							title : {
								text : 'hashtag'
							},
							categories : tweet_list
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Hashtag Count'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return  this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Most Frequent hashtags',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph3", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col4',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Most Retweeted Tweets - Simple Promotion'
						},
						xAxis : {
							title : {
								text : 'tweet'
							},
							categories : [ 'Tweet1', 'Tweet2',
									'Tweet3', 'Tweet4', 'Tweet5' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : 'No of Retweets'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Retweet Count',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	 
	  $.ajax({url: "graph4", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col5',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Hashtags - Aggresive Promotion'
						},
						xAxis : {
							title : {
								text : 'hashtag'
							},
							categories : tweet_list
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Hashtag Count'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return  this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Most Frequnt hashtags',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph5", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col6',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Most Retweeted Tweets - Aggresive Promotion'
						},
						xAxis : {
							title : {
								text : 'tweet'
							},
							categories : [ 'Tweet1', 'Tweet2',
									'Tweet3', 'Tweet4', 'Tweet5' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : 'No of Retweets'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Retweet Count',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	 
	  $.ajax({url: "graph6", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col7',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 hashtags - Charity'
						},
						xAxis : {
							title : {
								text : 'hashtag'
							},
							categories : tweet_list
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Hashtag Count'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return  this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Most Frequent Hashtags',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph7", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col8',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Most Retweeted Tweets - Charity'
						},
						xAxis : {
							title : {
								text : 'tweetid'
							},
							categories : [ 'Tweet1', 'Tweet2',
									'Tweet3', 'Tweet4', 'Tweet5' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : 'No of Retweets'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Retweet Count',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	 
	  $.ajax({url: "graph8", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col9',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Hashtags - Gifts'
						},
						xAxis : {
							title : {
								text : 'hashtag'
							},
							categories : tweet_list
						},
						yAxis : {
							min : 0,
							title : {
								text : 'Hashtag Count'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							formatter : function() {
								return  this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Most Frequent Hashtags',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph9", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'row3-col10',
					{
						chart : {
							type : 'column'
						},
						title : {
							text : 'Top 5 Most Retweeted Tweets - Gifts'
						},
						xAxis : {
							title : {
								text : 'tweetid'
							},
							categories : [ 'Tweet1', 'Tweet2',
									'Tweet3', 'Tweet4', 'Tweet5' ]
						},
						yAxis : {
							min : 0,
							title : {
								text : 'No of Retweets'
							},
						/*,
						labels: {
						formatter: function () {
						return this.value / 1000000000 + 'B';
						}
						}*/
						},
						exporting : {
							enabled : false
						},
						tooltip : {
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.series.options.tweet[this.point.index];
							}

						},
						series : [ {
							name : 'Retweet Count',
							data : value_list,
							tweet : tweet_list
						} ]
					});
		   }
		});
	  $.ajax({url: "graph11", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
			var summary_list=[];
			
			var s1="it's beginning to feel like An important safety tip 4 all of us this Christmas thank u Bravo .Christmas show was wonderful!!Merry Christmas everyone Christmas And the Christmas lights are on!!"
			var s2="Expectation vs realty  MAKE YOUR XMAS WISH xmas holliday edition christmas happynewyear… Donate toys todayHAPPY  MAKE YOUR XMAS WISH XMAS christmas happynewyear santaclausCreating a cute  MAKE YOUR XMAS WISH santa outfit for a cute xmas puppy christmas"
			var s3="10 DIY Ugly Christmas Sweater Crafts  christmas family2 DAYS LEFT TO ORDER PRINTS IN EUROPE TO GET GUARANTEED DELIVERY IN TIME FOR Christmas !!!!40% Off USe Code CYBERMONRTRN Pink Rose Photo Floral Cool Ceramic Ornament sale christmas"
			var s4="Row of model homes becomes winter wonderland for charity  via @ABC13Houston Amazing.Staff helping with donations for christmas Adopt a Senior - Teaching Kids Charity & Kindness over the Holidays -->  charity Christmas kindness"
			var s5="not even worth that anymore but people will pay the prices for their children for Christmas and.would love to win for my family, thanks for the chance to win Christmas.Fantastic prize just in time for Christmas fingersandtoescrossed for a win"
			
			summary_list.push(s1);
			summary_list.push(s2);
			summary_list.push(s3);
			summary_list.push(s4);
			summary_list.push(s5);
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'grid-1-1',
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : null,
							plotShadow : false,
							type : 'pie'
						},
						title : {
							text : 'Sub Topic composition for Christmas Tweets'
						},
						tooltip : {
							// pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
							shared: false,
    						useHTML: true,
							formatter : function() {
								return this.point.summary;
							}
						},
						plotOptions : {
							pie : {
								allowPointSelect : true,
								cursor : 'pointer',
								dataLabels : {
									enabled : true,
									format : '<b>{point.name}</b>: {point.percentage:.1f} %',
									style : {
										color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
												|| 'black'
									}
								}
							}
						},
						series : [ {
							name : 'Topics',
							colorByPoint : true,
							data : [ {
								name : tweet_list[0],
								y : value_list[0],
								summary:summary_list[0]
							}, {
								name : tweet_list[1],
								y : value_list[1],
								sliced: true,
								selected: true,
								summary:summary_list[1]
							}, {
								name : tweet_list[2],
								y : value_list[2],
								summary:summary_list[2]
							}, {
								name : tweet_list[3],
								y : value_list[3],
								summary:summary_list[3]
							}, {
								name : tweet_list[4],
								y : value_list[4],
								summary:summary_list[4]
							}]
						} ]
					});
		   }
		});
	  $.ajax({url: "graph10", success: function(result){
		  	var value_list=[];
			var tweet_list=[];
	      
			for ( var i in result.jsonList) {
				var id = result.jsonList[i].key;
				var name = result.jsonList[i].value;
				tweet_list.push(id)
				value_list.push(name)
				
			 }
			Highcharts
			.chart(
					'grid-1-2',
					{
						chart : {
							plotBackgroundColor : null,
							plotBorderWidth : 0,
							plotShadow : false
						},
						title : {
							text : 'Top  10 Hashtags<br>overall',
							align : 'center',
							verticalAlign : 'middle',
							y : 40
						},
						tooltip : {
							pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'

						},
						plotOptions : {
							pie : {
								dataLabels : {
									enabled : true,
									distance : -50,
									style : {
										fontWeight : 'bold',
										color : 'white'
									}
								},
								startAngle : -90,
								endAngle : 90,
								center : [ '50%', '75%' ]
							}
						},
						series : [ {
							type : 'pie',
							name : 'top 10 hashtags share',
							innerSize : '50%',
							data : [
									[ tweet_list[0], value_list[0] ],
									[ tweet_list[1], value_list[1] ],
									[ tweet_list[2], value_list[2] ],
									[ tweet_list[3], value_list[3] ],
									[ tweet_list[4], value_list[4] ],
									[ tweet_list[5], value_list[5] ],
									[ tweet_list[6], value_list[6] ],
									[ tweet_list[7], value_list[7] ],
									[ tweet_list[8], value_list[8] ],
									[ tweet_list[9], value_list[9] ],
									{
										name : 'Proprietary or Undetectable',
										y : 0.2,
										dataLabels : {
											enabled : false
										}
									} ]
						} ]
					});
	
		   }
		});

	 
	  Highcharts.chart('row3-col20', {
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: 'Sentiment Analysis For Topics'
	        },
	        xAxis: {
	            categories: ['Festival/Holiday', 'Simple Promotion', 'Aggressive Promotion', 'Charity', 'Gifts']
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: 'Sentiment Percentage'
	            },
	            stackLabels: {
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        legend: {
	            align: 'right',
	            x: -30,
	            verticalAlign: 'top',
	            y: 25,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
	            borderColor: '#CCC',
	            borderWidth: 1,
	            shadow: false
	        },
	        tooltip: {
	            headerFormat: '<b>{point.x}</b><br/>',
	            pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
	        },
	        plotOptions: {
	            column: {
	                stacking: 'normal',
	                dataLabels: {
	                    enabled: true,
	                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
	                }
	            }
	        },
	        series: [{
	            name: 'Positive',
	            data: [77, 45, 51, 61, 58]
	        }, {
	            name: 'Negative',
	            data: [4, 1, 5, 6, 8]
	        }, {
	            name: 'Neutral',
	            data: [19, 54, 44, 33, 34]
	        }]
	    });
				
				
			});
		</script>
</head>
<body class="application">

  <div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <!--  <a class="navbar-brand" href="../">
          <span class="glyphicon glyphicon-chevron-left"></span>
          </a>
        <a class="navbar-brand" href="./">Layouts &raquo; Hero Thirds</a> -->
      </div>
      <div class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-left"><li><a href="https://keen.io/team">Many Thanks to keen.io team</a></li></ul>
       <!--  <ul class="nav navbar-nav navbar-left">
          <li><a href="https://keen.io">Home</a></li>
          <li><a href="https://keen.io/team">Team</a></li>
          <li><a href="https://github.com/keenlabs/dashboards/tree/gh-pages/layouts/hero-thirds">Source</a></li>
          <li><a href="https://groups.google.com/forum/#!forum/keen-io-devs">Community</a></li><li><a href="http://stackoverflow.com/questions/tagged/keen-io?sort=newest&pageSize=15">Technical Support</a></li>
        </ul> -->
      </div>
    </div>
  </div>

  <div class="container-fluid">

    <div class="row">
      <div class="col-sm-6">
        <div class="chart-wrapper">
          <div class="chart-title">
            Composition
          </div>
          <div class="chart-stage">
            <div id="grid-1-1">
              <!-- <img data-src="holder.js/100%x240/white/text:#grid-1-1"> -->
            </div>
          </div>
          <div class="chart-notes">
            This chart depicts the top 10 subtopics present in Xmas tweets
          </div>
        </div>
      </div>
      <div class="col-sm-6">
        <div class="chart-wrapper">
          <div class="chart-title">
            Top 10 hashtags overall
          </div>
          <div class="chart-stage" id="grid-1-2">
            <!-- <img data-src="holder.js/100%x240/white"> -->
          </div>
          <div class="chart-notes">
            These graph depicts the top 10 Hashtags across all subtopic
          </div>
        </div>
      </div>
    </div>

    <div class="row">
      <div class="col-sm-6 col-md-4" id="row3-col1">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col2">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col3">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
<!-- end of three -->
      <div class="col-sm-6 col-md-4" id="row3-col4">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col5">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col6">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
    </div>
     <div class="col-sm-6 col-md-4" id="row3-col7">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col8">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
      <div class="col-sm-6 col-md-4" id="row3-col9">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
       <div class="col-sm-6 col-md-4" id="row3-col10">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
      </div>
    	 <div class="col-sm-8 col-md-8" id="row3-col20">
        <div class="chart-wrapper">
          <div class="chart-title">
            Cell Title
          </div>
          <div class="chart-stage">
            <img data-src="holder.js/100%x120/white">
          </div>
          <div class="chart-notes">
            Notes about this chart
          </div>
        </div>
        
      </div>
   
    </div>




    <hr>

    <p class="small text-muted">Built with &#9829; by <a href="https://keen.io">Keen IO</a></p>

  </div>

 
	
	
	<script type="text/javascript" src="assets/lib/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript" src="assets/lib/bootstrap/dist/js/bootstrap.min.js"></script>

  <script type="text/javascript" src="assets/lib/holderjs/holder.js"></script>
  <script>
    Holder.add_theme("white", { background:"#fff", foreground:"#a7a7a7", size:10 });
  </script>

  <script type="text/javascript" src="assets/lib/keen-js/dist/keen.min.js"></script>
  <script type="text/javascript" src="assets/js/meta.js"></script>
</body>
</html>