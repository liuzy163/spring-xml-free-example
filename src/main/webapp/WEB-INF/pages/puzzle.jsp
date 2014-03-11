<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Welcome to Zhiyong's Puzzle Solution</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
     $.validator.addMethod("integerList", function (value, element) {
    	return this.optional(element) || /^(\d*;?)*$/.test(value);
	 });
	 $.validator.addMethod("oneInteger", function (value, element) {
    	return this.optional(element) || /(\d+)/.test(value);
	 });

     $('#newProblemForm').validate({
        rules: {
          numbers: {
            required: true,
            integerList: true
          },
          order: {
            required: true,
            oneInteger: true
          },
          complexity: {
          	required: true
          }
        },
        messages: {
    		numbers: 'Please enter semicolon delimited integers',
    		order: 'Please enter one integer'
    	}
      });
        
      $('#newProblemForm').submit(function(event) {
    	  var numbers = $('#numbers').val();
    	  var order = $('#order').val();
    	  var complexity = $('#complexity').val();  
    	  var json = { "numbers" : numbers, "order" : order, "complexity": complexity};
    	  
        $.ajax({
        	url: $("#newProblemForm").attr( "action"),
        	data: JSON.stringify(json),
        	type: "POST",
        	dataType: 'json', 
        	contentType: 'application/json',
    		mimeType: 'application/json',
        	
        	success: function(solution) {       		
        		var respContent = "";        		
		  		respContent += "<span class='success'>";
		  		respContent += "The result list is " + solution.numbers + " \n ";
		  		respContent += "And the "+order+ "th largest number is " + solution.order +  "</span>";
        		
        		$("#puzzleResult").html(respContent);   		
        	},
        	error: function(jqXHR, textStatus, errorThrown)
        	{
        		var errorMessage = $.parseJSON(jqXHR.responseText).errorMessage;
            	alert(errorMessage);
            }
        	
        });
         
        event.preventDefault();
      });
       
    });   
  </script>
  
  <style>
  	.success{
  		
  	}
  	.problemform{
		width: 600px;
		font-size: 1.0em;
		color: #333;
	}
	.problemform label {
		color: #333;
	}
	.problemform label.error, label.error {
		color: red;
		font-style: italic
	}
	.problemform input {	border: 1px solid black; }
	.problemform input:focus { border: 1px dotted black; }
	.problemform input.error { border: 1px dotted red; }
  </style>
  
</head>
<body>

<h2>Enter a puzzle</h2>
<form:form class= "problemform" id="newProblemForm" method="POST" action="/solution">
   <table>
    <tr>
        <td><form:label path="numbers">Numbers</form:label></td>
        <td><form:input path="numbers" /></td>
    </tr>
    <tr>
        <td><form:label path="order">Index</form:label></td>
        <td><form:input path="order" /></td>
    </tr>
 	<tr>
		<td>Please select a complexity:</td>
		<td>
			<form:select path="complexity">
				<form:option value="nlogn">O(N*log(N))</form:option>
				<form:option selected="selected" value="n">O(N)</form:option>
				<form:option value="leastmemory">Least memory</form:option>
			</form:select>
		</td>
	</tr>
    <tr>
        <td>
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
<div id="puzzleResult"></div>
</body>
</html>