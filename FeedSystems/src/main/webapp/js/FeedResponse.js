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
    	
    	$('#noMyFeeds').remove();
    	$('#noAllFeeds').remove();
    	
        $.ajax({
        	type: 'POST',
            url: '/feedUpdate',
            data :{ feedText : $('#yourFeed').val()},
            success: function (data, status, xhr) {
            	$('#yourFeed').val('');
            	
            	$('.tabcontent').prepend('<section class= "dynamicFeeds"><h3>'+data.lastfeed.name+'</h3><h6 style="color:#b3b3b3">'+data.date+' at '+data.time+'</h6><p>'+data.lastfeed.feed.value+'</p><div class = "hoverOverLike"><abbr class="count'+data.lastfeed.id+'"" title="">'+data.totalLikes+'</abbr><input type = "button" class ="'+data.lastfeed.id+' likeButton" value="Like"></div></section>')
            },
            dataType : 'json'
        });
    });
});




//#AllFeeds
//#MyFeeds
$.ajax({
	type: 'GET',
    url: '/GetListOfMyFeeds',
    success: function (data, status, xhr) {
    	
    	if(data.myfeeds.length == 0)
    	{
    		$('#MyFeeds').append('<div id = "noMyFeeds">'+'No feeds to show'+'</div>');
    	}

    for(let i = 0 ; i <  data.myfeeds.length;i++)
    {
    	$('#MyFeeds').append('<section class= "dynamicFeeds"><h3>'+data.myfeeds[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.myfeeds[i].feed.value+'</p><div class = "hoverOverLike"><abbr class="count'+data.myfeeds[i].id+'" title="">'+data.totalLikes[i]+'</abbr><input type = "button" class ="'+data.myfeeds[i].id+' likeButton" value="'+data.likedOrNot[i]+'"></input></div></section>')
    }
    	
//    for(let i = 0;i < data.myfeeds.length;i++)
//    {
//    	for(let j = 0;j < data.myfeeds[i].length;j++)
//    	{
//    		
//    	}
//    }
    	
    },
    dataType : 'json'
});

$.ajax({
	type: 'GET',
    url: '/GetListOfAllFeeds',
    success: function (data, status, xhr) {
    	
    	if(data.allfeeds.length == 0)
    	{
    		$('#AllFeeds').append('<div id = "noAllFeeds">'+'No feeds to show'+'</div>');
    	}

    for(let i = 0 ; i <  data.allfeeds.length;i++)
    {
    	$('#AllFeeds').append('<section class= "dynamicFeeds"><h3>'+data.allfeeds[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.allfeeds[i].feed.value+'</p><div class = "hoverOverLike"><abbr class="count'+data.allfeeds[i].id+'" title="">'+data.totalLikes[i]+'</abbr><input type = "button" class ="'+data.allfeeds[i].id+' likeButton" value="'+data.likedOrNot[i]+'"></input></div></section>')
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
        	
        	if(data.fetchfeedsbymail.length == 0)
        	{
        		$('.mainTwo').append('<div id = "noFeedsInThisMail">'+'No feeds to show'+'</div>');
        	}
        	else
        	{
        		$('#noFeedsInThisMail').remove();
        	}
        	
        	for(let i = 0 ; i <  data.fetchfeedsbymail.length;i++)
            {
            	$('.mainTwo').append('<section class= "dynamicFeeds"><h3>'+data.fetchfeedsbymail[i].name+'</h3><h6 style="color:#b3b3b3">'+data.date[i]+' at '+data.time[i]+'</h6><p>'+data.fetchfeedsbymail[i].feed.value+'</p><abbr class="count'+data.fetchfeedsbymail[i].id+'" title="">'+data.totalLikes[i]+'<div class = "hoverOverLike"></abbr><input type = "button" class ="'+data.fetchfeedsbymail[i].id+' likeButton" value="'+data.likedOrNot[i]+'"></input></div></section>')
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


$(function() {
    $('.tabcontent').on('click','.likeButton',function() {
    	console.log('likeIcon');
//    	let timeAndDate = $(this).closest('section').find('h6').text().replace(" at "," ");
//    	let name = $(this).closest('section').find('h3').text();
    	let id = $(this).closest('section').find('.likeButton').attr('class').substring(0,$(this).closest('section').find('.likeButton').attr('class').indexOf(" "));
    	console.log('id '+id);
//    	let newCheck;
//    	let time = timeAndDate.substring(timeAndDate.indexOf(' '),(timeAndDate.length));
//    	let date = timeAndDate.substring(0,timeAndDate.indexOf(' '));
    	
//    	name : name,
//		time : time,
//		date : date
    	
//    	$('.tabcontent').closest('.likeIcon,.likespan').toggleClass( 'press',true);
//    	date = dateFormatting(date);
    	
//    	var check = false;
//    	$(this).closest('section').find('.likeButton').val("Liked");
    	
//    	console.log($(this).attr('class'))
    	
        $.ajax({
        	type: 'PUT',
            url: '/likeFunction',
            data :{id : id},
            success: function (data, status, xhr) {
//            	newCheck = data.check;
            	console.log(data.check)
           
            	if(data.check == true)
            	{
            		console.log('like test like icon true');
//            		$('.tabcontent .likeIcon').closest('.likeIcon,.likespan').toggleClass( 'press',true);
//            		$('.tabcontent').closest('section').find('.likeIcon,.likespan').toggleClass( 'press',true);
//            		$(this).closest('section').find('.likeButton').val("liked");
//            		$('.tabcontent').on('click','.likeButton').val("liked");
            		
            		$('.'+data.id+'').val('unlike');
//            		console.log($('.count'+data.id+'').text());
            		$('.count'+data.id+'').text(''+data.totalLikes+'');
            	}
            	else
            	{
            		console.log('like test like span false');
//            		$('.tabcontent .likeIcon').closest('.likeIcon,.likespan').toggleClass( 'press',false);
//            		$('.tabcontent').closest('section').find('.likeIcon,.likespan').toggleClass( 'press',false);
//            		$(this).closest('section').find('.likeButton').val("NotLiked");
//            		$('.tabcontent').on('click','.likeButton').val("liked");
            		
            		$('.'+data.id+'').val('Like');
//            		console.log($('.count'+data.id+'').text());
            		$('.count'+data.id+'').text(''+data.totalLikes+'');
            	}
            },
            dataType : 'json'
        });
 
    });
  });


function dateFormatting(test)
{
	console.log(test)
	var date = test;
	var d = new Date(date.split("-").reverse().join("-"));
	var dd = ('0'+d.getDate()).slice(-2);
	var mm = ('0'+(d.getMonth()+1)).slice(-2);
	var yyyy = d.getFullYear();
	var newdate = yyyy+"-"+mm+"-"+dd
	
	return newdate;
}


$('.tabcontent').on("mouseenter", ".hoverOverLike", function() {
	
	console.log("code enters");
	let id = $(this).closest('section').find('.likeButton').attr('class').substring(0,$(this).closest('section').find('.likeButton').attr('class').indexOf(" "));
	
	console.log(id);
    // hover starts code here
	
	$.ajax({
    	type: 'GET',
        url: '/hoverFunction',
        data :{id : id},
        success: function (data, status, xhr) {

        	
        	let like = "";
        	for(let i = 0;i < data.likes.length;i++)
        	{
        		 like = like+'\n'+data.likes[i];
        	}
        	$('.count'+data.id+'').attr('title' ,like);
        	$('.'+data.id+'').attr('title' ,like);
        },
        dataType : 'json'
    });
	
});


