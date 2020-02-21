import {createStore} from 'redux'

import rootReducer from './reducers'

/* eslint no-underscore-dangle: 0 */
export default createStore(rootReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__())
