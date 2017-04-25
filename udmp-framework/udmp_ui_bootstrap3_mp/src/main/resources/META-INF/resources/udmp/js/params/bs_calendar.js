/**
 * Description:calendar组件
 * 
 * @author 叶清平
 * @date 2016年5月26日
 * 
 */

idefine(function(require, exports, module) {
	var BsCalendar = {
		init : function($bc, options) {
			var settings = $.extend({
                customDay: new Date(),
				eventModel: 'normal',
				width: 220,
                lang: 'CN',
                dataUrl: null,
                addUrl: null,
                delUrl: null
            }, options);
            var isInit = false; // 是否初始化
            var remindMap = {};
            
            var dayNames = {};
            var monthNames = {};
            var lAddEvent = {};
            var lAllDay = {};
            var lEvent = {};
            dayNames['CN'] = new Array('一', '二', '三', '四', '五', '六', '日');
            monthNames['CN'] = new Array('1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月');
            monthValues = new Array('01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12');
            lAddEvent['CN'] = '新增工作项';
            lAllDay['CN'] = '全天';
            
            var div = function (e, classN) {
                return $(document.createElement(e)).addClass(classN);
            };
            
            var clockHour = [];
            var clockMin = [];
            for (var i = 0; i < 24; i++ ){
				if (i < 10){
					clockHour.push(div('div', 'option').text('0' + i))
				} else {
					clockHour.push(div('div', 'option').text(i));
				}
            }
            for (var i = 0; i < 59; i += 5 ){
				if (i < 10){
					clockMin.push(div('div', 'option').text('0' + i))
				} else {
					clockMin.push(div('div', 'option').text(i));
				}
            }
			// 初始化
			$bc.append(
				div('div', 'container-fluid').append(
					div('div', 'row-fluid').append(
						div('div', 'main').append(
							div('div', 'col-md-6').append(
								div('div', 'calendar-pages').append(
									div('div', 'header').css('background-color', settings.color).append(
										div('a', 'prv-m glyphicon glyphicon-menu-left'),
										div('h1'),
										div('a', 'nxt-m glyphicon glyphicon-menu-right'),
										div('div', 'line'),
										div('div', 'day-names')
									),
									div('div', 'days')
								)
							)
						)
					)
				)
            );
			
			if (settings.eventModel == 'normal') {
				$bc.find('.main').append(
					div('div', 'col-md-6').append(
						div('div', 'container-fluid').append(
							div('div', 'add-event').append(
								div('div', 'row-fluid').append(
									div('div', 'col-md-12').append(
										div('div', 'add-new').append(
											div('div', 'title').append(
												'<input type="text" placeholder="' + lAddEvent[settings.lang] + '" />',
												div('span', 'submit glyphicon glyphicon-plus')
											)
										)
									)
								),
								div('div', 'row-fluid').append(
									div('div', 'col-md-12').append(
										 div('div', 'add-time').append(
											div('span', 'disabled glyphicon glyphicon-time'),
											div('div', 'select').addClass('hour').css('background-color', settings.color).append(
												div('span').text('09'),
												div('div', 'dropdown').append(clockHour)
											),
											div('div', 'left').append(':'),
											div('div', 'select').addClass('min').css('background-color', settings.color).append(
												div('span').text('00'),
												div('div', 'dropdown').append(clockMin)
											)
										),
										div('div', 'all-day').append(
											div('fieldset').attr('data-type','disabled').append(
												div('input', 'check').attr('type', 'checkbox'),
												div('label').text(lAllDay[settings.lang])
											)
										)
									)
								),
								div('div', 'row-fluid').append(
									div('div', 'col-md-12').append(
										 div('div', 'events').append(
											div('div', 'event-list').append(
												div('ul', 'list-group')
											)
										)
									)
								)
							)
						)
					)
				);
			}

            // 添加日期
            for (var i = 0; i < 42; i++) {
                $bc.find('.days').append(div('div', 'day'));
            }
            // 添加星期
            for (var i = 0; i < 7; i++) {
                $bc.find('.day-names').append(div('h2').text(dayNames[settings.lang][i]));
            }

            var d = new Date(settings.customDay);
            var year = d.getFullYear();
            var month = d.getMonth();
			var date = d.getDate();

			var isLeapYear = function(year1) {
                var f = new Date();
                f.setYear(year1);
                f.setMonth(1);
                f.setDate(29);
                return f.getDate() == 29;
            };
        
            var feb;
            var febCalc = function(feb) { 
                if (isLeapYear(year) === true) { feb = 29; } else { feb = 28; } 
                return feb;
            };
            var monthDays = new Array(31, febCalc(feb), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

			// 后台获取事件列表
			function queryEvents() {
				$('.added-event').remove();
				var workRemind = {
					month : year + '-' + monthValues[month]
				};
				$.post(settings.dataUrl, workRemind, function(data) {
					if(data) {
						$.each(data, function(index){
							$bc.prepend(
								div('div', 'added-event').attr('data-id', this.id).attr('data-date', this.remindDt).attr('data-time', this.remindTm).attr('data-title', this.title)
							);
							if (isInit == false) {
								var remindTime = this.remindDt + ' ' + this.remindTm;
								registRemind(this.id, remindTime, this.title);
							}
						});
					}
					
					// 添加事件标志
	                $bc.find('.added-event').each(function(i){
	                    $bc.find('.this-month[data-date="' + $(this).attr('data-date') + '"]').append(
	                        div('div','event-single')
	                    );
	                });
					$bc.find('.day').has('.event-single').prepend(div('i','have-event'));
					addEvent($bc.find('.today').attr('data-date'));
					isInit = true;
				});
			}

			// 添加事件
			function addEvent(date) {
				$bc.find('.added-event[data-date="' + date + '"]').each(function (i) {
					$bc.find('.events .event-list .list-group').append(
						div('li', 'list-group-item').append(
							div('p', 'list-group-item-heading').text($(this).attr('data-title')),
							div('div', 'list-group-item-text').append(
								div('div', 'container-fluid').append(
									div('div', 'row-fluid').append(
										div('div', 'col-md-6').text($(this).attr('data-time')),
										div('div', 'col-md-6 text-right').append(
											//div('span', 'glyphicon glyphicon-pencil editBtn').attr('data-time', $(this).attr('data-time')),
											div('span', 'glyphicon glyphicon-trash delBtn').attr('data-id', $(this).attr('data-id'))
										)
									)
								)
							)
						).attr('data-id', $(this).attr('data-id'))
					);
				});
			}

            function calcMonth() {

                monthDays[1] = febCalc(feb);
                
                var weekStart = new Date();
                weekStart.setFullYear(year, month, 0);
                var startDay = weekStart.getDay();  
                $bc.find('.header h1').html(monthNames[settings.lang][month] + ' ' + year);
        
                $bc.find('.day').html('');
                $bc.find('.day').removeClass('this-month').removeClass('pre-month').removeClass('next-month').removeClass('redundant');
				for (var i = 0; i < startDay; i++) {
					if (month == 0) {
						$bc.find('.day').eq(i).addClass('pre-month').append(div('span').text(monthDays[11] - startDay + i + 1));
					} else {
						$bc.find('.day').eq(i).addClass('pre-month').append(div('span').text(monthDays[month-1] - startDay + i + 1));
					}
				}
                for (var i = 1; i <= monthDays[month]; i++) {
                    $bc.find('.day').eq(startDay).addClass('this-month').attr('data-date', year+'-'+ (monthValues[month]) +'-'+i).append(div('span').text(i));
					startDay++;
                }
				if (startDay%7 != 0) {
					var nextMonthDay = 7 - startDay%7;
					for (var i = 0; i < nextMonthDay; i++) {
						$bc.find('.day').eq(startDay).addClass('next-month').append(div('span').text(i + 1));
						startDay++;
					}
				}
				for (var i = startDay; i < $bc.find('.day').size(); i++) {
					$bc.find('.day').eq(i).addClass('redundant');
				}
                if (month == d.getMonth()) {
                    $bc.find('.day.this-month').removeClass('today').eq(date-1).addClass('today');
                } else {
                    $bc.find('.day.this-month').removeClass('today');
                }
            }

            function registRemind(id, remindTime, msg) {
        		var timestamp = (new Date()).getTime();
        		var remindTimestamp = Date.parse(remindTime);
				var interval = remindTimestamp - timestamp;
				if (interval >= 0) {
					var t = window.setTimeout(function() {
						//window.showInfo('info', msg);
						toastr.info(msg, '您有一个工作提醒',  {"timeOut": "0","closeButton": true});
        			}, interval);
					remindMap[id] = t;
				}
            }
            
            queryEvents();
            calcMonth();
			addEvent($bc.find('.today').attr('data-date'));
			
            var arrows = new Array ($bc.find('.prv-m'), $bc.find('.nxt-m'));
            var dropdown = new Array ($bc.find('.add-time .select span'), $bc.find('.add-time .select .dropdown .option'), $bc.find('.add-time .select'));
            var allDay = new Array ('.all-day fieldset[data-type="disabled"]', '.all-day fieldset[data-type="enabled"]');
            var $erase = $bc.find('.event-single .erase');
            
            $bc.find('.select .dropdown .option').hover(function() {
                $(this).css('background-color', settings.color); 
            }, function() {
                $(this).css('background-color', 'inherit'); 
            });
            
            function prevAddEvent() {
                $bc.find('.day').removeClass('selected');
                $bc.find('.today').css('color', settings.color);
				$bc.find('.events .event-list .list-group').html('');
            }
            
            arrows[1].on('click', function () {
                if ( month >= 11 ) {
                    month = 0;
                    year++;
                } else {
                    month++;   
                }
                calcMonth();
                queryEvents();
                prevAddEvent();
            });
            arrows[0].on('click', function () {
                dayClick = $bc.find('.this-month');
                if ( month === 0 ) {
                    month = 11;
                    year--;
                } else {
                    month--;   
                }
                calcMonth();
                queryEvents();
                prevAddEvent();
            });
      
            $bc.on('click', '.this-month', function () {
                prevAddEvent();
                $(this).addClass('selected');
				addEvent($(this).attr('data-date'));
            });
            
            dropdown[0].click(function(){
                dropdown[2].children('.dropdown').hide(0);
                $(this).next('.dropdown').show(0);
            });
            dropdown[1].click(function(){
                $(this).parent().parent().children('span').text($(this).text());
                dropdown[2].children('.dropdown').hide(0);
            });
            $('html').click(function(){
                dropdown[2].children('.dropdown').hide(0); 
            });
            $bc.find('.add-time .select span').click(function(event){
                event.stopPropagation(); 
            });
            
            $bc.on('click', allDay[0], function(){
				$(this).removeAttr('data-type').attr('data-type', 'enabled').children('.check').children().css('background-color', settings.color);
                dropdown[2].children('.dropdown').hide(0);
                $bc.find('.add-time').css('opacity', '0.4').children('.disabled').css('z-index', '10');
				$bc.find('.all-day .check').prop('checked', true);
            });
            $bc.on('click', allDay[1], function(){
                $(this).removeAttr('data-type').attr('data-type', 'disabled').children('.check').children().css('background-color', 'transparent');
                $bc.find('.add-time').css('opacity', '1').children('.disabled').css('z-index', '-1');
				$bc.find('.all-day .check').prop('checked', false);
            });
            
            // 添加工作项
            var dataId = parseInt($bc.find('.total-bar b').text());
            $bc.find('.submit').on('click', function(){
            	if ($bc.find('.day.this-month.selected').size() == 0){
            		window.showInfo('error', '请选择日期');
					return;
				}
                var title = $(this).prev('input').val();
				if(title == ''){
					window.showInfo('error', '请输入标题');
					return;
				}
                var hour = $(this).parents('.add-event').find('.hour > span').text();
                var min = $(this).parents('.add-event').find('.min > span').text();
                var isAllDay = $(this).parents('.add-event').find('.all-day fieldset').attr('data-type');
                var isAllDayText = $(this).parents('.add-event').find('.all-day fieldset label').text();
                var thisDay = $bc.find('.day.this-month.selected').attr('data-date');
				if (thisDay == undefined) {
					thisDay = $bc.find('.today').attr('data-date');
				}
                var time;
                if ( isAllDay == 'disabled' ) {
                    time = hour + ':' + min;
					if ($bc.find('.added-event[data-date="' + thisDay + '"][data-time="' + time + '"]').size() > 0) {
						window.showInfo('error', '同一时间段不允许添加两个工作项');
						return;
					}
                } else {
                    time = isAllDayText;
                }
				
				var workRemind = {
					title : title,
					content : title,
					remindDt : thisDay,
					remindTm : time
				};
				$.post(settings.addUrl, workRemind, function(data) {
					if(data) {
						$bc.find('.day[data-date="' + thisDay + '"]').prepend(
		                    div('div','event-single')
		                );
		                $bc.find('.day').has('.event-single').prepend(div('i','have-event'));
						
						$bc.prepend(
							div('div', 'added-event').attr('data-id', data).attr('data-date', thisDay).attr('data-time', time).attr('data-title', title)
						);
						$bc.find('.events .event-list .list-group').html('');
						addEvent(thisDay);
						var remindTime = thisDay + ' ' + time;
						registRemind(data, remindTime, title);
		                // 重置表单
		                $bc.find('.add-event input[type="text"]').val('').select();
		                $(this).parents('.add-event').find('.hour > span').text('00');
		                $(this).parents('.add-event').find('.min > span').text('00');
		                $bc.find('.all-day .check').prop("checked", false);
					} else {
						window.showInfo('error', '添加工作项失败');
						return;
					}
				});
            });
            
            // 删除工作项
            $bc.on('click', '.event-list .delBtn', function() {
            	var id = $(this).attr('data-id');
				var workRemind = {
					id : id
				};
            	window.showConfirm('确认要删除该通知吗？', function() {
            		var thisDay = $bc.find('.day.this-month.selected').attr('data-date');
    				if (thisDay == undefined) {
    					thisDay = $bc.find('.today').attr('data-date');
    				}
    				$.post(settings.delUrl, workRemind, function(data){
    					if(data && data == 'success') {
    						$bc.find('[data-id="' + id + '"]').remove();
            				if ($bc.find('.added-event[data-date="' + thisDay + '"]').size() == 0) {
            					$bc.find('[data-date="' + thisDay + '"] .event-single').remove();
            					$bc.find('[data-date="' + thisDay + '"] .have-event').remove();
            				}
            				window.clearTimeout(remindMap[id]);
    					}
    				});
            	});
            });
            return $bc[0];
		}
	};
	module.exports = BsCalendar;
});