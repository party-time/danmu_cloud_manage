var element = (
    <div>
      <h1>Hello, world!</h1>
      <h2>It is {new Date().toLocaleTimeString()}.</h2>
    </div>
 );
 
var Element2 = React.createClass({
	render:function(){
		return  <div><h1>Hello, world!</h1><h2>It is {new Date().toLocaleTimeString()}.</h2></div>
	}
});

var selectTag = (
	<select>
		<option>1</option>
		<option>2</option>
		<option>3</option>
	</select>
)

/*var selectTag2 = React.createClass({
	getInitialState:function(){
		 return {info:""};
    },
	handleChange:funciton(event){
	},
	render:funciton(){
		return <selectTag/>
	}
})*/

var InputTag = (
	<input type="text" value="1"/>
)


var InputTagParam = function(props){
	return <input type="text"  value={props.name}/>;
}

var Content = React.createClass({
	getInitialState:function(){
		return {
			inputText:'',
		};
	},
	handleChange:function(event){
		this.setState({inputText:event.target.value});
	},
	handleClick:function(){
		console.log("props name is " + this.props.selectName + " \n and inputText is "  + this.state.inputText);
	},
	render:function(){

	 return <div>
			<textarea onChange = {this.handleChange} value={this.props.inputText} ></textarea>
		</div>;
	},
});

//评论组件
var Comment = React.createClass({
	getInitialState:function(){
		return {
			names:["Tom","Axiba","daomul"],
			dataArray:[{value:'1',name:'Tome'},{value:'2',name:'Axiba'},{value:'3',name:'LiLY'}],
			selectName:''
		};
	},
	handleSelect:function(event){
		this.setState({selectName : event.target.value});
		console.log(event.target.value)
	},
	render:function(){
		var options = [];
		//往options中添加子option
		for (var option in this.state.dataArray) {
			//options.push(<option value={this.state.names[option]}> {this.state.names[option]}  </option>)
			options.push(<option value={this.state.dataArray[option].value}> {this.state.dataArray[option].name}  </option>)
		};
		return 	<div>
					<Content inputText = {this.state.selectName}/>
					<select onChange = {this.handleSelect}>
					 {options}
					</select>
				</div>;
	},
});
ReactDOM.render(<Comment/>,document.getElementById('root'));
//ReactDOM.render(<Content inputText="2222222222222"/>,document.getElementById('root'));


var MyContainer = React.createClass({
  getInitialState: function () {
    return {
      checked: true
    };
  },
  render: function() {
    return (
      <ToggleButton text="Toggle me" checked={this.state.checked} />
    );
  }
});

// 子组件
var ToggleButton = React.createClass({
  render: function () {
    // 从【父组件】获取的值
    var checked = this.props.checked,
        text = this.props.text;

    return (
        <label>{text}: <input type="checkbox" checked={checked} /></label>
    );
  }
});

ReactDOM.render(<MyContainer />,document.getElementById('son'));

ReactDOM.render(<ToggleButton />,document.getElementById('father'));
//ReactDOM.render(InputTag,document.getElementById('root'));
//ReactDOM.render(<InputTagParam name="12123123"/>,document.getElementById('root'));
