$('document').ready(function() {

	$('.ui.dropdown').dropdown({
		on : 'hover',
		delay : {
			hide : 150,
			show : 50,
			search : 50,
			touch : 50
		}
	});
});

$(document).on('click', 'a[href]', function(e) {
	var link = $(this), href = link.attr('href');

	if (/^(\/\/|http)/.test(href) && href.search(location.hostname) < 0) {
		link.attr('target', '_blank');
	}
});