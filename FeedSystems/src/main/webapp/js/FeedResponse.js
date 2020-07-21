$(".mainTwo").hide();
$(".home").hide();


function Feeds(evt, typeOfFeeds) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(typeOfFeeds).style.display = "block";
  evt.currentTarget.className += " active";
}

// Get the element with id="defaultOpen" and click on it
document.getElementById("defaultOpen").click();


$(function () {
    $('#FeedSubmit').on('click', function () {
    	
        $.ajax({
        	type: 'POST',
            url: '/feedUpdate',
            data :{ feedText : $('#yourFeed').val()},
            success: function (data, status, xhr) {
            	$('#yourFeed').val('');
            	
            	$('.tabcontent').prepend('<section class= "dynamicFeeds"><h3>'+data.lastfeed.name+'</h3><h6 style="color:#b3b3b3">'+data.date+' at '+data.time+'</h6><p>'+data.lastfeed.feed.value+'</p></section>')
            },
            dataType : 'json'
        });
    });
});




$.ajax({
	type: 'GET',
    url: '/GetListOfMyFeeds',
    success: function (data, status, xhr) {

    for(let i = 0 ; i <  data.myfeeds.length;i++)
    {
    	$('#MyFeeds').append('<section class= "dynamicFeeds"><h3>'+data.myfeeds[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.myfeeds[i].feed.value+'</p></section>')
    }
    	
    	
    	
    },
    dataType : 'json'
});

$.ajax({
	type: 'GET',
    url: '/GetListOfAllFeeds',
    success: function (data, status, xhr) {

    for(let i = 0 ; i <  data.allfeeds.length;i++)
    {
    	$('#AllFeeds').append('<section class= "dynamicFeeds"><h3>'+data.allfeeds[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.allfeeds[i].feed.value+'</p></section>')
    }
    	
    	
    	
    },
    dataType : 'json'
});


$.ajax({
	type: 'GET',
    url: '/GetAllUsers',
    success: function (data, status, xhr) {

    	
    for(let i = 0 ; i <  data.allusers.length;i++)
    {
    	$('.AllUsers').append('<span class = "dynamicMailIds">'+data.allusers[i].email+'</span>')
    }
    	
    },
    dataType : 'json'
});

$(function () {
    $('.AllUsers').on('click','.dynamicMailIds',function () {
    	
    	let email = $(this).closest('span').text();
    	
    	console.log(email);
    	$(".main").hide();
    	
    	$(".mainTwo").show();
		$(".home").show();
    	
    	$.ajax({
    			type: 'POST',
    			url: '/GetFeedsByMail',
    			data :{ email : email},
        success: function (data, status, xhr) {
        	
        	$(".mainTwo").empty();
        	
        	for(let i = 0 ; i <  data.fetchfeedsbymail.length;i++)
            {
            	$('.mainTwo').append('<section class= "dynamicFeeds"><h3>'+data.fetchfeedsbymail[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.fetchfeedsbymail[i].feed.value+'</p></section>')
            }
        	
        },
        dataType : 'json'
    });
    	
    });
});



$(function () {
    $('.home').on('click', function () {
    	
    	$(".main").show();
    	
    	$(".mainTwo").hide();
		$(".home").hide();
    	
    });
});


$(function () {
    $('#logout').on('click', function () {
    	
        $.ajax({
        	type: 'GET',
            url: '/logout',

            success: function (data, status, xhr) {
            	
            	if(data.value == 'true')
            	{
            		window.location.href = '/';
    			}
    			
            },
            dataType : 'json'
        });
    });
});
