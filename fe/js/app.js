(function (window) {
	'use strict';

	// Your starting point. Enjoy the ride!
	ajaxCall('/api/todos', 'GET', {}, getAllList);

	//엔터 클릭 시에 발생하는 event
	$('.new-todo').keypress(function(event){
		var text = $(this).val();

		if(event.which == 13 && text != ''){
			addTodo(text);
		}
	});

	//필터링을 위한 a 태그 click 이벤트.
	$('a').click(function(event){
		event.preventDefault();
		var href = $(this).attr('href');

		$('.selected').attr('class','');
		$(this).attr('class','selected');

		if(href == '#/'){
			$('.todo-list>li').show();
		}else if(href == '#/active'){
			$('.todo-list>li:not(.completed)').show();
			$('.completed').hide();
		}else if(href == '#/completed'){
			$('.completed').show();
			$('.todo-list>li:not(.completed)').hide();
		}
	});

	//check-box클릭시에 발생하는 event.
	$('.clear-completed').click(function(){
		$('.completed').remove();
		ajaxCall('/api/todos/deleteCompleted')
	});

})(window);

function syncToDoListCount(){
	var count = $('.todo-list>li:not(.completed)').length*1;
	$('.todo-count>strong').html(count);
}

function getAllList(result){
	$.each(result, function(i, val){
		var li = $('<li>'+
						'<div class="view">'+
							'<input class="todo-id" type="hidden" value="'+val.id+'">'+
							'<input class="toggle" type="checkbox">'+
							'<label>'+val.todo+'</label>'+
							'<button class="destroy"></button>'+
						'</div>'+
						'<input class="edit" value="Rule the web">'+
					'</li>');
			if(val.completed == 1){
				li.attr('class', 'completed');
				li.find('.toggle').attr('checked','checked')
			} 
				
			li.find('.toggle').click(function(){
				toggleEvent($(this).parent().parent());
			});

			li.find('.destroy').click(function(){
				deleteEvent($(this).parent().parent());
			});

			$('.todo-list').prepend(li);
		});
	syncToDoListCount();
}

function addTodo(text){
	ajaxCall('/api/todos','PUT', {todo : text}, 
		function(result){
			var li = $('<li>'+
							'<div class="view">'+
								'<input class="todo-id" type="hidden" value="'+result+'">'+
								'<input class="toggle" type="checkbox">'+
								'<label>'+text+'</label>'+
								'<button class="destroy"></button>'+
							'</div>'+
							'<input class="edit" value="Rule the web">'+
						'</li>');
					
			li.find('.toggle').click(function(){
				toggleEvent($(this).parent().parent());
			});

			li.find('.destroy').click(function(){
				deleteEvent($(this).parent().parent());
			});
				
			$('.todo-list').prepend(li);
			$('.new-todo').val('');
			syncToDoListCount();
	});
}

function deleteEvent(parentLi){
	var id = parentLi.find('.todo-id').val();
	ajaxCall('/api/todos/'+id,'DELETE', {},
		function(result){
			parentLi.remove();		
			syncToDoListCount();
		});
}

function toggleEvent(parentLi){
	var data = {id : parentLi.find('.todo-id').val()};

	if(parentLi.attr('class') != 'completed'){
		data.completed = 1;
		
	}else{
		data.completed = 0;
		
	}

	ajaxCall('/api/todos', 'POST', data, 
		function(result){
			if(data.completed == 0){
				parentLi.attr('class', '');
			}else{
				parentLi.attr('class','completed');
			}
			syncToDoListCount();
		});
	
}

function ajaxCall(url, type, data, successCb){
	$.ajax({
		url : url,
		type : type,
		data : data,
		success : successCb,
		error : function(err){
			console.log(err.message);
		}
	});
}