//import 'bootstrap/dist/css/bootstrap.css';
var React = require('react');
var ReactDOM = require('react-dom');
var $ = require('jquery');
class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        $.get("/user/findAll")
            .then((response) =>
                this.setState({users: response})
            )
    }

    render() {
        return (
            <UserList users={this.state.users}/>
        )
    }
}

class UserList extends React.Component {
    render() {
        return (
            <table className="table table-condensed">
                <thead>
                <tr>
                    <th>手机号码</th>
                    <th>性别</th>
                    <th>昵称</th>
                    <th>水杯场景</th>
                    <th>体质</th>
                    <th>出生年月</th>
                    <th>身高(cm)</th>
                    <th>体重(kg)</th>
                </tr>
                </thead>
                <tbody>
                {
                    this.props.users.map((user) =>
                        <User key={user.accountid} user={user}/>
                    )}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.user.phone}</td>
                <td>{this.props.user.sex}</td>
                <td>{this.props.user.nickname}</td>
                <td>{this.props.user.scene}</td>
                <td>{this.props.user.constitution}</td>
                <td>{this.props.user.birthday}</td>
                <td>{this.props.user.height}</td>
                <td>{this.props.user.weight}</td>
            </tr>
        )
    }
}
ReactDOM.render(<App />, document.getElementById('user'))