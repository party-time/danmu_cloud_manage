var TableTr =  React.createClass({
	render : function(){
		//var object = {};
		var options = [];
		for (var key in this.props.content){
			  //console.log(key+'=============='+this.props.content[key]);
			  //eval("object." + key + "='" + this.props.content[key] + "'"); 
			  options.push(<td>{this.props.content[key]}</td>)
		}
	
		return	<tr>
					{options}
				</tr>
	}
});


/*function initTable(tableData){
	var TableTag = React.createClass({
		render : function(){
			var options = [];
			for (var i=0; i<this.props.tableData; i++) {
				options.push(<TableTr content={this.props.tableData[i].name}/>)
			};
			return	<table>
						{options}
					</table>
		}
	});
}*/
var TableHead = React.createClass({
	render : function(){
		//var object = {};
		var options = [];
		for(var i=0; i<this.props.title.length; i++){
			var object = this.props.title[i];
			options.push(<th>{object.title}</th>)
		}
		return	<tr>
					{options}
				</tr>
	}
});
var PageTag =  React.createClass({
	getInitialState: function () {
		return { 
			page: 1,
			addClass:'disabled'
		};
	},
	firstPageClick: function () {	
		this.setState(function(state) {
		  return {page: 1};
		});
	},
	prePageClick: function () {
		this.setState(function(state) {
		  return {page: state.page - 1};
		});
	},
	nextPageClick: function () {
		this.setState(function(state) {
			return {page: state.page + 1};
		});
	},
	lastPageClick: function () {
		this.setState(function(state) {
		  return {page: 100};
		});
	},
	render : function(){
		return <div className="page">
					<span className="firstPage"  onClick={this.firstPageClick}>首页</span>
					<span className="prePage" onClick={this.prePageClick}>&lt;上一页</span>
					<span className="nextPage" onClick={this.nextPageClick}>下一页&gt;</span>
					<span className="lastPage" onClick={this.lastPageClick}>末页</span>
				</div>
	}
});

function initTable(tableData,title){
	var options = [];
	for (var i=0; i<tableData.length; i++) {
		options.push(<TableTr content={tableData[i]}/>)
	};
	
	return	<div>
				<table>
					<TableHead title={title}/>
					{options}
				</table>
				<PageTag/>
			</div>

}


function init(){
	var title= [
		{field: 'id',title: '编号',align: 'center'},
		{field: 'name',title: '名称',align: 'center'},
		{field: 'value',title: '值',align: 'center'},
	];
	var table = [
		{name:'1111',value:'2222','id':'22222'},
		{name:'2222',value:'2222','id':'22222'},
		{name:'3333',value:'2222','id':'22222'}
	]
	ReactDOM.render(initTable(table,title),document.getElementById('main'));
}
init();