/*
Copyright (c) 2003-2012, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

CKEDITOR.editorConfig = function( config )
{
	config.language = 'zh-cn'; //配置语言     
	config.uiColor = 'blue'; //背景颜色     
	config.width = 700; //宽度     
	config.height = 300; //高度   
	config.skin='v2';       //工具栏    
	config.resize_enabled = false;
	filebrowserBrowseUrl = '/ckfinder2.3.1/ckfinder.html';
	filebrowserImageBrowseUrl = '/ckfinder2.3.1/ckfinder.html?type=Images';
	filebrowserFlashBrowseUrl = '/ckfinder2.3.1/ckfinder.html?type=Flash';
	filebrowserUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Files';
	filebrowserImageUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Images';
	filebrowserFlashUploadUrl = '/ckfinder2.3.1/core/connector/java/connector.java?command=QuickUpload&type=Flash';

	config.toolbar =  [
	                   ['Source','-','Save','NewPage','Preview','-','Templates'],
	                   ['Cut','Copy','Paste','PasteText','PasteFromWord','-','Print', 'SpellChecker', 'Scayt'],
	                   ['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
	                   ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField'],
	                   '/',
	                   ['Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
	                   ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],
	                   ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
	                   ['Link','Unlink','Anchor'],
	                   ['Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],
	                   '/',
	                   ['Styles','Format','Font','FontSize'],
	                   ['TextColor','BGColor']
	                   ]; 

};
