var HelloMessage = React.createClass({
  render: function() {
    return <h1 className="foo">Hello World！</h1>;
  }
});
var HelloMessage2 = React.createClass({
  render: function() {
    return <h1>Hello {this.props.name}</h1>;
  }
});
var LikeButton = React.createClass({
	getInitialState: function() {
		return {liked: false};
	},
	handleClick: function(event) {
		this.setState({liked: !this.state.liked});
	},
	render: function() {
	  var text = this.state.liked ? '喜欢' : '不喜欢';
	  return (
		<button onClick={this.handleClick}>
		  你<b>{text}</b>我。点我切换状态。
		</button>
	  );
	}
});

var HelloMessage3 = React.createClass({
  getDefaultProps: function() {
    return {
      name: 'Runoob'
    };
  },
  render: function() {
    return <h1>Hello {this.props.name}</h1>;
  }
});

var Hello = React.createClass({
	getInitialState: function () {
	  return {
		opacity: 1.0
	  };
	},

	componentDidMount: function () {
	  this.timer = setInterval(function () {
		var opacity = this.state.opacity;
		opacity -= .05;
		if (opacity < 0.1) {
		  opacity = 1.0;
		}
		this.setState({
		  opacity: opacity
		});
	  }.bind(this), 100);
	},

	render: function () {
	  return (
		<div style={{opacity: this.state.opacity}}>
		  Hello {this.props.name}
		</div>
	  );
	}
});
 
var array = [
	<HelloMessage />,
	<HelloMessage2 name="baby" />,
	<LikeButton />,
	<HelloMessage3/>,
	<Hello  name="world999999999999999999999999999999999999"/>
];
var arrayDiv = <div>{array}</div>
ReactDOM.render(
  arrayDiv,
  document.getElementById('example')
);


var Example2Dom = React.createClass({
  render: function() {
    return <h1 className="foo">Example2Dom！</h1>;
  }
});



ReactDOM.render(
	<Example2Dom />
	,
	document.getElementById('example2')
);


function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

const element = <Welcome name="Sara" />;
ReactDOM.render(
  element,
  document.getElementById('root')
);