

<!DOCTYPE html>
<html>
<head>
	<title>Feed System</title>
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	<link rel="stylesheet" type="text/css" href="css\dashstyle.css">
</head>
<body>


<div class = "main">
<textarea class="yourFeedArea" id = "yourFeed" rows="2" name="comment" 
placeholder = "Hi,What's on your mind?...">
</textarea>
<div class = "bluey">
<input type="submit" id= "FeedSubmit" value= "share">
</div>


	<div class="tab">
  <span class="tablinks" onclick="Feeds(event, 'MyFeeds')" id="defaultOpen">My Feeds</span>
  <span class="tablinks" onclick="Feeds(event, 'AllFeeds')">All Feeds</span>
</div>

<div id="MyFeeds" class="tabcontent">
</div>

<div id="AllFeeds" class="tabcontent">

</div>
</div>
<div class = "sideBar">

<h3 id = sidehead>All Users</h3>
<div class = "AllUsers" id="users"></div>
  
</div>
	
<script src="js/FeedResponse.js"></script>
</body>
</html>