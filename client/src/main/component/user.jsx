var React = require('react');
var ReactDOM = require('react-dom');
var Table = require('antd').Table;
var $ = require('jquery');
const columns = [{
    title: '手机号码',
    dataIndex: 'phone'
}, {
    title: '性别',
    dataIndex: 'sex',
    sorter: true
}, {
    title: '昵称',
    dataIndex: 'nickname'
}, {
    title: '水杯场景',
    dataIndex: 'scene'
}, {
    title: '体质',
    dataIndex: 'constitution'
}, {
    title: '出生年月',
    dataIndex: 'birthday'
}, {
    title: '身高(cm)',
    dataIndex: 'height'
}, {
    title: '体重(kg)',
    dataIndex: 'weight'
}];

class User extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: [], pagination: {}, loading: false,};
    }
    fetch(params = {}) {
        this.setState({loading: true});
        $.get("/userInfoes",params)
            .then((response) => {
                    const pagination = this.state.pagination;
                    pagination.total = response.page.totalPages;
                    this.setState({users: response._embedded.userInfoes, loading: false, pagination,})
                }
            )
    }


    handleTableChange(pagination, filters, sorter) {
        const pager = this.state.pagination;
        pager.current = pagination.current;
        this.setState({
            pagination: pager
        });

        const params = {
            size: pagination.pageSize,
            page: pagination.current,
            sort: sorter.order == "ascend" ? sorter.field: sorter.field+",desc"
        };
        for (let key in filters) {
            params[key] = filters[key];
        }
        this.fetch(params);
    }

    componentDidMount() {
        this.fetch()
    }

    render() {
        return (
            <Table columns={columns}
                   dataSource={this.state.users}
                   pagination={this.state.pagination}
                   loading={this.state.loading}
                   onChange={this.handleTableChange} rowKey={(r)=>r._links.self.href}/>
        )
    }
}
ReactDOM.render(<User />, document.getElementById('user'))
