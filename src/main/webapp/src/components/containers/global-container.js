import React from 'react'
import {Layout, Menu, Breadcrumb, Icon, Dropdown, Avatar} from 'antd'
import styled from 'styled-components'
import {withRouter, Link} from 'react-router-dom'
import {toCapitalCase} from '../../utils/strings'

const {SubMenu} = Menu
const {Header, Content, Footer, Sider} = Layout

const Logo = styled.div`
    width: 120px;
    height: 31px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px 28px 16px 0;
    float: left;
`

const GlobalContainer = props => {
    const {
        children,
        location: {pathname}
    } = props

    const bc = pathname.substring(1).split('/')

    const userInfo = {
        firstName: 'Last',
        lastName: 'Test'
    }
    return (
        <Layout>
            <Header className="header">
                <Logo/>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    defaultSelectedKeys="dashboard"
                    style={{
                        lineHeight: '64px',
                        float: 'left',
                        display: 'flex',
                        alignItems: 'left',
                        justifyContent: 'left'
                    }}
                >
                    <Menu.Item key="dashboard">Dashboard</Menu.Item>
                </Menu>
                <Menu
                    mode="horizontal"
                    theme="dark"
                    defaultSelectedKeys={['2']}
                    style={{
                        lineHeight: '64px',
                        display: 'flex',
                        alignItems: 'right',
                        justifyContent: 'right',
                        marginRight: '24px'
                    }}
                >
                    <Dropdown
                        overlay={
                            <Menu>
                                <Menu.Item key="0">
                                    <span>Link 1</span>
                                </Menu.Item>
                            </Menu>
                        }
                    >
                        <span style={{color: '#999', fontSize: '14px'}}>
                            <Avatar icon="user" size="small"
                                    style={{marginRight: '10px'}}/> {userInfo.firstName} {userInfo.lastName}
                        </span>
                    </Dropdown>
                </Menu>
            </Header>
            <Content style={{padding: '0 50px'}}>
                <Breadcrumb style={{margin: '16px 0'}}>
                    <Breadcrumb.Item>
                        <Link to="/">Home</Link>
                    </Breadcrumb.Item>
                    {bc.map(item => (
                        <Breadcrumb.Item key={item}>
                            <Link to={`/${item}`}>{toCapitalCase(item)}</Link>
                        </Breadcrumb.Item>
                    ))}
                </Breadcrumb>
                <Layout style={{padding: '24px 0', background: '#fff'}}>
                    <Sider width={200} style={{background: '#fff'}}>
                        <Menu mode="inline" defaultSelectedKeys={['1']} defaultOpenKeys={['sub1']}
                              style={{height: '100%'}}>
                            <Menu.Item key="accounts">
                                <Link to="/accounts">
                                    <span>
                                        <Icon type="bank"/>
                                        Accounts
                                    </span>
                                </Link>
                            </Menu.Item>
                            <SubMenu
                                key="events"
                                title={
                                    <span>
                                        <Icon type="clock-circle"/>
                                        Events
                                    </span>
                                }
                            >
                                <Menu.Item key="store">Store</Menu.Item>
                                <Menu.Item key="queue">Queue</Menu.Item>
                            </SubMenu>
                            <Menu.Item key="logMonitoring">
                                <span>
                                    <Icon type="monitor"/>
                                    Monitoring
                                </span>
                            </Menu.Item>
                        </Menu>
                    </Sider>
                    <Content style={{padding: '0 24px', height: '100%'}}>{children}</Content>
                </Layout>
            </Content>
            <Footer style={{textAlign: 'center'}}>Footer</Footer>
        </Layout>
    )
}

export default withRouter(GlobalContainer)
