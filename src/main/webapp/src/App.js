import React from 'react'
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import GlobalContainer from './components/Containers/GlobalContainer'
import Home from './components/Pages/Home'

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
