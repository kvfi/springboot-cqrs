import React from 'react'
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'
import GlobalContainer from './components/containers/global-container'
import Home from './components/pages/home'
import NoMatch from './components/pages/NoMatch'
import Accounts from './components/pages/accounts'

const App = () => {
    return (
        <Router>
            <GlobalContainer>
                <Switch>
                    <Route path="/" component={Home} exact/>
                    <Route path="/accounts" component={Accounts} exact/>
                    <Route component={NoMatch}/>
                </Switch>
            </GlobalContainer>
        </Router>
    )
}

export default App
