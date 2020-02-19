import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import GlobalContainer from './components/containers/global-container'
import Home from './components/pages/home'

const App = () => {
    return (
        <Router>
            <GlobalContainer>
                <Switch>
                    <Route path="/" exact>
                        <Home />
                    </Route>
                </Switch>
            </GlobalContainer>
        </Router>
    )
}

export default App
