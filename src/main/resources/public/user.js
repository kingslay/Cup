var App = React.createClass({
    getInitialState: function () {
        return ({users: []});
    },
    componentDidMount: function() {
        $.get("/user/findAll", function(result) {
            if (this.isMounted()) {
                this.setState({users:result});
            }
        }.bind(this));
    },
    render: function () {
        return (
            <UserList users={this.state.users}/>
        )
    }
})

var UserList = React.createClass({
    render: function () {
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
                    this.props.users.map(user =>
                    <User key={user.accountid} user={user}/>
                )}
                </tbody>
                </table>
        )
    }
})

var User = React.createClass({
    render: function () {
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
})
React.render(<App />, document.getElementById('react'))