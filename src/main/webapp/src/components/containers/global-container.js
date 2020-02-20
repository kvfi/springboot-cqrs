import React from 'react'
import { Layout, Menu, Breadcrumb, Icon, Dropdown, Avatar } from 'antd'
import styled from 'styled-components'

const { SubMenu } = Menu
const { Header, Content, Footer, Sider } = Layout

const Logo = styled.div`
    width: 120px;
    height: 31px;
    background: rgba(255, 255, 255, 0.2);
    margin: 16px 28px 16px 0;
    float: left;
`

const GlobalContainer = props => {
    const { children } = props

    const userInfo = {
        firstName: 'Last',
        lastName: 'Test'
    }
    return (
        <Layout>
            <Header className="header">
                <Logo />
                <Menu
                    theme="dark"
                    mode="horizontal"
                    defaultSelectedKeys={['2']}
                    style={{
                        lineHeight: '64px',
                        float: 'left',
                        display: 'flex',
                        alignItems: 'left',
                        justifyContent: 'left'
                    }}
                >
                    <Menu.Item key="1">nav 1</Menu.Item>
                    <Menu.Item key="2">nav 2</Menu.Item>
                    <Menu.Item key="3">nav 3</Menu.Item>
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
                        <span style={{ color: '#999', fontSize: '14px' }}>
                            <Avatar icon="user" size="small" style={{ marginRight: '10px' }} /> {userInfo.firstName} {userInfo.lastName}
                        </span>
                    </Dropdown>
                </Menu>
            </Header>
            <Content style={{ padding: '0 50px' }}>
                <Breadcrumb style={{ margin: '16px 0' }}>
                    <Breadcrumb.Item>Home</Breadcrumb.Item>
                    <Breadcrumb.Item>List</Breadcrumb.Item>
                    <Breadcrumb.Item>App</Breadcrumb.Item>
                </Breadcrumb>
                <Layout style={{ padding: '24px 0', background: '#fff' }}>
                    <Sider width={200} style={{ background: '#fff' }}>
                        <Menu mode="inline" defaultSelectedKeys={['1']} defaultOpenKeys={['sub1']} style={{ height: '100%' }}>
                            <SubMenu
                                key="sub1"
                                title={
                                    <span>
                                        <Icon type="user" />
                                        subnav 1
                                    </span>
                                }
                            >
                                <Menu.Item key="1">option1</Menu.Item>
                                <Menu.Item key="2">option2</Menu.Item>
                                <Menu.Item key="3">option3</Menu.Item>
                                <Menu.Item key="4">option4</Menu.Item>
                            </SubMenu>
                            <SubMenu
                                key="sub2"
                                title={
                                    <span>
                                        <Icon type="laptop" />
                                        subnav 2
                                    </span>
                                }
                            >
                                <Menu.Item key="5">option5</Menu.Item>
                                <Menu.Item key="6">option6</Menu.Item>
                                <Menu.Item key="7">option7</Menu.Item>
                                <Menu.Item key="8">option8</Menu.Item>
                            </SubMenu>
                            <SubMenu
                                key="sub3"
                                title={
                                    <span>
                                        <Icon type="notification" />
                                        subnav 3
                                    </span>
                                }
                            >
                                <Menu.Item key="9">option9</Menu.Item>
                                <Menu.Item key="10">option10</Menu.Item>
                                <Menu.Item key="11">option11</Menu.Item>
                                <Menu.Item key="12">option12</Menu.Item>
                            </SubMenu>
                        </Menu>
                    </Sider>
                    <Content style={{ padding: '0 24px', height: '100%' }}>{children}</Content>
                </Layout>
            </Content>
            <Footer style={{ textAlign: 'center' }}>Footer</Footer>
        </Layout>
    )
}

export default GlobalContainer
